package com.between.zara.service;

import com.between.zara.model.Brand;
import com.between.zara.model.Price;
import com.between.zara.model.PriceResponse;
import com.between.zara.repository.PriceRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.between.zara.model.Currency.EUR;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {
    private static final long PRODUCT_ID = 35455L;
    private static final long BRAND_ID = 1L;
    private static final Date APPLICATION_DATE = createApplicationDate("2020-06-15-16.00.00");
    private static final Date START_DATE = createApplicationDate("2020-06-14-00.00.00");
    private static final Date END_DATE = createApplicationDate("2020-12-31-23.59.59");
    private static final long PRICE_LIST = 1L;
    private static final long PRODUCT_ID1 = 35455L;
    private static final int PRIORITY = 0;
    private static final BigDecimal PRICE = BigDecimal.valueOf(35.50);
    private static final String BRAND_NAME = "Zara";

    @Mock
    private PriceRepository priceRepository;

    private PriceServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PriceServiceImpl(priceRepository);
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void retrievesPopulatedPriceResponseIfPriceFound() {
        //given
        when(priceRepository.searchPriceForSpecificDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE)).thenReturn(List.of(createPrice()));
        //when
        final Optional<PriceResponse> prices = service.getPricesByDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE);
        //then
        assertThat(prices.get()).satisfies(price -> {
                    assertThat(price.getPrice()).isEqualTo(PRICE);
                    assertThat(price.getPriceList()).isEqualTo(PRICE_LIST);
                    assertThat(price.getEndDate()).isEqualTo(END_DATE);
                    assertThat(price.getStartDate()).isEqualTo(START_DATE);
                }
        );
        verify(priceRepository).searchPriceForSpecificDate(eq(PRODUCT_ID), eq(BRAND_ID), eq(APPLICATION_DATE));
    }

    @Test
    void retrievesEmptyOptionalIfPriceNotFound() {
        //given
        when(priceRepository.searchPriceForSpecificDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE)).thenReturn(emptyList());
        //when
        final Optional<PriceResponse> prices = service.getPricesByDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE);
        //then
        assertThat(prices).isEmpty();
        verify(priceRepository).searchPriceForSpecificDate(eq(PRODUCT_ID), eq(BRAND_ID), eq(APPLICATION_DATE));
    }

    private Price createPrice() {
        return Price.builder()
                .brand(new Brand(null, BRAND_NAME))
                .startDate(START_DATE)
                .endDate(END_DATE)
                .priceList(PRICE_LIST)
                .productId(PRODUCT_ID1)
                .priority(PRIORITY)
                .price(PRICE)
                .currency(EUR)
                .build();
    }

    @SneakyThrows
    private static Date createApplicationDate(String source) {
        return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(source);
    }
}