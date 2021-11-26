package com.between.zara;

import com.between.zara.fixture.FixtureLoader;
import io.restassured.http.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("it")
@SpringBootTest
public class PricesControllerIT {
    @Autowired
    private WebApplicationContext applicationContext;

    @Test
    void shouldRetrieveExpectedProductIfPresent() {
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
                        JSONCompareMode.LENIENT));

    }
}
