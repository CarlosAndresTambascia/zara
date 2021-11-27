package com.between.zara.fixture;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

public class FixtureLoader {
    public static String getPricesInboundRq() {
        return readFileFromClassPath("fixtures/prices/inboundRq.json");
    }

    public static String getPricesOutboundRs() {
        return readFileFromClassPath("fixtures/prices/outboundRs.json");
    }

    public static String getPricesInboundRqNotFound() {
        return readFileFromClassPath("fixtures/prices/inboundRqNotFound.json");
    }

    @SneakyThrows
    private static String readFileFromClassPath(String filePath) {
        return Files.readString(ResourceUtils.getFile("classpath:" + filePath).toPath());
    }
}
