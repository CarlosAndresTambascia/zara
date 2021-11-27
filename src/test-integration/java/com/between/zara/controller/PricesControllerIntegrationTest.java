package com.between.zara.controller;

import com.between.zara.fixture.FixtureLoader;
import io.restassured.http.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static com.between.zara.fixture.FixtureLoader.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.apache.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("it")
@SpringBootTest
public class PricesControllerIntegrationTest {
    @Autowired
    private WebApplicationContext applicationContext;

    @Test
    void shouldRetrieveExpectedPriceIfPresent() {
        //given
        given()
                .webAppContextSetup(applicationContext)
                .contentType(APPLICATION_JSON_VALUE)
                .body(FixtureLoader.getPricesInboundRq())
        //when
        .when()
                .request(Method.GET, "/api/v1/prices")
        //then
        .then()
                .statusCode(SC_OK)
                .expect(result -> JSONAssert.assertEquals(result.getResponse().getContentAsString(),
                        FixtureLoader.getPricesOutboundRs(),
                        customComparator()));
    }

    @Test
    void shouldRetrieveNotFoundIfNotFoundPrice() {
        //given
        given()
                .webAppContextSetup(applicationContext)
                .contentType(APPLICATION_JSON_VALUE)
                .body(FixtureLoader.getPricesInboundRqNotFound())
        //when
        .when()
                .request(Method.GET, "/api/v1/prices")
        //then
        .then()
                .statusCode(SC_NOT_FOUND);
    }

    private static Object[][] invalidRqScenarios() {
        return new Object[][]{
                {getInboundRqInvalidApplicationDate(), getOutboundRsInvalidApplicationDate()},
                {getInboundRqInvalidBrandId(), getOutboundRsInvalidBrandId()},
                {getInboundRqInvalidProduct(), getOutboundRsInvalidProductId()}
        };
    }

    @ParameterizedTest
    @MethodSource("invalidRqScenarios")
    void shouldRetrieveValidationExceptionIfInvalidRequest(String request, String expectedResponse) {
        //given
        given()
                .webAppContextSetup(applicationContext)
                .contentType(APPLICATION_JSON_VALUE)
                .body(request)
        //when
        .when()
                .request(Method.GET, "/api/v1/prices")
        //then
        .then()
                .statusCode(SC_BAD_REQUEST)
                .expect(result -> JSONAssert.assertEquals(result.getResponse().getContentAsString(),
                        expectedResponse,
                        JSONCompareMode.LENIENT));
    }

    private CustomComparator customComparator() {
        return new CustomComparator(JSONCompareMode.LENIENT, new Customization("startDate", (o1, o2) -> true),
                new Customization("endDate", (o1, o2) -> true));
    }
}
