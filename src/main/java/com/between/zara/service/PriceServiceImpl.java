package com.between.zara.service;

import com.between.zara.model.Price;
import com.between.zara.model.PriceResponse;
import com.between.zara.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public Optional<PriceResponse> getPricesByDate(Long productId, Long brandId, Date applicationDate) {
        final List<Price> prices = priceRepository.searchPriceForSpecificDate(productId, brandId, applicationDate);
        return prices.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(this::createPriceResponse);
    }

    private PriceResponse createPriceResponse(Price price) {
        return PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrand().getId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .build();
    }
}
