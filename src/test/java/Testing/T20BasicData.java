package Testing;

import Assertion.AssertUtility;
import PagesTesting.P01Login;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import Factory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.*;

public class T20BasicData {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landing = "basicData_URL";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String basicDataURL = DataUtility.GetPropertiesDataFromFile(browser_filename,landing);
    private final String inboxurl = DataUtility.GetPropertiesDataFromFile(browser_filename,"inboxURL") ;


    SoftAssert softAssert = new SoftAssert();



    @BeforeMethod
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
    void BasicData() {

        String outgoingNo =
                DataFaker.generateRandomListOfNumbers(1,96,8);
        new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .clickOnIncomingInbox()
                .selectDep1()
                .openDDLRegisterIncomingTrans()
                .clickOnRegIncomingTrans()
                .clickOnBasicData()
                .selectTransactionType("اصل")
                .selectYesForRoyal("yes")
                .selectConfidentialLevel("عام")
                .selectImportantLevel(1)
                .selectUrgentLevel("عادي")
                .enterTransactionTopic("New Automated Test ")
                .selectEntity()
                .selectMainEntity("Mohamed Mousa Entity")
                .selectSecondaryEntity("Mohamed Mousa Entity")
                .enterNoOfAttachments(7)
                .enterOutgoingNo(outgoingNo)
                .selectEnterDate("Hijri")
                .enterEntityTopic("Automated")
               .clickOnSave()
           ;
    }
    @Test(priority = 2)
    void getTransactionCode() {

        String transKey = "TransactionNo"+ DataFaker.generateRandomListOfNumbers(1,96,2);
        String outgoingNo =
                DataFaker.generateRandomListOfNumbers(1,96,8);
        String transNo = new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .clickOnIncomingInbox()
                .selectDep1()
                .openDDLRegisterIncomingTrans()
                .clickOnRegIncomingTrans()
                .clickOnBasicData()
                .selectTransactionType("اصل")
                .selectYesForRoyal("yes")
                .selectConfidentialLevel("عام")
                .selectImportantLevel(1)
                .selectUrgentLevel("عادي")
                .enterTransactionTopic("New Automated Test ")
                .selectEntity()
                .selectMainEntity("Mohamed Mousa Entity")
                .selectSecondaryEntity("Mohamed Mousa Entity")
                .enterNoOfAttachments(7)
                .enterOutgoingNo(outgoingNo)
                .selectEnterDate("Hijri")
                .enterEntityTopic("Automated")
                .clickOnSave()
                .getTransactionCode("Arabic")
        ;

        DataUtility.appendToJsonFileString("TransNumbers",transKey,transNo);
    }
    @AfterMethod
    void Quit(){

//        DriverFactory.QuitThreadDriver();

    }
}
