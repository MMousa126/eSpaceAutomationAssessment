package PageTesting;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import PagesTesting.P01Login;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})

public class T01Login {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landing = "landingPage";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String landingPage = DataUtility.GetPropertiesDataFromFile(browser_filename,landing);

    @BeforeClass
    void setUp(){

        try {

            SetupThreadDriver(Browser);

            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            GetThreadDriver().get(nazahaPortal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Test
    void login(){

        new P01Login(DriverFactory.GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin();

        new AssertUtility(new SoftAssert())
                .assertEqual(GetThreadDriver().getCurrentUrl(),landingPage)
                .assertAll();



    }

    @AfterClass
    void quit()
    {
        DriverFactory.QuitThreadDriver();
    }

}
