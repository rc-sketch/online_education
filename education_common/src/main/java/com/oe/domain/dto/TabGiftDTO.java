package com.oe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
/***
 * 礼物
 */
public class TabGiftDTO implements Serializable {
    private Integer id;

    private String giftName;

    private Long price;

}