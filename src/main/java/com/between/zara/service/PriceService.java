package com.between.zara.service;

import com.between.zara.model.PriceRequest;
import com.between.zara.model.PriceResponse;

import java.util.Optional;

public interface PriceService {
    Optional<PriceResponse> getPricesByDate(PriceRequest request);
}
