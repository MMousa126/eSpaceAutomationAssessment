package Testing.PositiveTestCases;


import Assertion.AssertUtility;
import Factory.DriverFactory;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;
import PagesTesting.itemSearch;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})

//Verify that the user is able to add an item to the Card (ID: #12552)
public class AddingItemToCard {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String testDatafile = "TestData";
    private String searchitem = "searchedProduct";


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

    //Verify that the user is able to add an item to the Card
    @Test(priority = 1)
    void addingItemToCard(){
        String checkAddedToCard = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem()
                .selectSize(hoddieSize)
                .selectColor(hoddieColor)
                .enterQuentity(hoddieQuentity)
                .clickOnAddCard()
                .clickOnCard(hoddieQuentity)
                .clickOnViewCard()
                .getitemName();

        AssertUtility.assertEqual(softAssert,checkAddedToCard,searchedItem);
    }


    @Test(priority = 2)
    void addingItemToCardDiffAssertion(){
        String checkingNoOfAddedItems = new itemSearch(DriverFactory.GetThreadDriver())
                .enterSearchableItem(searchedItem)
                .clickOnSearchBtn()
                .clickOnSearchedItem()
                .selectSize(hoddieSize)
                .selectColor(hoddieColor)
                .enterQuentity(hoddieQuentity)
                .clickOnAddCard()
                .getNoOfProductsAddedToCard(hoddieQuentity);

        AssertUtility.assertEqual(softAssert,checkingNoOfAddedItems,hoddieQuentity);

    }

    @AfterMethod
    void Quit(){
        DriverFactory.QuitThreadDriver();
    }
}
