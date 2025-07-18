package Testing;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import Pages.P01Login;
import Pages.P03UserManagment;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})
public class T03UserManagment {

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

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the number of results
    @Test(priority = 1)
    void clickOnAdminUsers(){
        new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData,passwordData)
                .clickOnLogin()
                .searchInSidemenu(userNameData)
                .clickOnAdminUsers()
                .enterSearchUser(userNameData)
                .clickOnSearchBtn()
                ;

        String actualNoOfResults = new P03UserManagment(GetThreadDriver())
                .getNoOfResults();

        AssertUtility.assertEqual(softAssert,actualNoOfResults,getNoOfResult);

    }

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the username is correct
    @Test(priority = 2)
    void getUserName(){
        new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData,passwordData)
                .clickOnLogin()
                .searchInSidemenu(userNameData)
                .clickOnAdminUsers()
                .enterSearchUser(userNameData)
                .clickOnSearchBtn();

        String actualUserName = new P03UserManagment(GetThreadDriver())
                .getUsername();

        AssertUtility.assertEqual(softAssert,actualUserName,userNameData);

    }

    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the User Role is correct
    @Test(priority = 3)
    void getUserRole(){
        new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData,passwordData)
                .clickOnLogin()
                .searchInSidemenu(userNameData)
                .clickOnAdminUsers()
                .enterSearchUser(userNameData)
                .clickOnSearchBtn();

        String actualUserRole = new P03UserManagment(GetThreadDriver())
                .getUserRole();

        AssertUtility.assertEqual(softAssert,actualUserRole,userNameData);

    }
    // TODO: 4. Search using the username for the admin user
    // TODO: 5. Assert that the Status is correct
    @Test(priority = 4)
    void getStatus(){
        new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData,passwordData)
                .clickOnLogin()
                .searchInSidemenu(userNameData)
                .clickOnAdminUsers()
                .enterSearchUser(userNameData)
                .clickOnSearchBtn()
        ;

        String actualStatus = new P03UserManagment(GetThreadDriver())
                .getStatus();

        AssertUtility.assertEqual(softAssert,actualStatus,getStatusValue);

    }


    // TODO: 6. Click the Delete icon (trash bin) on the table
    // TODO: 7. Assert that the admin user deletion is not allowed (either nothing happens, or an error appears)
    @Test (priority = 5)
    void deleteAdmin(){
        String alart = new P01Login(GetThreadDriver())
                .enterAuthCrediential(userNameData,passwordData)
                .clickOnLogin()
                .searchInSidemenu(userNameData)
                .clickOnAdminUsers()
                .enterSearchUser(userNameData)
                .clickOnSearchBtn()
                .clickOnDelete()
                .getAlertText()
                ;


        AssertUtility.assertEqual(softAssert,alart,toastMessage);
    }

    @AfterMethod
    void quitBrowser(){
        DriverFactory.QuitThreadDriver();
    }

}
