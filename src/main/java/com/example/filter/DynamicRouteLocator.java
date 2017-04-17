package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mizan on 4/14/17.
 */
@Slf4j
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String, ZuulProperties.ZuulRoute> routes = new HashMap<String, ZuulProperties.ZuulRoute>();
        routes.putAll(super.locateRoutes());

        log.debug("reloading properties");

        ZuulProperties.ZuulRoute dynamicRoute = new ZuulProperties.ZuulRoute();
        dynamicRoute.setPath("/api/giftcards/test/*");
        dynamicRoute.setUrl("http://httpbin.org");

        routes.put("dynamicRoute", dynamicRoute);

        return routes;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

}
