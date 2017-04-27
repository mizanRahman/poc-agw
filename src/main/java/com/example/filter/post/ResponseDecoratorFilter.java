package com.example.filter.post;

import com.example.FilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.example.FilterConstants.POST_TYPE;

/**
 * Created by mizan on 4/9/17.
 */
@Component
@Slf4j
public class ResponseDecoratorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.RESPONSE_DECORATOR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("error response filter--mizan");




        ctx.getResponse().setHeader("X-KM-ProcessingEnd", String.valueOf(System.currentTimeMillis()));
        ctx.getResponse().setHeader("X-KM-Instance-Name", System.getenv("instance.name"));
        return null;
    }
}
