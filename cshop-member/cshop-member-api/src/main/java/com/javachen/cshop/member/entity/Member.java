package com.javachen.cshop.member.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberLevelId;

    private String username;

    private String nickname;

    private String password;

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

    private boolean enabled;

    private String openId;

    @CreationTimestamp
    private Date createTime;// 创建时间

    @UpdateTimestamp
    private Date updateTime;// 最后修改时间

}
