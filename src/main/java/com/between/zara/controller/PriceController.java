package com.between.zara.controller;

import com.between.zara.model.PriceResponse;
import com.between.zara.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/prices")
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    @Operation(summary = "Get price for a specific date")
    public ResponseEntity<PriceResponse> getPrices(
            Long productId,
            Long brandId,
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
                    Date applicationDate) {
        return priceService.getPricesByDate(productId, brandId, applicationDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
