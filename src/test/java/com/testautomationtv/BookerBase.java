package com.testautomationtv;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BookerBase {

    protected RequestSpecification reqSpecs;

    @BeforeMethod
    public void beforeATest()
    {
        reqSpecs = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com/").build();
    }
    public Response createNewBooking(JSONObject inputUser) {
        Response response =
                RestAssured.given().
                        spec(reqSpecs).
                        contentType(ContentType.JSON).
                        body(inputUser.toString()).
                        post("/booking")                 ;
        return response;
    }
}
