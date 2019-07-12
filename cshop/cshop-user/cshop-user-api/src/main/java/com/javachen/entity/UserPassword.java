package com.javachen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author june
 * @createTime 2019-06-24 22:20
 * @see
 * @since
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "tb_user_password")
public class UserPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String password;
}
