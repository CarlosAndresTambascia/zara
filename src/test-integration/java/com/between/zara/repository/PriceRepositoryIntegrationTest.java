package com.between.zara.repository;

import com.between.zara.bootstrap.DataInitializer;
import com.between.zara.model.Price;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackageClasses = DataInitializer.class)
class PriceRepositoryIntegrationTest {
    @Autowired
    private PriceRepository priceRepository;

    /**
     * - Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     * - Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     * - Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     * - Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     * - Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */

    private static Object[][] foundPriceScenarios() {
        return new Object[][]{
                {35455L, 1L, createDate("2020-06-14-10.00.00")},
                {35455L, 1L, createDate("2020-06-14-16.00.00")},
                {35455L, 1L, createDate("2020-06-14-21.00.00")},
                {35455L, 1L, createDate("2020-06-15-10.00.00")},
                {35455L, 1L, createDate("2020-06-16-21.00.00")}
        };
    }

    @ParameterizedTest
    @MethodSource("foundPriceScenarios")
    void findPriceByDateBrandAndProductIdRetrievesExpected(long productId, long brandId, Date date) {
        //when
        final List<Price> prices = priceRepository.searchPriceForSpecificDate(productId, brandId, date);
        //then
        assertThat(prices).isNotEmpty();
    }

    private static Object[][] overlappingScenarios() {
        return new Object[][]{
                {35455L, 1L, createDate("2020-06-14-16.00.00")},
                {35455L, 1L, createDate("2020-06-15-10.00.00")},
                {35455L, 1L, createDate("2020-06-16-21.00.00")}
        };
    }

    @ParameterizedTest
    @MethodSource("overlappingScenarios")
    void findPriceByDateBrandAndProductIdShouldBeSortedByPriority(long productId, long brandId, Date date) {
        //when
        final List<Price> prices = priceRepository.searchPriceForSpecificDate(productId, brandId, date);
        //then
        assertThat(prices).hasSize(2);
        assertThat(prices.stream().findFirst().map(Price::getPriority)).contains(1);
    }

    private static Object[][] notFoundPriceScenarios() {
        return new Object[][]{
                {15455L, 1L, createDate("2020-06-14-10.00.00")},
                {35455L, 2L, createDate("2020-06-14-16.00.00")},
                {35455L, 1L, createDate("2022-06-14-21.00.00")},
        };
    }

    @ParameterizedTest
    @MethodSource("notFoundPriceScenarios")
    void findPriceByDateBrandAndProductIdRetrievesEmptyListIfMissing(long productId, long brandId, Date date) {
        //when
        final List<Price> prices = priceRepository.searchPriceForSpecificDate(productId, brandId, date);
        //then
        assertThat(prices).isEmpty();
    }

    @SneakyThrows
    private static Date createDate(String date) {
        return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(date);
    }
}