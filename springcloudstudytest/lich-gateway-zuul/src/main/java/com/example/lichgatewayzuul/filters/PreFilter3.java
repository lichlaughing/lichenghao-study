package com.example.lichgatewayzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 网关过滤器
 */
@Component
public class PreFilter3 extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(PreFilter3.class);

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
        RequestContext currentContext = RequestContext.getCurrentContext();
        return currentContext.sendZuulResponse();
    }

    @Override
    public Object run() {
        logger.info("PreFilter3 run");
        return null;
    }
}
