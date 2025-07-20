package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;


public interface DishService {

    /**
     * Add new dish service
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * Dish query
     * @param dishPageQueryDTO
     * @return
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Delete dishes by id in batch
     * @param ids
     */
    public void remove(List<Long> ids);

    /**
     * Modify dishes
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * Get dish by id with corresponding flavor
     * @param id
     * @return
     */
    DishVO getDishByIdWithFlavor(Long id);

    /**
     * Activate or deactivate dish
     * @param status
     * @param id
     */
    void activeOrDeactive(Integer status, Long id);


    /**
     * Get dishes by category id
     * @param categoryId
     * @return
     */
    List<Dish> getDishesByCategoryId(Long categoryId);
}
