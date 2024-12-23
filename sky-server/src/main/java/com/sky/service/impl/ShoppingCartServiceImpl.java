package com.sky.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void save(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        //添加用户ID
        shoppingCart.setUserId(BaseContext.getCurrentId());

        //判断当前菜品或套餐是否在购物车中
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.list(shoppingCart);

        if(shoppingCartsList != null && shoppingCartsList.size() > 0){
            //如果存在，数量+1
            shoppingCart = shoppingCartsList.get(0);
            shoppingCart.setNumber(shoppingCart.getNumber() + 1);
            shoppingCartMapper.updateNum(shoppingCart);
        }else{
            //如果不存在，则添加到购物车，数量默认为1

            // 判断当前添加的是菜品还是套餐
            Long dishId = shoppingCart.getDishId();
            if(dishId != null){
                //添加的是菜品
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setName(dish.getName());

            }else{
                //添加的是套餐
                Setmeal setmeal = setmealMapper.getById(shoppingCart.getSetmealId());
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setName(setmeal.getName());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
        
    }

    @Override
    public List<ShoppingCart> list() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(BaseContext.getCurrentId());
        return shoppingCartMapper.list(shoppingCart);
    }

    @Override
    public void clean() {        
        shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());
    }

    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.list(shoppingCart);
        shoppingCart = shoppingCartsList.get(0);
        int num = shoppingCart.getNumber();

        //判断商品数量
        if(num == 1){
            shoppingCartMapper.delete(shoppingCart);
        }else{
            shoppingCart.setNumber(num - 1);
            shoppingCartMapper.updateNum(shoppingCart);
        }
    }

}
