package testcases;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static Utilities.Utility.*;


import static Utilities.Utility.NameGenerator.generateRandomFirstName;

import static Utilities.Utility.RandomNumberGenerator.generateRandomInteger;

import static io.restassured.RestAssured.given;

public class TC02_CreateBooking extends TestBase {

    String Firstname = faker.name().firstName();

    @Test(priority = 1, description = "Create booking with valid Data")
    public void createbookingwithvaliddata_P() {
        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("firstname", generateRandomFirstName())
                .put("lastname", Firstname)
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().post("/booking")
                .then().log().all().statusCode(200).extract().response();
        BookingID = response.jsonPath().getInt("bookingid");
        Assert.assertTrue(response.body().print().contains("bookingid"));
        Assert.assertNotNull("bookingid");
        Assert.assertNotNull("firstname");
        Assert.assertNotNull("lastname");

        System.out.println("Booking ID is " + BookingID);
    }

    @Test(priority = 2, description = "Create Booking With Invalid Method")
    public void createbookingwithinvalidmethod_N() {
        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("firstname", generateRandomFirstName())
                .put("lastname", "Rifaat")
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().put("/booking")
                .then().log().all().statusCode(404).extract().response();

    }

    @Test(priority = 3, description = "Create Booking with Invalid Payload")
    public void createbookingwithinvalidpayload_N() {
        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("F", generateRandomFirstName())
                .put("lastname", "Rifaat")
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().post("/booking")
                .then().log().all().statusCode(500).extract().response();

    }

    @Test(priority = 4, description = " Create Booking With Invalid Path")

    public void createbookingwithinvalidpath_N() {

        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("firstname", generateRandomFirstName())
                .put("lastname", Firstname)
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().post("/book")
                .then().log().all().statusCode(404).extract().response();
        Assert.assertTrue(response.getStatusLine().contains("Not Found"));
    }
}
