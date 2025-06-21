package Testing.NegativeTestCase;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
import PagesTesting.itemSearch;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})
public class invalidProductSearch {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String testDatafile = "TestData";
    private String invSearchitem = "InvSearchableItem";
    private final String validationMsg = DataUtility.GetJsonDataFromFile(testDatafile,"ValidationMSG");
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String ecommercePortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private String invSearchedItem = DataUtility.GetJsonDataFromFile(testDatafile,invSearchitem);

    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    void setUp(){

        try {

            DriverFactory.SetupThreadDriver(Browser);
            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            DriverFactory.GetThreadDriver().get(ecommercePortal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void invaliSearchdProduct(){

        String actualResult = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(invSearchedItem)
                .clickOnSearchBtn()
                .getValidationMSG();

        AssertUtility.assertEqual(softAssert,actualResult.trim(),validationMsg);

    }

    @AfterMethod
    void Quit(){
        DriverFactory.QuitThreadDriver();
    }
}
