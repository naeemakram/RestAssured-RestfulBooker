package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BookerBase {

    public Response createNewBooking(JSONObject inputUser) {
        Response response =
                RestAssured.given().contentType(ContentType.JSON).body(inputUser.toString()).post("https://restful-booker.herokuapp.com/booking");
        return response;
    }
}
