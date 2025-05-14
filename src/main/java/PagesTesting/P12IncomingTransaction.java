package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P12IncomingTransaction {
    private final WebDriver driver;
    private final By basicData = By.xpath("(//button[@class='btn'])[1]");
    private final By linkTransNo = By.xpath("(//button[@class='btn'])[2]");
    private final By linkTransYes = By.xpath("(//button[@class='btn'])[3]");
    private final By printBarcode = By.xpath("(//button[@class='btn'])[4]");
    private final By archiveFile = By.xpath("(//button[@class='btn'])[5]");
    private final By attachFileNo = By.xpath("(//button[@class='btn'])[6]");
    private final By attachFileYes = By.xpath("(//button[@class='btn'])[7]");

    public P12IncomingTransaction(WebDriver driver) {
        this.driver = driver;
    }

    public P20BasicData clickOnBasicData(){
        Utility.Clicking_OnElement(driver,basicData);
        return new P20BasicData(driver);
    }

    public P12IncomingTransaction clickOnNoLinkTrans(){
        Utility.Clicking_OnElement(driver,linkTransNo);
        return this;
    }

    public P21LinkTransaction clickOnYesLinkTrans(){
        Utility.Clicking_OnElement(driver,linkTransYes);
        return new P21LinkTransaction(driver);
    }

    public P22PrintBarcode clickOnPrintBarcode(){
        Utility.Clicking_OnElement(driver,printBarcode);
        return new P22PrintBarcode(driver);
    }

    public P23ArchiveFile clickOnArchiveFile(){
        Utility.Clicking_OnElement(driver,archiveFile);
        return new P23ArchiveFile(driver);
    }
    public P12IncomingTransaction clickOnNoAttachFile(){
        Utility.Clicking_OnElement(driver,attachFileNo);
        return this;
    }

    public P24AttachFile clickOnYesAttachFile(){
        Utility.Clicking_OnElement(driver,attachFileYes);
        return new P24AttachFile(driver);
    }


}
