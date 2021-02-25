package com.oe.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DicAreasVO implements Serializable {
    // 主键
    private Integer id;
    // 县id
    private String areaid;
    // 名称
    private String area;
    // 对应市的id
    private String cityid;

}