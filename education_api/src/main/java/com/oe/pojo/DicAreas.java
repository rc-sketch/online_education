package com.oe.pojo;

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
@TableName(value = "dic_areas")
public class DicAreas implements Serializable {
    // 主键
    private Integer id;
    // 县id
    private String areaid;
    // 名称
    private String area;
    // 对应市的id
    private String cityid;

}