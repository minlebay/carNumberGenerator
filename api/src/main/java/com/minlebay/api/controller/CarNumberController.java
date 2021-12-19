package com.minlebay.api.controller;

import com.minlebay.service.CarNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class CarNumberController {

    private final CarNumberService carNumberService;

    public CarNumberController(CarNumberService carNumberService) {
        this.carNumberService = carNumberService;
    }

    @GetMapping(value = "number/next")
    public String getNext() {
        return carNumberService.getNext();
    }

    @GetMapping(value = "number/random")
    public String getRandom() {
        return carNumberService.getRandom();
    }
}