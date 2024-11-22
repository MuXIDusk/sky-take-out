package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user)"+
    "values" + 
    "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category category);

    @Select("select * from category where type = #{type}")
    List<Category> list(int type);

}
