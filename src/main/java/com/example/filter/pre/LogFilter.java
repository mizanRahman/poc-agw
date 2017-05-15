package com.example.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

/**
 * Created by mac on 1/15/17.
 */
@Slf4j
@Component
public class LogFilter extends ZuulFilter {

    @Autowired
    RouteLocator routeLocator;

    private UrlPathHelper urlPathHelper = new UrlPathHelper();


    @Override
    public String filterType() {
        return "route";
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

        RequestContext ctx = RequestContext.getCurrentContext();

        log.info("{} {}", ctx.getRequest().getMethod(), ctx.getRequest().getRequestURI());

        String requestUri = urlPathHelper.getPathWithinApplication(ctx.getRequest());

        Route route = routeLocator.getMatchingRoute(requestUri);
        log.info("full path: {}", route.getFullPath());
        log.info("path : {}", route.getPath());
        log.info("prefix: {}", route.getPrefix());
        log.info("location: {}", route.getLocation());

        log.info("{} {}{}", ctx.getRequest().getMethod(), route.getLocation(), route.getPath());
        log.info("Headers: {}", ctx.getZuulRequestHeaders());
        for (String header : ctx.getZuulRequestHeaders().keySet()) {
            log.info("{}: {}", header, ctx.getZuulRequestHeaders().get(header));
        }

        return null;
    }
}
