package me.ulearn.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//#region Task
public class CarController {
    private String brand;
    private Integer power;
    private Integer releaseYear;
    private Integer tankCapacity;
}
//#endregion Task
