package Testing;

import PagesTesting.P01Login;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import Factory.DriverFactory;
import Assertion.AssertUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

public class T12IncomingTransaction {


    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landing = "basicData_URL";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String basicDataURL = DataUtility.GetPropertiesDataFromFile(browser_filename,landing);
    private final String inboxurl = DataUtility.GetPropertiesDataFromFile(browser_filename,"inboxURL") ;


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
    void clickOnBasicData() {
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
                .clickOnBasicData()
                .getCurrentUrl()
        ;

        new AssertUtility(softAssert)
                .assertEqual(url,basicDataURL);
    }

    @BeforeMethod
    void Quit(){

//        DriverFactory.QuitThreadDriver();

    }
}
