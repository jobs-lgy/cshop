package com.javachen.cshop.zuul;


import com.alibaba.fastjson.JSON;
import com.javachen.cshop.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 错误filter
 */
@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        Throwable throwable = ctx.getThrowable();
        log.error("Exception " + throwable.getMessage() + " was thrown in filters: ", throwable);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if (throwable instanceof RateLimiterException || throwable.getCause() instanceof RateLimiterException) {
                response.getWriter().write(JSON.toJSONString("系统繁忙,稍后重试"));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 5;
    }


}
