package com.oe.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/19 14:39
 * @Version 1.0
 **/
//122
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {
    private Integer userId;
    private String username;
    private String address;
}
