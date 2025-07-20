package com.sky.controller.admin;

import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "Seat meal Apis")
@Slf4j
public class SetMealController {

    @Autowired
    private SetMealService setMealService;


    /**
     * Add new set meal
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new set meal")
    public Result addSet(@RequestBody SetmealDTO setmealDTO) {
        log.info("add set meal: {}", setmealDTO);
        setMealService.add(setmealDTO);
        return Result.success();
    }

    /**
     * Get setmeal page result
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(("Get page result"))
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setMealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Update setmeal
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("update set")
    public Result updateSet(@RequestBody SetmealDTO setmealDTO) {
        log.info("update set: {}", setmealDTO);
        setMealService.update(setmealDTO);
        return  Result.success();
    }

    /**
     * Get setmeal by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("get setmeal by id")
    public Result<SetmealVO> getSetmealById(@PathVariable Long id) {
        log.info("get setmeal by id:{}", id);
        SetmealVO setmealVO= setMealService.getSetmealById(id);
        return Result.success(setmealVO);
    }


    /**
     * Delete setmeal by setmeal ids
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("delete by setmeal ids")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("delete by setmeal ids:{}", ids);
        setMealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Activate setmeal or deactivate setmeal
     * @param status
     * @param setmealId
     * @return
     */
    @PostMapping("/status/{status}")
    public Result activeOrDeactive(@PathVariable Integer status, Long  setmealId) {
        setMealService.activeOrDeactive(status, setmealId);
        return Result.success();
    }
}
