package com.lambo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author : 张勇杰
 * @date : 2019/1/4 17:08
 * @Version : v1.0
 * @description
 **/
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    private int status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    private String avatar;

}
