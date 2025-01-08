package testcases;

import com.github.javafaker.Faker;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.DateFormatSymbols;

import static Utilities.Utility.NameGenerator.generateRandomFirstName;

import static Utilities.Utility.RandomNumberGenerator.generateRandomInteger;
import static io.restassured.RestAssured.given;

public class TC03_UpdateBooking extends TestBase {
    String Lastname = faker.name().lastName();
    String Firstname = faker.name().firstName();



    @Test(priority = 1, description = "Update All Booking With Valid Data")
    public void updateallbooking_P() {
        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("firstname", generateRandomFirstName())
                .put("lastname", Lastname)
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .cookie("token", token)
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().put("/booking/" + BookingID)
                .then().log().all().statusCode(200).extract().response();
        Assert.assertTrue(response.jsonPath().get("firstname") instanceof String);
        Assert.assertTrue(response.jsonPath().get("lastname") instanceof String);
        Assert.assertTrue(response.jsonPath().get("totalprice") instanceof Integer);
        Assert.assertTrue(response.jsonPath().get("depositpaid") instanceof Boolean);
        Assert.assertTrue(response.jsonPath().get("bookingdates") instanceof Object);
        Assert.assertTrue(response.jsonPath().get("bookingdates.checkin") instanceof String);
        Assert.assertTrue(response.jsonPath().get("bookingdates.checkout") instanceof String);


        System.out.println("Body Response " + response);
    }

    @Test(priority = 2, description = "Partial Update for Fistname and Lastname")
    public void partialupdateforfirstlastname_P() {

        JSONObject BookingData = new JSONObject();
        BookingData.put("firstname",Firstname)
                .put("lastname", Lastname);

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .cookie("token", token)
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().patch("/booking/" + BookingID)
                .then().log().all().statusCode(200).extract().response();
        Assert.assertTrue(response.jsonPath().get("firstname") instanceof String);
        Assert.assertTrue(response.jsonPath().get("lastname") instanceof String);
    }

    @Test(priority = 3, description = "Update Booking Using Invalid Method")

    public void updatewithinvalidmethod_N() {
        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("firstname", generateRandomFirstName())
                .put("lastname", Lastname)
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .cookie("token", token)
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().post("/booking/" + BookingID)
                .then().log().all().statusCode(404).extract().response();
        Assert.assertTrue(response.body().print().contains("Not Found"));
    }

    @Test(priority = 4, description = "Update Booking With Invalid Path")
    public void updatebookingwithinvalidpath_N() {
        JSONObject BookingData = new JSONObject();
        JSONObject BookingDates = new JSONObject();
        BookingData.put("firstname", generateRandomFirstName())
                .put("lastname", Lastname)
                .put("totalprice", generateRandomInteger())
                .put("depositpaid", true)
                .put("additionalneeds", "breakfast")
                .put("bookingdates", BookingDates);
        BookingDates.put("checkin", "1-1-2025")
                .put("checkout", "20-12-2025");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .cookie("token", token)
                .contentType(ContentType.JSON).body(BookingData.toString())
                .when().put("/book/" + BookingID)
                .then().log().all().statusCode(404).extract().response();
        Assert.assertTrue(response.body().print().contains("Not Found"));
    }


}
