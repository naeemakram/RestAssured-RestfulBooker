package com.testautomationtv;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckWithSpecs {
    @Test
    public void healthCheck()
    {
        RequestSpecification requestSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").build();

        given().
                spec(requestSpec).
        when().
                get("/ping").
        then().
                assertThat().
                statusCode(201);
    }
}
