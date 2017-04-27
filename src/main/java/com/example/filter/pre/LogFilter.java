package com.example.filter.pre;

import com.example.FilterConstants;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

import static com.example.FilterConstants.G12_FILTER_LOG_ENABLED;

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

        String requestUri = urlPathHelper.getPathWithinApplication(ctx.getRequest());

        Route route = routeLocator.getMatchingRoute(requestUri);
        log.info("full path: {}", route.getFullPath());
        log.info("path : {}", route.getPath());
        log.info("prefix: {}", route.getPrefix());
        log.info("location: {}", route.getLocation());
        return null;
    }
}
