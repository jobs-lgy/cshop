package com.javachen.cshop.common.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbstractBaseDomain implements Serializable {
    private Long id;
    private Date created;
    private Date updated;
}
