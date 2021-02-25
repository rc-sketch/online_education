package com.oe.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/***
 * @Data  set get 方法 toString
 * @AllArgsConstructor   有参
 * @NoArgsConstructor    无参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
// 教师表
public class TabTeacherVO implements Serializable {
    // 教师ID
    private String teacherId;
    // 教师名称
    private String name;
    // 教师等级     1.教授     2.副教授    3.金牌讲师
    private String levelName;
    // 账号密码
    private String password;
    // 邮箱
    private String email;
    // 电话
    private String mobile;
    // 手机号
    private String telephone;
    // 状态   0待审核，1审核通过，2审核不通过，3关闭账户 -1删除账户
    private String status;
    // 教师简介
    private String brief;
    // 认证时间
    private Date loginTime;
    // 创建时间
    private Date createTime;
    // 性别 1.男  2.女
    private String sex;
    // 年龄
    private Integer age;
    // 盐
    private String salt;
    // 备用1
    private String field1;
    // 备用2
    private String field2;

}