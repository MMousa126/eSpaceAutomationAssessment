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

import static Factory.DriverFactory.GetThreadDriver;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})

public class T02Sidemenu {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portalFromEnv = "Portal";
    private final String dataFile = "TestData";
    private final String toastMSG = "AlertMSG";
    private final String userName = "UserName";
    private final String password = "Password";
    private final String noOfResult = "NoOfResult";
    private final String statusValue = "Status";


    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String portal = DataUtility.GetPropertiesDataFromFile(browser_filename,portalFromEnv);
    private final String toastMessage = DataUtility.GetJsonDataFromFile(dataFile,toastMSG);
    private final String userNameData = DataUtility.GetJsonDataFromFile(dataFile,userName);
    private final String passwordData = DataUtility.GetJsonDataFromFile(dataFile,password);
    private final String getNoOfResult = DataUtility.GetJsonDataFromFile(dataFile,noOfResult);
    private final String getStatusValue = DataUtility.GetJsonDataFromFile(dataFile,statusValue);

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

    @Test(priority = 1)
    void searchForAdmin(){
        new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData,passwordData)
                .clickOnLogin()
                .searchInSidemenu(userNameData);
    }

    @Test(priority = 2)
    void clickOnAdminUsers(){
        new P01Login(GetThreadDriver())
            .enterAuthCrediential(userNameData,passwordData)
            .clickOnLogin()
            .searchInSidemenu(userNameData)
            .clickOnAdminUsers();
    }

    @AfterMethod
    void quitBrowser(){
        DriverFactory.QuitThreadDriver();
    }
}
