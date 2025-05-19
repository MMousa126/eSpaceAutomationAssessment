package Testing;

import Mandatory.CreatingTransaction;
import PagesTesting.P01Login;
import PagesTesting.P02LandingPage;
import PagesTesting.P11IncomingInbox;
import PagesTesting.P12IncomingTransaction;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.apache.commons.collections4.Get;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

public class T23ArchiveFile implements CreatingTransaction {

    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename, browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename, portal);


    SoftAssert softAssert = new SoftAssert();

    public void logIn(){
        new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin();
    }
    public void clickOnRegisterTransaction(){
        new P02LandingPage(GetThreadDriver())
                .clickOnIncomingInbox()
                .selectDep1()
                .openDDLRegisterIncomingTrans()
                .clickOnRegIncomingTrans();
    }

    public String baseSetUp() {
        String outgoingNo =
                DataFaker.generateRandomListOfNumbers(1, 96, 8);


        String transNo =  new P12IncomingTransaction(GetThreadDriver())
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
                .getTransactionCode("Arabic");

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnContinueRegistering();

        return transNo;
    }

    public void doNotLinkingTransaction(){

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnNoLinkTrans();
    }

    public void printBarcode(String fileBarcodeName){
        new P12IncomingTransaction(GetThreadDriver())
                .clickOnPrintBarcode()
                .printBarcodeData("8")
                .finishStepPrint(fileBarcodeName)
                .clickOnContinueRegistering();
    }

    @BeforeMethod
    void setUp() {
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
    void ArchiveFile() {


        logIn();

        clickOnRegisterTransaction();
        String noOfTheTransaction = baseSetUp();
        String transKey = "TransactionNo" + DataUtility.getAndIncrementCounter("TransactionCounter");

        DataUtility.appendToJsonFileString("TransNumbers", transKey, noOfTheTransaction);


        doNotLinkingTransaction();
        printBarcode(DataFaker.fakerName());


        String archiveFile = DataUtility.GetPropertiesDataFromFile(browser_filename,"FilesToBeArchived");

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnArchiveFile()
                .clickOnAddArchive()
                .clickOnAddArchiveFromPopup(archiveFile)
                .enterBrowsingName("New New fddfd New")
                .clickOnSaveArchive()
                .clickOnContinueRegistering();

    }

    @AfterMethod
    void Quit(){

//        DriverFactory.QuitThreadDriver();

    }
}
