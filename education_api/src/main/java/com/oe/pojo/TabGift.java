package com.oe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tab_gift")
public class TabGift implements Serializable {
    // 主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String giftName;

    private Long price;

}