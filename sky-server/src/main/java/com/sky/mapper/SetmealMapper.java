package com.sky.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;

@Mapper
public interface SetmealMapper {


    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(long id);

    @Delete("delete from setmeal where id = #{id}")
    void deleteById(long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);

}
