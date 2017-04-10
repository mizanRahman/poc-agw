package com.example.filter.pre;

import com.example.FilterConstants;
import com.example.GatewayProperties;
import com.example.UserCredential;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 * Created by mizan on 4/9/17.
 */
@Component
@Slf4j
public class PasswordAuthenticationFilter extends ZuulFilter {

    @Autowired
    GatewayProperties properties;

    private final String AUTH_HEADER = "X-KM-Password-Authorization";

    @Autowired
    ProxyRequestHelper proxyRequestHelper;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //filter specific endpoints
        return false;
    }

    /*
    *
    * Authorization Request Format:
    * X-KM-Password-Authorization : Base64(userId:passwordHash)
    *
    * */
    @Override
    public Object run() {
        log.info("route properties = {}", properties.isEnabled());
        log.info("route properties = {}", properties.getUserSensitiveApis());

        RequestContext ctx = RequestContext.getCurrentContext();
        UserCredential userCredential = parseFromHeader();

        log.debug("user credential = {}", userCredential);

        if (userCredential.getPasswordHash() == "1234") {
            forbiddenRequest(ctx);
        }

        return null;
    }

    private UserCredential parseFromHeader() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String headerRaw = ctx.getRequest().getHeader(AUTH_HEADER);

        if (headerRaw == null) {
            forbiddenRequest(ctx);
        } else {
            proxyRequestHelper.addIgnoredHeaders(AUTH_HEADER);
        }

        String header = new String(Base64Utils.decodeFromString(headerRaw));
        String[] split = header.split(":");
        return UserCredential.builder()
                .userId(split[0])
                .passwordHash(split[1])
                .build();
    }

    private void forbiddenRequest(RequestContext ctx) {
        ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
//        ctx.setSendZuulResponse(false);
    }


}
