package Testing;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import PagesTesting.P01Login;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

public class T05AddingCompany {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landing = "landingPage";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String landingPage = DataUtility.GetPropertiesDataFromFile(browser_filename,landing);

    SoftAssert softAssert = new SoftAssert();
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

    @Test(priority = 1)
    void addingCompany(){

        String companyName = "Automated Level 1" + " "+ DataFaker.fakerApp()
                +DataFaker.generateRandomListOfNumbers(10,20,1);
        String comRegNo = DataFaker.generateRandomListOfNumbers(0,10,10);
        String inboxurl = DataUtility.GetPropertiesDataFromFile(browser_filename,"inboxURL") ;
        String url = new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .sysSettings()
                .clickOnCompany()
                .clickOnAddingCompany()
                .enterCompanyData(companyName,comRegNo)
                .availableIncoming("نعم")
                .clickOnSave()
                .clickOnMyInbox()
                .getCurrentURL();

        new AssertUtility(softAssert)
                .assertEqual(url,inboxurl);

    }

    @AfterMethod
    void quit()
    {
        DriverFactory.QuitThreadDriver();
    }
}
