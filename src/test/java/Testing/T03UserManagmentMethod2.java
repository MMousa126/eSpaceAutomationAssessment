package Testing;

import Factory.DriverFactory;
import Pages.P01Login;
import Pages.P03UserManagment;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})

public class T03UserManagmentMethod2 {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portalFromEnv = "Portal";
    private final String dataFile = "TestData";
    private final String toastMSG = "AlertMSG";
    private final String userName = "UserName";
    private final String password = "Password";
    private final String noOfResult = "NoOfResult";
    private final String statusValue = "Status";


    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename, browser);
    private final String portal = DataUtility.GetPropertiesDataFromFile(browser_filename, portalFromEnv);
    private final String toastMessage = DataUtility.GetJsonDataFromFile(dataFile, toastMSG);
    private final String userNameData = DataUtility.GetJsonDataFromFile(dataFile, userName);
    private final String passwordData = DataUtility.GetJsonDataFromFile(dataFile, password);
    private final String getNoOfResult = DataUtility.GetJsonDataFromFile(dataFile, noOfResult);
    private final String getStatusValue = DataUtility.GetJsonDataFromFile(dataFile, statusValue);

    private final SoftAssert softAssert = new SoftAssert();


    @BeforeClass
    void setUp() {

        try {
            DriverFactory.SetupThreadDriver(Browser);
            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            DriverFactory.GetThreadDriver().get(portal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");


            new P01Login(GetThreadDriver())
                    .enterAuthCrediential(userNameData, passwordData)
                    .clickOnLogin()
                    .searchInSidemenu(userNameData)
                    .clickOnAdminUsers()
                    .enterSearchUser(userNameData)
                    .clickOnSearchBtn()
            ;

        } catch (Exception e) {
            LogsUtility.LoggerError("Invalid URL");
            e.printStackTrace();
        }

    }

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the number of results
    @Test(priority = 1)
    void clickOnAdminUsersM2() {
        String actualNoOfResults = new P03UserManagment(GetThreadDriver())
                .getNoOfResults();

        Assert.assertEquals(actualNoOfResults, getNoOfResult);

    }

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the username is correct
    @Test(priority = 2)
    void getUserNameM2() {
        String actualUserName = new P03UserManagment(GetThreadDriver())
                .getUsername();

        Assert.assertEquals(actualUserName, userNameData);

    }

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the User Role is correct
    @Test(priority = 3)
    void getUserRoleM2() {
        String actualUserRole = new P03UserManagment(GetThreadDriver())
                .getUserRole();

        Assert.assertEquals(actualUserRole, userNameData);

    }

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the Status is correct
    @Test(priority = 4)
    void getStatusM2() {
        String actualStatus = new P03UserManagment(GetThreadDriver())
                .getStatus();

        Assert.assertEquals(actualStatus, getStatusValue);

    }


    // TODO: 6. Click the Delete icon (trash bin) on the table
    // TODO: 7. Assert that the admin user deletion is not allowed (either nothing happens, or an error appears)
    @Test(priority = 5)
    void deleteAdminM2() {

        new P03UserManagment(GetThreadDriver())
                .clickOnDelete();

        String alart = new P03UserManagment(GetThreadDriver())
                .getAlertText();
        Assert.assertEquals(alart, toastMessage);
    }

    @AfterClass
    void quitBrowser() {
        DriverFactory.QuitThreadDriver();
    }

}

