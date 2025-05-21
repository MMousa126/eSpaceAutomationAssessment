package Testing;

import Mandatory.Basic;
import Mandatory.CreatingTransaction;
import PagesTesting.P12IncomingTransaction;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.GetThreadDriver;
import static Factory.DriverFactory.SetupThreadDriver;

public class T24AttachFile {
    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename, browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename, portal);


    SoftAssert softAssert = new SoftAssert();

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



        Basic.logIn();

        Basic.clickOnRegisterTransaction();
        String noOfTheTransaction = Basic.baseSetUp();
        String transKey = "TransactionNo" + DataUtility.getAndIncrementCounter("TransactionCounter");

        DataUtility.appendToJsonFileString("TransNumbers", transKey, noOfTheTransaction);


        Basic.LinkingTransaction();
        Basic.printBarcode(DataFaker.fakerName());


        String archiveFile = DataUtility.GetPropertiesDataFromFile(browser_filename, "FilesToBeArchived");
        String randomName = DataFaker.fakerArtist();
        Basic.archiveFile(archiveFile,randomName);
        String file= "C:\\Users\\User\\Downloads\\DSC_6176-min.JPG";


        System.out.println(file);
        new P12IncomingTransaction(GetThreadDriver())
                .clickOnYesAttachFile()
                .clickOnAttachFile()
                .enterFileName(randomName)
                .enterAttachedFile("C:\\Users\\User\\Downloads\\Untitled video - Made with Clipchamp.mp4")
                .clickOnAdd()
                .clickOnSaveAttachFile()
//                .clickOnContinueRegistering()
                ;
    }

    @AfterMethod
    void Quit(){

//        DriverFactory.QuitThreadDriver();

    }

}
