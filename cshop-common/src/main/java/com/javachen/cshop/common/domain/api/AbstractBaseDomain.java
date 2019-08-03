package com.javachen.cshop.common.domain.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbstractBaseDomain implements Serializable {
    private Long id;
    private Date created;
    private Date updated;
}
