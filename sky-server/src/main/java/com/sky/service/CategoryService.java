package com.sky.service;

import java.util.List;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

public interface CategoryService {

    void save(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void startOrStop(int status, long id);

    List<Category> list(int type);

    void deleteById(Long id);

    void update(CategoryDTO categoryDTO);

}
