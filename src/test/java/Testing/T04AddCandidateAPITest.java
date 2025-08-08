package Testing;

import Factory.DriverFactory;
import Pages.P01Login;
import Pages.P04AddCandidateApI;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import Utilities.Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

import static Factory.DriverFactory.GetThreadDriver;
import static io.restassured.RestAssured.given;

public class T04AddCandidateAPITest {
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

    @BeforeClass
    void login(){
        DriverFactory.SetupThreadDriver(Browser);
        LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
        DriverFactory.GetThreadDriver().get(portal);
        LogsUtility.LoggerInfo("Page is Redirected to the URL");

            new P01Login(GetThreadDriver())
                    .enterAuthCrediential(userNameData, passwordData)
                    .clickOnLogin();

        cookies = Utility.GetAllCookies(GetThreadDriver());
        DriverFactory.QuitThreadDriver();

    }
    @BeforeMethod
    void setUp() {

        try {
            DriverFactory.SetupThreadDriver(Browser);
            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            DriverFactory.GetThreadDriver().get(portal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");
            Utility.InjectCookies(GetThreadDriver(), cookies);

            GetThreadDriver().get(homePage);
            GetThreadDriver().navigate().refresh();

        } catch (Exception e) {
            LogsUtility.LoggerError("Invalid URL");
            e.printStackTrace();
        }

    }
    @Test
    public void addCandidateApiTest() {

        JSONObject body = new JSONObject();
        body.put("firstName", "Mohamed");
        body.put("middleName", "AbdElhamid");
        body.put("lastName", "Mousa");
        body.put("email", "mohamed.abdelhaimd.ebrahim@gamil.com");
        body.put("contactNumber", JSONObject.NULL);
        body.put("keywords", JSONObject.NULL);
        body.put("comment", JSONObject.NULL);
        body.put("dateOfApplication", Utility.TodayGeorgianDate());
        body.put("consentToKeepData", false);

        String jsonBody = body.toString();

        String actualStatusCode =  new P04AddCandidateApI()
                .getStatusCode(getAddCandidateEndpoin,getConfigAPI
                        ,jsonBody);

        Assert.assertEquals(actualStatusCode,"401");
    }

    @AfterClass
    void quit(){
        DriverFactory.QuitThreadDriver();
    }
}
