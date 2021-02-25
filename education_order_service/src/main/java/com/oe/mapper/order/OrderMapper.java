package com.oe.mapper.order;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.oe.pojo.TabOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.mapper.order
 * @ClassName: OrderMapper
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:57
 * @Version: 1.0
 */
public interface OrderMapper extends BaseMapper<TabOrder> {
    @Select("SELECT d.date,IFNULL(d2.sumPostFee,0) as fee FROM\n" +
            "    (SELECT \"01\" as date\n" +
            "    union\n" +
            "    SELECT \"02\"\n" +
            "    union\n" +
            "    SELECT \"03\"\n" +
            "    union\n" +
            "    SELECT \"04\"\n" +
            "    union\n" +
            "    SELECT \"05\"\n" +
            "    union\n" +
            "    SELECT \"06\"\n" +
            "    union\n" +
            "    SELECT \"07\"\n" +
            "    union\n" +
            "    SELECT \"08\"\n" +
            "    union\n" +
            "    SELECT \"09\"\n" +
            "    union\n" +
            "    SELECT \"10\"\n" +
            "    union\n" +
            "    SELECT \"11\"\n" +
            "    union\n" +
            "    SELECT \"12\" ) as d\n" +
            "    LEFT JOIN (SELECT DATE_FORMAT(create_time,\"%m\") AS date,SUM(post_fee)\n" +
            "    as sumPostFee FROM tab_order\n" +
            "    WHERE DATE_FORMAT(create_time,'%Y')=\"2020\" and  teacher_name = #{teacherName}\n" +
            "    GROUP BY DATE_FORMAT(create_time,\"%m\")) as d2\n" +
            "    on d.date = d2.date")
    List<TabOrder> findOrderByTeacherName(@Param("teacherName")String teacherName);


}
