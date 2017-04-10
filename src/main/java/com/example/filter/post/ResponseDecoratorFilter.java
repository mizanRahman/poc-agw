package com.example.filter.post;

import com.example.FilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import static com.example.FilterConstants.POST_TYPE;

/**
 * Created by mizan on 4/9/17.
 */
@Component
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
        ctx.getResponse().setHeader("X-KM-ProcessingEnd", String.valueOf(System.currentTimeMillis()));
        ctx.getResponse().setHeader("X-KM-Instance-Name", System.getenv("instance.name"));
        return null;
    }
}
