package Testing.PositiveTestCases;

import Factory.DriverFactory;
import PagesTesting.itemPage;
import PagesTesting.itemSearch;
import PagesTesting.purchaseItemPage;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})


// Verify that the user is able to complete the purchase of the product (ID: #12553)
public class purchaseItemTC {
    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String testDatafile = "TestData";
    private String searchitem = "searchedProduct";

    private final String attributeSuccessUrl = "successURL";

    private final String hoddSize = "HoddieSize";
    private final String hoddColor = "HoddieColor";
    private final String hoddQuantity = "HoddiesQuentity";


    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String ecommercePortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private String searchedItem = DataUtility.GetJsonDataFromFile(testDatafile,searchitem);

    private final SoftAssert softAssert = new SoftAssert();

    private final String hoddieSize = DataUtility.GetJsonDataFromFile(testDatafile,hoddSize);
    private final String hoddieColor = DataUtility.GetJsonDataFromFile(testDatafile,hoddColor);
    private final String hoddieQuentity = DataUtility.GetJsonDataFromFile(testDatafile,hoddQuantity);


    private final String successUrl = DataUtility.GetPropertiesDataFromFile(browser_filename,attributeSuccessUrl);

    @BeforeClass
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
    void searchForItem(){
        new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem();
    }

    @Test(dependsOnMethods = "searchForItem")
    void clickSearchedItem(){
        new itemPage(DriverFactory.GetThreadDriver())
                .selectSize(hoddieSize)
                .selectColor(hoddieColor)
                .enterQuentity(hoddieQuentity)
                .clickOnAddCard()
                .clickOnCard(hoddieQuentity)
                .clickOnViewCard()
                .clickOnProceed();
    }

    @Test(dependsOnMethods = {"searchForItem", "clickSearchedItem"})
    void proceedItem(){
        String greettingMsg = new purchaseItemPage(DriverFactory.GetThreadDriver())
                .enterEmail(DataFaker.fakeEmail())
                .enterName(DataFaker.fakerFirstName(),DataFaker.fakerLastName())
                .enterCompany(DataFaker.fakerCompany())
                .enterStreetAddress(DataFaker.fakerAddress())
                .enterCity(DataFaker.fakerCity())
                .selectRigion("California")
                .enterZipCode("87843")
                .enterPhoneNo("989748378")
                .selectShoppingMethod()
                .clickOnNext()
                .clickOnPay()
                .clickOnPlaceOrder()
                .getCurrentURL();

        Assert.assertEquals(greettingMsg, successUrl);
    }

    @AfterClass
    void Quit(){
        DriverFactory.QuitThreadDriver();
    }
}
