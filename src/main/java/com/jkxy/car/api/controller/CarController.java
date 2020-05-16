package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(@RequestBody Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    @PostMapping("wildcardFindByCarName")
    public JSONResult wildcardFindByCarName(@RequestBody Map<String, Object> map) {
        String carName = "";
        int offset = 0, rows = 0;
        if (map.containsKey("carName")) {
            carName = String.valueOf(map.get("carName"));
        }
        if (map.containsKey("offset")) {
            offset = (Integer) map.get("offset");
        }
        if (map.containsKey("rows")) {
            rows = (Integer) map.get("rows");
        }
        List<Car> cars = carService.wildcardFindByCarName(carName, offset, rows);
        return JSONResult.ok(cars);
    }

    @PostMapping("updateInventoryById")
    public JSONResult updateInventoryById(@RequestBody Map<String, Object> map) {
        int count = 0, id = 0;
        if (map.containsKey("count")) {
            count = (Integer) map.get("count");
        }
        if (map.containsKey("id")) {
            id = (Integer) map.get("id");
        }
        carService.updateInventoryById(count, id);
        return JSONResult.ok();
    }
}
