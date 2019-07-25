package com.javachen.cshop.common.logging;

import org.slf4j.MDC;

/**
 * @author june
 * @createTime 2019-07-21 13:15
 * @see
 * @since
 */
public abstract class RequestIdAware {
    private final String requestId;

    public RequestIdAware() {
        this.requestId = MDC.get(RequestIdMdcFilter.REQUEST_ID);
    }

    public String getRequestId() {
        return requestId;
    }
}
