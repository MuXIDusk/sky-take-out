package com.sky.mapper;

import java.time.LocalDateTime;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;

@Mapper
public interface OrderMapper {

    void insert(Orders order);

    @Select("select * from orders where number = #{outTradeNo} and user_id= #{userId}")
    Orders getByNumberAndUserId(String outTradeNo, Long userId);

    void update(Orders orders);

     @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrdertimeLT(Integer status, LocalDateTime orderTime);

    @Select("select * from orders where id = #{id}")
	Orders getById(Long id);

    /**
     * 根据动态条件统计营业额
     * @param map
     */
    Double sumByMap(Map map);

    Integer countByMap(Map map);

    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);
}
