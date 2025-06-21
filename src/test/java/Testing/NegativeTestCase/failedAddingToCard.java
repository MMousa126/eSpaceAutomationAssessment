package Testing.NegativeTestCase;

import Assertion.AssertUtility;
import Factory.DriverFactory;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
import PagesTesting.itemPage;
import PagesTesting.itemSearch;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})
public class failedAddingToCard {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String testDatafile = "TestData";
    private String searchitem = "searchedProduct";


    private final String hoddSize = "HoddieSize";
    private final String hoddColor = "HoddieColor";
    private final String hoddQuantity = "HoddiesQuentity";


    private final String errorSizeColor = DataUtility.GetJsonDataFromFile(testDatafile,"ErrorMsgSizeColor");
    private final String errorQuentityZero = DataUtility.GetJsonDataFromFile(testDatafile,"EroorMsgQuentityZero");
    private final String errorQuentityEmpty = DataUtility.GetJsonDataFromFile(testDatafile,"ErrorMsgQuentityEmpty");

    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String ecommercePortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private String searchedItem = DataUtility.GetJsonDataFromFile(testDatafile,searchitem);

    private final SoftAssert softAssert = new SoftAssert();

    private final String hoddieSize = DataUtility.GetJsonDataFromFile(testDatafile,hoddSize);
    private final String hoddieColor = DataUtility.GetJsonDataFromFile(testDatafile,hoddColor);
    private final String hoddieQuentity = DataUtility.GetJsonDataFromFile(testDatafile,hoddQuantity);

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
    void addingItemToCardWithoutSelectSize(){
        String sizeError = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem()
                .selectColor(hoddieColor)
                .enterQuentity(hoddieQuentity)
                .clickOnAddCard()
                .getErrorMsgForSize();

        AssertUtility.assertEqual(softAssert,sizeError.trim(), errorSizeColor);
    }

    @Test(priority = 2)
    void addingItemToCardWithoutColor(){
        String colorError = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem()
                .selectSize(hoddieSize)
                .enterQuentity(hoddieQuentity)
                .clickOnAddCard()
                .getErrorMsgForColor();

        AssertUtility.assertEqual(softAssert,colorError.trim(), errorSizeColor);
    }

    @Test(priority = 3)
    void addingItemToCardWhenQuentityZero(){
        String zeroQuentity = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem()
                .selectSize(hoddieSize)
                .selectColor(hoddieColor)
                .enterQuentity("0")
                .clickOnAddCard()
                .getErrorMsgForQuentity();

        AssertUtility.assertEqual(softAssert,zeroQuentity.trim(), errorQuentityZero);
    }

    @Test(priority = 4)
    void addingItemToCardWhenQuentityNon(){
        String zeroQuentity = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem()
                .selectSize(hoddieSize)
                .selectColor(hoddieColor)
                .emptyQuentity()
                .clickOnAddCard()
                .getErrorMsgForQuentity();

        AssertUtility.assertEqual(softAssert,zeroQuentity.trim(), errorQuentityEmpty);
    }


    @AfterMethod
    void Quit(){
        DriverFactory.QuitThreadDriver();
    }
}
