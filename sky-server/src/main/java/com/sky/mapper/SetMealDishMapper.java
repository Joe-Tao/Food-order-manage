package com.sky.mapper;

import com.sky.dto.SetmealDTO;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * Get setmeal id by dish id
     * @param dishIds
     * @return
     */
    // select setmeal_id from setmeal_dish where dish_id in dishIds
    List<Long> getSetMealIdByDishId(List<Long> dishIds);


    /**
     * Add dishes into setmeal_dish table
     * @param setmealDishes
     */
    void addBatch(List<SetmealDish> setmealDishes);

    /**
     * Delete dishes by setmeal id
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);


    /**
     * Get setmeal dishes by setmeal id
     * @param id
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getBySetmealId(Long id);
}
