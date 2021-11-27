package com.between.zara.controller;

import com.between.zara.model.PriceRequest;
import com.between.zara.model.PriceResponse;
import com.between.zara.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/prices")
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrices(@RequestBody @Valid PriceRequest request) {
        return priceService.getPricesByDate(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
