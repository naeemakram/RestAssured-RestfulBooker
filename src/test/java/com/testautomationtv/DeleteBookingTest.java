package com.testautomationtv;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends  BookerBase
{
 @Test
 public void deleteBookingTest()
 {
  JSONObject jsonObject = new JSONObject();
  jsonObject.put("firstname", "Naeem");
  jsonObject.put("lastname", "Malik-D");
  jsonObject.put("totalprice", 111);
  jsonObject.put("depositpaid", true);
  jsonObject.put("additionalneeds", "Baby Chair");
  JSONObject bookingDates = new JSONObject();
  bookingDates.put("checkin", "2022-02-01");
  bookingDates.put("checkout", "2022-02-03");
  jsonObject.put("bookingdates", bookingDates);

  Response responseCreate = createNewBooking(jsonObject);
  responseCreate.prettyPrint();
  int newID = responseCreate.jsonPath().getInt("bookingid");

  Response responseDelete = RestAssured.given().
          auth().preemptive().basic(
                  "admin", "password123").
          delete(
                  String.format(
                          "https://restful-booker.herokuapp.com/booking/%s", newID));

  // verify response is 201
  Assert.assertEquals(responseDelete.getStatusCode(), 201, "Response is not 200.");

  Response responseAfterDelete = RestAssured.get(String.format(
          "https://restful-booker.herokuapp.com/booking/%s", newID));

  System.out.print(responseAfterDelete.body().asString());

  Assert.assertEquals(responseAfterDelete.body().asString(), "Not Found", "Object not deleted.");
 }
}
