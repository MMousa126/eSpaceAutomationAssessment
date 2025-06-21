package Testing;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import PagesTesting.itemSearch;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})


// Verify that the user is able to search for item (ID: #12551)
public class itemSearchTC {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String testDatafile = "TestData";
    private String searchitem = "searchedProduct";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String ecommercePortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private String searchedItem = DataUtility.GetJsonDataFromFile(testDatafile,searchitem);

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
    public void searchItem(){

        String actualResult = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .getTitleOfSearchedItem();

        AssertUtility.assertEqual(softAssert,actualResult.toLowerCase(),searchedItem.toLowerCase());

    }

    @AfterMethod
    void Quit(){
        DriverFactory.QuitThreadDriver();
    }
}
