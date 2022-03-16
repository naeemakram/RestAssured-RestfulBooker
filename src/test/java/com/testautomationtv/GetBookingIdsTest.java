package com.testautomationtv;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Unit test for simple App.
 */
public class GetBookingIdsTest
{

    //TODO: This class is too big, should be refactored into smaller things.
    @Test
    public void getBookingIdsWihoutFilterTest()
    {
        // Not a BDD style test

        // get response
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

        // verify response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Response is not 200.");

        // there is atleast 1 booking ID in response
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking Ids is empty when it shouldn't be.");
    }

    @Test
    public void getBookingByIDTest()
    {
        // Not a BDD style test

        // get response
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");

        response.print();

        // verify response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Response is not 200.");

        // there is atleast 1 booking ID in response
        String name = response.jsonPath().get("firstname");
        String lastName = response.jsonPath().get("lastname");
        int totalPrice = response.jsonPath().getInt("totalprice");
        String checkin = response.jsonPath().getString("bookingdates.checkin");
        String checkout = response.jsonPath().getString("bookingdates.checkout");

        //Verify all fields
        //{"firstname":"Jim","lastname":"Ericsson","totalprice":175,"depositpaid":false,"bookingdates":{"checkin":"2016-09-30","checkout":"2018-01-10"},"additionalneeds":"Breakfast"}

        SoftAssert softAss = new SoftAssert();
        softAss.assertEquals(name, "Jim", "Name is not as expected.");
        softAss.assertEquals(lastName, "Ericsson", "Last name is not as expected.");
        softAss.assertEquals(totalPrice, 207, "Total price not as expected.");
        softAss.assertEquals(checkin, "2016-07-07", "Total price not as expected.");
        softAss.assertEquals(totalPrice, "2017-07-13", "Total price not as expected.");

        softAss.assertAll();
    }
}
