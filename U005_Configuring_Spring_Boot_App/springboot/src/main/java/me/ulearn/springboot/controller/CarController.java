package me.ulearn.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//#region Task
@RestController
@RequestMapping("/car")
public class CarController {
    @Value("${car.brand:Toyota Camry 3.5}")
    private String brand;
    @Value("${car.power:298}")
    private Integer power;
    @Value("${car.releaseYear:2022}")
    private Integer releaseYear;
    @Value("${car.tankCapacity:60}")
    private Integer tankCapacity;

    @GetMapping("/brand")
    public String getBrand() {
        return brand;
    }

    @GetMapping("/power")
    public Integer getPower() {
        return power;
    }

    @GetMapping("/releaseYear")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @GetMapping("/tankCapacity")
    public Integer getTankCapacity() {
        return tankCapacity;
    }
}
//#endregion Task
