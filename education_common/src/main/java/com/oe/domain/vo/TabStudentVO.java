package com.oe.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
/***
 * 学生表
 */
public class TabStudentVO implements Serializable {
    private Long id;

    private String name;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private String sourceType;

    private String nickName;

    private String status;

    private String headPic;

    private String qq;

    private String isMobileCheck;

    private String isEmailCheck;

    private String sex;

    private Integer vipLevel;

    private Date birthday;

    private Date lastLoginTime;

    private Integer age;

    private String school;

    private String identity;

    private String field1;

    private String field2;

}