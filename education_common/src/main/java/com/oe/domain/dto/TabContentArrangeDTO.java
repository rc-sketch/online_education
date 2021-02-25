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
public class TabContentArrangeDTO implements Serializable {
    private Long id;

    private String introduction;

    private Integer classContentTime;

    private String classContent;

    private String contentArrange;

  }