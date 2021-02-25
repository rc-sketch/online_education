package com.oe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicCities implements Serializable {
    private Integer id;

    private String cityid;

    private String city;

    private String provinceid;

}