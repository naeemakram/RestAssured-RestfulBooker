package com.testautomationtv;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestFulBooker
{
    @Test
    public void healthCheckTest()
    {
        //BDD style test
        given().when().get("https://restful-booker.herokuapp.com/ping").
                    then().
                    assertThat().statusCode(201);
    }
}
