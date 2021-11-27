package com.between.zara.bootstrap;

import com.between.zara.model.Brand;
import com.between.zara.model.Price;
import com.between.zara.repository.BrandRepository;
import com.between.zara.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.between.zara.model.Currency.EUR;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PriceRepository priceRepository;
    private final BrandRepository brandRepository;

    @Override
    public void run(String... args) {
        brandRepository.save(new Brand(null, "Zara"));
        final Brand brand = brandRepository.findById(1L).orElse(null);
        final Price price1 = Price.builder()
                .brand(brand)
                .startDate(getDateFromString("2020-06-14-00.00.00"))
                .endDate(getDateFromString("2020-12-31-23.59.59"))
                .priceList(1L)
                .productId(35455L)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
        final Price price2 = Price.builder()
                .brand(brand)
                .startDate(getDateFromString("2020-06-14-15.00.00"))
                .endDate(getDateFromString("2020-06-14-18.30.00"))
                .priceList(2L)
                .productId(35455L)
                .priority(1)
                .price(BigDecimal.valueOf(25.45))
                .currency(EUR)
                .build();
        final Price price3 = Price.builder()
                .brand(brand)
                .startDate(getDateFromString("2020-06-15-00.00.00"))
                .endDate(getDateFromString("2020-06-15-11.00.00"))
                .priceList(3L)
                .productId(35455L)
                .priority(1)
                .price(BigDecimal.valueOf(30.50))
                .currency(EUR)
                .build();
        final Price price4 = Price.builder()
                .brand(brand)
                .startDate(getDateFromString("2020-06-15-16.00.00"))
                .endDate(getDateFromString("2020-12-31-23.59.59"))
                .priceList(4L)
                .productId(35455L)
                .priority(1)
                .price(BigDecimal.valueOf(38.95))
                .currency(EUR)
                .build();
        priceRepository.save(price1);
        priceRepository.save(price2);
        priceRepository.save(price3);
        priceRepository.save(price4);
    }

    @SneakyThrows
    private Date getDateFromString(String date) {
        return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(date);
    }
}
