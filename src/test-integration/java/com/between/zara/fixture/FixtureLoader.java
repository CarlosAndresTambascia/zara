package com.between.zara.fixture;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

public class FixtureLoader {
    public static String getPricesInboundRq() {
        return readFileFromClassPath("fixtures/prices/success/inboundRq.json");
    }

    public static String getPricesOutboundRs() {
        return readFileFromClassPath("fixtures/prices/success/outboundRs.json");
    }

    public static String getPricesInboundRqNotFound() {
        return readFileFromClassPath("fixtures/prices/failure/inboundRqNotFound.json");
    }

    public static String getInboundRqInvalidApplicationDate() {
        return readFileFromClassPath("fixtures/prices/failure/inboundRqInvalidApplicationDate.json");
    }

    public static String getInboundRqInvalidBrandId() {
        return readFileFromClassPath("fixtures/prices/failure/inboundRqInvalidBrandId.json");
    }

    public static String getInboundRqInvalidProduct() {
        return readFileFromClassPath("fixtures/prices/failure/inboundRqInvalidProduct.json");
    }

    public static String getOutboundRsInvalidApplicationDate() {
        return readFileFromClassPath("fixtures/prices/failure/outboundRsInvalidApplicationDate.json");
    }

    public static String getOutboundRsInvalidBrandId() {
        return readFileFromClassPath("fixtures/prices/failure/outboundRsInvalidBrandId.json");
    }

    public static String getOutboundRsInvalidProductId() {
        return readFileFromClassPath("fixtures/prices/failure/outboundRsInvalidProductId.json");
    }

    @SneakyThrows
    private static String readFileFromClassPath(String filePath) {
        return Files.readString(ResourceUtils.getFile("classpath:" + filePath).toPath());
    }
}
