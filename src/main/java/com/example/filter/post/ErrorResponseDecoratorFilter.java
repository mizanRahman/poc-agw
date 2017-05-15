package com.example.filter.post;

import com.example.ErrorResponse;
import com.example.ErrorResponseMapper;
import com.example.KMService;
import com.example.common.JsonMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.InputStream;

import static com.example.FilterConstants.POST_TYPE;

/**
 * Created by mizan on 4/8/17.
 */
@Component
@Slf4j
public class ErrorResponseDecoratorFilter extends ZuulFilter {


    @Autowired
    JsonMapper jsonMapper;

    @Autowired
    ErrorResponseMapper errorResponseMapper;

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpStatus status = HttpStatus.valueOf(ctx.getResponse().getStatus());
//        log.info(status.toString());
//
//        return status.is4xxClientError();

        return false;
    }

    @Override
    public Object run() {
        log.info(getClass().toString());
        RequestContext ctx = RequestContext.getCurrentContext();
        String responseJson = null;
        try {
            ErrorResponse errorResponse = getErrorResponse(ctx);
            if (errorResponse == null) {
                log.warn("response format is unknown. sending as it is");
                log.debug("response: {}", ctx.getResponseBody());
                return null;
            }
            responseJson = jsonMapper.toJson(errorResponse);
            log.debug("response={}", responseJson);
            ctx.setResponseBody(responseJson);
            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);

        } catch (Exception e) {
            log.error("error: {}", e);
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }


    private ErrorResponse getErrorResponse(RequestContext ctx) throws Exception {

        ErrorResponse errorResponse = null;

        KMService kmService = KMService.MAP;
        String ok = ctx.getResponseBody();
        log.info("response body1: {} {}",ok, ctx.getResponseBody());
        String responseBody = ctx.getResponseBody();
        log.info("response body2: {} {} {}", responseBody, ok, ctx.getResponseBody());
        if (responseBody == null) {
            InputStream is = ctx.getResponseDataStream();
            log.info("response body3: {}", ctx.getResponseBody());
            String x = IOUtils.toString(ctx.getResponseDataStream());
            ctx.setResponseBody(x);
            responseBody = IOUtils.toString(ctx.getResponseDataStream());
            log.info("response body4:{} {}", responseBody, ctx.getResponseBody());
        }

        DocumentContext documentContext = JsonPath.parse(responseBody);
        log.info("response body5: {}", ctx.getResponseBody());



        errorResponse = getRegularResponse(documentContext);
        log.info("response body6: {}", ctx.getResponseBody());

        if (errorResponse != null) {
            log.debug("regular response: {}", ctx.getResponseBody());
            return errorResponse;
        }
        errorResponse = getPSMResponse(documentContext);
        if (errorResponse != null) {
            log.debug("psm response: {}", ctx.getResponseBody());
            return errorResponse;
        }
        errorResponse = getKppResponse(documentContext);
        if (errorResponse != null) {
            log.debug("kpp response: {}", ctx.getResponseBody());
            return errorResponse;
        }
        return null;
    }


    private ErrorResponse getRegularResponse(DocumentContext documentContext) {
        try {
            String reason = documentContext.read("$.reason");
            String message = documentContext.read("$.message");
            return ErrorResponse.of(reason, message);
        } catch (PathNotFoundException e) {
            return null;
        }
    }

    private ErrorResponse getPSMResponse(DocumentContext documentContext) {
        try {
            String reason = documentContext.read("$.reasonCode");
            String message = documentContext.read("$.message");
            String status = documentContext.read("$.status");
            return ErrorResponse.of(reason+" hello", message);
        } catch (PathNotFoundException e) {
            return null;
        }
    }


    private ErrorResponse getKppResponse(DocumentContext documentContext) {
        try {
            String reason = documentContext.read("$.executionStatus.reasonCode");
            String message = documentContext.read("$.executionStatus.message");
            String processingStart = documentContext.read("$.executionStatus.processingStart");
            String processingEnd = documentContext.read("$.executionStatus.processingEnd");
            return ErrorResponse.of(reason, message);
        } catch (PathNotFoundException e) {
            return null;
        }
    }


    private KMService getKMService(RequestContext ctx) {
        int port = ctx.getRouteHost().getPort();

        log.debug("port={}", port);
        switch (port) {
            case 4010:
                return KMService.DCP;
            default:
                return KMService.PSM;
        }
    }


}
