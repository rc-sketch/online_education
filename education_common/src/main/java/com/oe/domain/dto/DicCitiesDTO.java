package com.oe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicCitiesDTO implements Serializable {
    private Integer id;

    private String cityid;

    private String city;

    private String provinceid;

}