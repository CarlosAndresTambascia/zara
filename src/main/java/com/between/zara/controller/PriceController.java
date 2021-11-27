package com.between.zara.controller;

import com.between.zara.model.PriceRequest;
import com.between.zara.model.PriceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/prices")
public class PriceController {
    @GetMapping
    public List<PriceResponse> getPrices(@RequestBody PriceRequest request) {
        return null;
    }
}
