package com.example.filter.pre;

import com.example.FilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by mizan on 4/9/17.
 */
@Component
@Slf4j
public class UserInfoPlacementFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("X-KM-User-AspId","22312312");
        ctx.addZuulRequestHeader("X-KM-User-Id", "41234132");
        ctx.addZuulRequestHeader("X-KM-User-Email", "23242");
        ctx.addZuulRequestHeader("X-KM-User-Phone", "141234234");

        ctx.addZuulRequestHeader("X-KM-User-MpaId", "1432142");


        log.info("user info placed");

        return null;
    }
}
