package com.between.zara.service;

import com.between.zara.model.PriceResponse;

import java.util.Date;
import java.util.Optional;

public interface PriceService {
    Optional<PriceResponse> getPricesByDate(Long productId, Long brandId, Date applicationDate);
}
