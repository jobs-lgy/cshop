package com.javachen.cshop.zuul;

import com.google.common.util.concurrent.RateLimiter;
import com.javachen.cshop.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimiterFilter extends ZuulFilter {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100, 1, TimeUnit.SECONDS);


    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!RATE_LIMITER.tryAcquire(1)) {
            throw new RateLimiterException();
        }
        return null;
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -100;
    }
}
