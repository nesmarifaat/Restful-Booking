package testcases;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC04_DeleteBooking extends TestBase {

    @Test(priority = 1, description = "Delete Booking with Valid Booking ID and Data")

    public void deletebookingwithvaliddata_P() {

       Response response= given().log().all().filter(new AllureRestAssured())
                .cookie("token", token)
                .contentType(ContentType.JSON)
                .when().delete("/booking/" + BookingID)
                .then().log().all().statusCode(201).extract().response();
        Assert.assertTrue(response.body().print().contains("Created"));
    }

    @Test(priority = 1, description = " Delete Booking with Invalid Path")

    public void deletebookingwithinvalidpath_N(){
        Response response= given().log().all().filter(new AllureRestAssured())
                .cookie("token", token)
                .contentType(ContentType.JSON)
                .when().delete("/book/" + BookingID)
                .then().log().all().statusCode(404).extract().response();
        Assert.assertTrue(response.body().print().contains("Not Found"));


    }
}
