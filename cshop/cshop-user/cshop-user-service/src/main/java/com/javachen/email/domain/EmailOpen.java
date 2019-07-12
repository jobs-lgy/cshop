package com.javachen.email.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class EmailOpen implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long id;

    protected Date openTime;

    protected String userAgent;

    protected Long emailRecordId;


}
