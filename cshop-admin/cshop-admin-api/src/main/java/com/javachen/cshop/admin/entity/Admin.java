package com.javachen.cshop.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String nickName;

    private String phone;

    private String icon;

    private String email;

    private String password;

    private boolean active;

    @CreationTimestamp
    private Date createTime;// 创建时间

    @UpdateTimestamp
    private Date updateTime;// 最后修改时间
}
