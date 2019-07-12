package com.javachen.email.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class EmailRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long id;

    protected String source;

    protected String target;

    protected Date sentTime;

    //1激活、2注册成功
    protected Integer type;

    protected Long userId;

}
