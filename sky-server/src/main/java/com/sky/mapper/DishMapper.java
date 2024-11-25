package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;

@Mapper
public interface DishMapper {
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);
}
