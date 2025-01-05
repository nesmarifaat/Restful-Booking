package testcases;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class TestBase {
    static String token = null;
    static int BookingID=0;

    protected Faker faker=new Faker();

    @BeforeTest

    public void Setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}
