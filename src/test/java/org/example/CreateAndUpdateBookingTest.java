package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAndUpdateBookingTest extends  BookerBase
{
  @Test
   public void createBookingTest()
   {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("firstname", "Naeem");
    jsonObject.put("lastname", "Mal");
    jsonObject.put("totalprice", 111);
    jsonObject.put("depositpaid", true);
    jsonObject.put("additionalneeds", "Baby Chair");
    JSONObject bookingDates = new JSONObject();
    bookingDates.put("checkin", "2022-02-01");
    bookingDates.put("checkout", "2022-02-03");
    jsonObject.put("bookingdates", bookingDates);

    Response responseCreate = createNewBooking(jsonObject);
    responseCreate.prettyPrint();
    Assert.assertEquals(responseCreate.getStatusCode(), 200, "Response is not 200.");   }
    @Test
    public void updateBookingByPutTest()
    {
        JSONObject passJSON = new JSONObject();
        passJSON.put("username",  "admin");
        passJSON.put( "password", "password123");

        Response tokenResponse = RestAssured.given().contentType("application/json").body(passJSON.toString()).post(" https://restful-booker.herokuapp.com/auth");

        tokenResponse.prettyPrint();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Naeem");
        jsonObject.put("lastname", "Mal");
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

        JSONObject jsonObjectUpdate = new JSONObject();
        jsonObjectUpdate.put("firstname", "Naeem");
        jsonObjectUpdate.put("lastname", "Mal");
        jsonObjectUpdate.put("totalprice", 111);
        jsonObjectUpdate.put("depositpaid", false);
        jsonObjectUpdate.put("additionalneeds", "Baby Chair");
        JSONObject bookingDates2 = new JSONObject();
        bookingDates2.put("checkin", "2022-02-02");
        bookingDates2.put("checkout", "2022-02-04");
        jsonObjectUpdate.put("bookingdates", bookingDates);

        System.out.println(String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
        Response responseUpdate = RestAssured.given().
                auth().preemptive().basic("admin", "password123").
                        contentType(ContentType.JSON).body(jsonObjectUpdate.toString()).put( String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
        responseUpdate.prettyPrint();

        // verify response is 200
        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response is not 200.");
    }

 @Test
 public void updateBookingByPatchTest()
 {
  JSONObject jsonObject = new JSONObject();
  jsonObject.put("firstname", "Naeem");
  jsonObject.put("lastname", "Mal");
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

  JSONObject jsonObjectUpdate = new JSONObject();
  jsonObjectUpdate.put("lastname", "Mlx");
  jsonObjectUpdate.put("depositpaid", false);

  System.out.println(String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
  Response responseUpdate = RestAssured.given().
          auth().preemptive().basic("admin", "password123").
          contentType(ContentType.JSON).body(jsonObjectUpdate.toString()).patch( String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
  responseUpdate.prettyPrint();

  // verify response is 200
  Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response is not 200.");
 }
}
