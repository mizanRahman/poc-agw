package com.example.dynamicroute;

import org.springframework.cloud.netflix.zuul.filters.Route;

import java.util.Collection;

/**
 * Created by mizan on 5/1/17.
 */
public interface ZuulRouteStore {

    Collection<Route> findAll();
}
