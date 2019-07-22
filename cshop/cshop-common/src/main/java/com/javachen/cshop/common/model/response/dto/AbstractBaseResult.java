package com.javachen.cshop.common.model.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractBaseResult implements Serializable {
    @Data
    protected static class Links {
        private String self;
        private String next;
        private String last;
    }

    @Data
    protected static class DataBean<T extends AbstractBaseDomain> {
        private String type;
        private Long id;
        private T attributes;
        private T relationships;
        private Links links;
    }
}
