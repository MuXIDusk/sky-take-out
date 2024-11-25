package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;

@Mapper
public interface SetmealMapper {


    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

}
