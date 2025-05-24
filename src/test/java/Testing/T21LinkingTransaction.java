package Testing;

import PagesTesting.P01Login;
import PagesTesting.P12IncomingTransaction;
import PagesTesting.P21LinkTransaction;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

public class T21LinkingTransaction {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landing = "basicData_URL";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String basicDataURL = DataUtility.GetPropertiesDataFromFile(browser_filename,landing);
    private final String inboxurl = DataUtility.GetPropertiesDataFromFile(browser_filename,"inboxURL") ;


    SoftAssert softAssert = new SoftAssert();

    public String baseSetUp(){
        String filePath = DataUtility.GetPropertiesDataFromFile("environment","AuthFile_Location");
        String userName = DataUtility.GetJsonDataFromFilePC(filePath,"Musername");
        String password = DataUtility.GetJsonDataFromFilePC(filePath,"Mpassword");
        String outgoingNo =
                DataFaker.generateRandomListOfNumbers(1,96,8);
        String noOfTheTransaction =   new P01Login(GetThreadDriver())
                .enterUsername(userName)
                .enterPassword(password)
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


        return noOfTheTransaction;
    }
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
    void linkTransaction(){

        String noOfTheTransaction = baseSetUp();
        String transKey = "TransactionNo"+ DataUtility.getAndIncrementCounter("TransactionCounter");

        DataUtility.appendToJsonFileString("TransNumbers",transKey,noOfTheTransaction);

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnContinueRegistering()
                .clickOnYesLinkTrans()
                .enterTransactionNo("46/000001")
                .selectTransactionType("منشئ داخلي")
                .clickOnSearch()
                .selectRelationType("Relation","english")
                .clickOnLink()
                .clickOnSaveLinking()
                .clickOnContinueRegistering()
        ;
    }
    @AfterMethod
    void Quit(){

//        DriverFactory.QuitThreadDriver();

    }
}
