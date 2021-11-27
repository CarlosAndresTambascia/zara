package com.between.zara.model;

import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class PriceRequest {
    private final Date applicationDate;
    private final Long productId;
    private final Long brandId;
}
