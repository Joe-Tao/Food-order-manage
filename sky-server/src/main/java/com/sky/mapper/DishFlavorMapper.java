package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * Add multiple flavors
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * Delete flavor data by dish id
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);


    /**
     * Delete flavor data by dish ids
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * Get flavors by dish id
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
