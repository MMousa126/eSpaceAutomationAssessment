package Testing;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import Pages.P01Login;
import Pages.P02HomePage;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;

import static Factory.DriverFactory.GetThreadDriver;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})
public class T01Login {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portalFromEnv = "Portal";
    private final String expectedHomePage = "HomePageURL";


    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String portal = DataUtility.GetPropertiesDataFromFile(browser_filename,portalFromEnv);
    private final String homePage = DataUtility.GetPropertiesDataFromFile(browser_filename,expectedHomePage);

    private final SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    void setUp(){

        try {
            DriverFactory.SetupThreadDriver(Browser);
            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            DriverFactory.GetThreadDriver().get(portal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");
        }catch (Exception e){
            LogsUtility.LoggerError("Invalid URL");
            e.printStackTrace();
        }

    }

    @Test
    void login(){
        new P01Login(GetThreadDriver())
                .enterAuthCrediential("Admin","admin123")
                .clickOnLogin();

        String actualHomePageURL = new P02HomePage(GetThreadDriver()).getHomePageURL();
        AssertUtility.assertEqual(softAssert,actualHomePageURL,homePage);
    }

    @AfterMethod
    void quitBrowser(){
        DriverFactory.QuitThreadDriver();
    }
}
