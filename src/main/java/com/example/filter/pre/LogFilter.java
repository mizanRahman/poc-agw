package com.example.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mac on 1/15/17.
 */
@Slf4j
@Component
public class LogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("{} {}", ctx.getRequest().getMethod(), ctx.getRequest().getRequestURI());

        log.debug("{}", ctx.getFilterExecutionSummary());
        return null;
    }
}
