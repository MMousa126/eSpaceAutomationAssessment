package Testing;

import Factory.DriverFactory;
import Pages.P01Login;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import Utilities.Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

import static Factory.DriverFactory.GetThreadDriver;
import static io.restassured.RestAssured.given;

public class T04SuccessfulAddCandiditeUsingRestAssured {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portalFromEnv = "Portal";
    private final String dataFile = "TestData";
    private final String toastMSG = "AlertMSG";
    private final String userName = "UserName";
    private final String password = "Password";
    private final String configAPI = "APIConfig";
    private final String candidateEndpoint = "addCandiditeEndPoint";


    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename, browser);
    private final String portal = DataUtility.GetPropertiesDataFromFile(browser_filename, portalFromEnv);
    private final String getAddCandidateEndpoin = DataUtility.GetPropertiesDataFromFile(browser_filename, candidateEndpoint);
    private final String getConfigAPI = DataUtility.GetJsonDataFromFile(dataFile, configAPI);
    private final String userNameData = DataUtility.GetJsonDataFromFile(dataFile, userName);
    private final String passwordData = DataUtility.GetJsonDataFromFile(dataFile, password);


    private final String expectedHomePage = "HomePageURL";

    private final String homePage = DataUtility.GetPropertiesDataFromFile(browser_filename,expectedHomePage);
    private final SoftAssert softAssert = new SoftAssert();
    Set<Cookie> cookies;
    String sessionId;
    @BeforeClass
    void login(){
        DriverFactory.SetupThreadDriver(Browser);
        LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
        DriverFactory.GetThreadDriver().get(portal);
        LogsUtility.LoggerInfo("Page is Redirected to the URL");

        new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData, passwordData)
                .clickOnLogin();

        // session identifier, and I can use it in Rest Assured just like a token.
        cookies = Utility.GetAllCookies(GetThreadDriver());
        sessionId = GetThreadDriver().manage().getCookieNamed("orangehrm").getValue();
        DriverFactory.QuitThreadDriver();
    }
    @BeforeMethod
    void setUp() {

        try {
            DriverFactory.SetupThreadDriver(Browser);
            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            DriverFactory.GetThreadDriver().get(portal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");

        } catch (Exception e) {
            LogsUtility.LoggerError("Invalid URL");
            e.printStackTrace();
        }

    }
    @Test
    public void addCandidateApiTest() {

        JSONObject body = new JSONObject();
        body.put("firstName", DataFaker.fakerFirstName());
        body.put("middleName", DataFaker.fakerFirstName());
        body.put("lastName", DataFaker.fakerLastName());
        body.put("email", DataFaker.fakeEmail());
        body.put("contactNumber", JSONObject.NULL);
        body.put("keywords", JSONObject.NULL);
        body.put("comment", JSONObject.NULL);
        body.put("dateOfApplication", Utility.TodayGeorgianDate());
        body.put("consentToKeepData", false);

        String jsonBody = body.toString();

        Response respond = RestAssured.given()
                .contentType("application/json")
                .cookie("orangehrm", sessionId) // Using session id as token
                .body(jsonBody)
                .when()
                .post("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/recruitment/candidates")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(respond.asPrettyString());
        String statusCode = String.valueOf(respond.getStatusCode());

        Assert.assertEquals(statusCode,"200");

    }

    @AfterClass
    void quit(){
        DriverFactory.QuitThreadDriver();
    }
}
