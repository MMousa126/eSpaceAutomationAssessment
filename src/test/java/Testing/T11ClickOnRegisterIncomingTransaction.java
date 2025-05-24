package Testing;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import PagesTesting.P01Login;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

public class T11ClickOnRegisterIncomingTransaction {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landingIncoming = "incomingTrans";
    private final String landingInterIncoming = "internalIncomingTrans";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String landingIncomingPage = DataUtility.GetPropertiesDataFromFile(browser_filename,landingIncoming);
    private final String landingInternalIncomingPage = DataUtility.GetPropertiesDataFromFile(browser_filename,landingInterIncoming) ;


    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod
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

    @Test(priority = 1)
    void clickOnIncomingTransaction() {
        String filePath = DataUtility.GetPropertiesDataFromFile("environment","AuthFile_Location");
        String userName = DataUtility.GetJsonDataFromFilePC(filePath,"Musername");
        String password = DataUtility.GetJsonDataFromFilePC(filePath,"Mpassword");
        String url = new P01Login(GetThreadDriver())
                .enterUsername(userName)
                .enterPassword(password)
                .clickOnLogin()
                .clickOnIncomingInbox()
                .selectDep1()
                .openDDLRegisterIncomingTrans()
                .clickOnRegIncomingTrans()
                .getCurrentUrl();

        new AssertUtility(softAssert)
                .assertEqual(url,landingIncomingPage);
    }

    @Test(priority = 2)
    void clickOnInternalIncomingTransaction() {
        String filePath = DataUtility.GetPropertiesDataFromFile("environment","AuthFile_Location");
        String userName = DataUtility.GetJsonDataFromFilePC(filePath,"Musername");
        String password = DataUtility.GetJsonDataFromFilePC(filePath,"Mpassword");
        String url = new P01Login(GetThreadDriver())
                .enterUsername(userName)
                .enterPassword(password)
                .clickOnLogin()
                .clickOnIncomingInbox()
                .selectDep1()
                .openDDLRegisterIncomingTrans()
                .clickOnRegInternalIncomingTrans()
                .getCurrentUrl();

        new AssertUtility(softAssert)
                .assertEqual(url,landingInternalIncomingPage);
    }

    @AfterMethod
    void quit()
    {
//        DriverFactory.QuitThreadDriver();
    }
    }
