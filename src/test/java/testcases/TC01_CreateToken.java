package testcases;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC01_CreateToken extends TestBase {

    //TODO: postive scenario
    //Given >> Request Data
    //When Action method post, get put
    //Then >> Assertion

    @Test(priority = 1, description = "Create Token with valid Data")

    public void createtokenwithvaliddata_P() {
        JSONObject logindata = new JSONObject();
        logindata.put("username", "admin").put("password", "password123");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(logindata.toString())
                .when().post("/auth")
                .then().log().all().statusCode(200).extract().response();
        token = response.jsonPath().getString("token");
        Assert.assertTrue(token.length() >= 7);
        Assert.assertFalse(checknull(response));
        Assert.assertFalse(token.isBlank());


        System.out.println("Token Value is" + token);
    }

    private boolean checknull(Response response) {
        return response.jsonPath().getString("token").isEmpty();

    }


    @Test(priority = 2, description = "Create Token With invalid Action method")
    public void createtokenwithinvalidmethod() {
        JSONObject logindata = new JSONObject();
        logindata.put("username", "admin").put("password", "password123");
        Response response = given().log().all().filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(logindata.toString())
                .when().get("/auth")
                .then().log().all().statusCode(404).extract().response();
        System.out.println("Response is" + response);
    }


    @Test(priority = 3, description = "Create Token with invalid credentials")

    public void createtokenwithinvalidcredentials_N() {
        JSONObject logindata = new JSONObject();
        logindata.put("username", "admin123").put("password", "password123");
        Response response = given().log().all().filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(logindata.toString())
                .when().post("/auth")
                .then().log().all().statusCode(200).extract().response();
        Assert.assertTrue(response.jsonPath().getString("reason").equals("Bad credentials"));
    }


    @Test(priority = 4, description = "Create Token with Invalid Path")

    public void createtokenwithinvalidpath_N() {

        JSONObject logindata = new JSONObject();
        logindata.put("username", "admin").put("password", "password123");

        Response response = given().log().all()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(logindata.toString())
                .when().post("/aut")
                .then().log().all().statusCode(404).extract().response();
            // Assert.assertFalse(response.body().print().isEmpty());
             Assert.assertTrue(response.body().print().matches("Not Found"));



    }
}
