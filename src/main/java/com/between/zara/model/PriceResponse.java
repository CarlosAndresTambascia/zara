package com.between.zara.model;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@RequiredArgsConstructor
public class PriceResponse {
    private final Long productId;
    private final Long brandId;
    private final Long priceList;
    private final Date startDate;
    private final Date endDate;
    private final BigDecimal price;
}
