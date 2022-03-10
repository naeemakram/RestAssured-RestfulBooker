package org.example;

import org.testng.annotations.Test;
import io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;

/**
 * Unit test for simple App.
 */
public class RestFulBooker
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void healthCheckTest()
    {
        given().when().get("https://restful-booker.herokuapp.com/ping").
                    then().
                    assertThat().statusCode(201);
    }
}
