package com.javachen.cshop.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private String icon;

    private Integer gender;

    @ElementCollection
    private List<String> hobbies;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String city;

    private String job;

    private Integer source;

    private Long deptId;

    private String openId;

    private boolean enabled;

    @CreationTimestamp
    private Date createTime;// 创建时间

    @UpdateTimestamp
    private Date updateTime;// 最后修改时间
}
