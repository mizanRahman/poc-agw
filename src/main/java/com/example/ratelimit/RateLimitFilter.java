package com.example.ratelimit;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Created by mac on 1/15/17.
 */
@Slf4j
@Component
public class RateLimitFilter extends ZuulFilter {

    @Autowired
    RateLimiterService rateLimiterService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {

            RequestContext ctx = RequestContext.getCurrentContext();

            boolean rateLimitCrossed = rateLimiterService.checkRateLimit();
//            ctx.setResponseBody("Hello world");
            if (rateLimitCrossed) {
                throw new RuntimeException("request limit exceeded");
            }
            log.info("request rate is in limit. forwarding...");
        } catch (Exception e) {
            log.error("Error while executing RateLimit Filter. {}", e.getMessage(), e);
            RequestContext.getCurrentContext().setResponseStatusCode(400);
            ReflectionUtils.rethrowRuntimeException(e);
        }

        return null;
    }
}
