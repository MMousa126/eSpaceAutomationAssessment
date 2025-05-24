package Mandatory;

import PagesTesting.P01Login;
import PagesTesting.P02LandingPage;
import PagesTesting.P12IncomingTransaction;
import Utilities.DataFaker;
import Utilities.DataUtility;

import static Factory.DriverFactory.GetThreadDriver;

public class Basic implements CreatingTransaction{


    public static void logIn(){

        String filePath = DataUtility.GetPropertiesDataFromFile("environment","AuthFile_Location");
        String userName = DataUtility.GetJsonDataFromFilePC(filePath,"Musername");
        String password = DataUtility.GetJsonDataFromFilePC(filePath,"Mpassword");
        new P01Login(GetThreadDriver())
                .enterUsername(userName)
                .enterPassword(password)
                .clickOnLogin();
    }

    public static void clickOnRegisterTransaction(){
        new P02LandingPage(GetThreadDriver())
                .clickOnIncomingInbox()
                .selectDep1()
                .openDDLRegisterIncomingTrans()
                .clickOnRegIncomingTrans();
    }
    public static String baseSetUp() {
        String outgoingNo =
                DataFaker.generateRandomListOfNumbers(1, 96, 8);


        String transNo =  new P12IncomingTransaction(GetThreadDriver())
                .clickOnBasicData()
                .selectTransactionType("اصل")
                .selectYesForRoyal("No")
                .selectConfidentialLevel("عام")
                .selectImportantLevel(1)
                .selectUrgentLevel("عادي")
                .enterTransactionTopic("New Automated Test")
                .selectEntity()
                .selectMainEntity("Mohamed Mousa Entity")
                .selectSecondaryEntity("Mohamed Mousa Entity")
                .enterNoOfAttachments(7)
                .enterOutgoingNo(outgoingNo)
                .selectEnterDate("ميلادي")
                .enterEntityTopic("Automated")
                .clickOnSave()
                .getTransactionCode("Arabic");

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnContinueRegistering();

        return transNo;
    }

    public static void doNotLinkingTransaction(){

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnNoLinkTrans();
    }

    public static void LinkingTransaction(){

        new P12IncomingTransaction(GetThreadDriver())
                .clickOnYesLinkTrans()
                .enterTransactionNo("46/000001")
                .selectTransactionType("منشئ داخلي")
                .clickOnSearch()
                .selectRelationType("Relation","english")
                .clickOnLink()
                .clickOnSaveLinking()
                .clickOnContinueRegistering();
    }
    public static void printBarcode(String fileBarcodeName){
        String randomNo = DataFaker.generateRandomListOfNumbers(1,15,1);
        new P12IncomingTransaction(GetThreadDriver())
                .clickOnPrintBarcode()
                .printBarcodeData(randomNo)
                .finishStepPrint(fileBarcodeName)
                .clickOnContinueRegistering();
    }

    public static void archiveFile(String archiveFilePath,String browsingName){
        new P12IncomingTransaction(GetThreadDriver())
                .clickOnArchiveFile()
                .clickOnAddArchive()
                .clickOnAddArchiveFromPopup(archiveFilePath)
                .enterBrowsingName(browsingName)
                .clickOnSaveArchive()
                .clickOnContinueRegistering();
    }
}
