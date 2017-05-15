package com.example.dynamicroute;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by mizan on 5/1/17.
 */
@Component
public class SimpleZuulRouteStore implements ZuulRouteStore {

    AtomicReference<Map<String, Route>> routes = new AtomicReference<Map<String, Route>>();

    @Override
    public Collection<Route> findAll() {
        return routes.get().values();
    }
}
