package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dish management
 */
@RestController
@Api("Dish management Apis")
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    /**
     * Add new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("Add dish:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }


    /**
     * Dish page query
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Page query")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("Page query:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Remove dishes from the platform
     * @return
     */
    @DeleteMapping
    @ApiOperation("Remove dishes")
    public Result removeDish(@RequestParam List<Long> ids) {
        log.info("remove dish ids:{}", ids);
        dishService.remove(ids);
        return Result.success();
    }

    /**
     * Modify dishes
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Modify dishes")
    public Result modifyDish(@RequestBody DishDTO dishDTO) {
        log.info("modify dish:{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Get dish by dish id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get dish by dishId")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("get dish by dishId:{}", id);
        DishVO dishVO= dishService.getDishByIdWithFlavor(id);
        return  Result.success(dishVO);
    }


    /**
     * Set dish status
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Set dish status")
    public Result activeOrDeactive(@PathVariable("status") Integer status, Long id){
        log.info("set dish status:{},id:{}",status,id);
        dishService.activeOrDeactive(status, id);
        return Result.success();
    }

    /**
     * Get dishes by category id
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Get dishes by category id")
    public Result<List <Dish>> list(Long categoryId) {
        log.info("get dishes by categoryId:{}", categoryId);
        List<Dish> dishList = dishService.getDishesByCategoryId(categoryId);
        return Result.success(dishList);
    }


}
