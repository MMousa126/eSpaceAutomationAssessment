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
import static Utilities.DataFaker.generateRandomListOfNumbers;

public class T06AddingIndividual {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);

    private final String firstName = DataFaker.fakerName();
    private final String secondName = DataFaker.fakerName();
    private final String thirdName = DataFaker.fakerName();
    private final String fourthName = DataFaker.fakerName();

    private final String fullName =
            firstName  + " " +
            secondName + " " +
            thirdName  + " " +
            fourthName;
    private final String[] enteredData =
            {
                    DataFaker.generateRandomListOfNumbers(0,10,10),
                    firstName,
                    secondName,
                    thirdName,
                    fourthName,
                    "05" + DataFaker.generateRandomListOfNumbers(0,10,8)
                    ,"10/30/2000"
            };
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
    void addingIndividual(){
        String filePath = DataUtility.GetPropertiesDataFromFile("environment","AuthFile_Location");
        String userName = DataUtility.GetJsonDataFromFilePC(filePath,"Musername");
        String password = DataUtility.GetJsonDataFromFilePC(filePath,"Mpassword");
        String indvNameKey = "IndividualName"+ DataFaker.generateRandomNumber(1,20);
        String inboxurl = DataUtility.GetPropertiesDataFromFile(browser_filename,"inboxURL") ;
        String url = new P01Login(GetThreadDriver())
                .enterUsername(userName)
                .enterPassword(password)
                .clickOnLogin()
                .sysSettings()
                .clickOnIndividual()
                .clickOnAddingIndividual()
                .selectIdentity("هوية")
                .enterIndividualData(enteredData)
                .selectGender("ذكر")
                .selectNationality("سعودي")
                .availableIncoming("yes")
                .clickOnSave()
                .clickOnMyInbox()
                .getCurrentURL()
                ;
        DataUtility.appendToJsonFileString("IndividualNames",indvNameKey,fullName);

        new AssertUtility(softAssert)
                .assertEqual(url,inboxurl);
    }

    @AfterMethod
    void quit()
    {
        //DriverFactory.QuitThreadDriver();
    }


}
