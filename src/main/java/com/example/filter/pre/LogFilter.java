package com.example.filter.pre;

import com.example.FilterConstants;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.example.FilterConstants.G12_FILTER_LOG_ENABLED;

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

        DynamicBooleanProperty dynamicBooleanProperty = DynamicPropertyFactory.getInstance().getBooleanProperty(G12_FILTER_LOG_ENABLED, true);

        log.info("dynamic property: {}", dynamicBooleanProperty);

        return null;
    }
}
