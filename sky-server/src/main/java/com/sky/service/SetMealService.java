package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {

    /**
     * Add new set meal
     * @param setmealDTO
     */
    void add(SetmealDTO setmealDTO);

    /**
     * Get setmeal page result
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Update set meal
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * Get setmeal by setmeal id
     * @param id
     * @return
     */
    SetmealVO getSetmealById(Long id);

    /**
     * Delete setmeal by setmeal ids
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * Activate setmeal or deactivate setmeal
     * @param status
     * @param setmealId
     */
    void activeOrDeactive(Integer status, Long  setmealId);
}
