package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P12IncomingTransaction {
    private final WebDriver driver;
    private final By basicData = By.xpath("(//button[@class='btn'])[1]");
    private final By linkTransNo = By.xpath("(//button[@class='btn'])[1]");
    private final By linkTransYes = By.xpath("(//button[@class='btn'])[2] ");
    private final By printBarcode = By.xpath("(//button[@class='btn'])[2]");
    private final By archiveFile = By.xpath("(//button[@class='btn'])[3]");
    private final By attachFileNo = By.xpath("(//button[@class='btn'])[4]");
    private final By attachFileYes = By.xpath("(//button[@class='btn'])[5]");
    private final By btnPrintMemo = By.xpath("(//button[@type='button'])[1]");
    private final By btnSave = By.xpath("(//button[@type='button'])[2]");
    private final By btnCancel = By.xpath("(//button[@type='button'])[3]");
    private final By vMessage = By.cssSelector(".modal-body > .description");
    private final By continueRegister = By.xpath("(//button[@type = 'button'])[5]");
    private final By inbox = By.xpath("(//button[@type = 'button'])[6]");


    private final By locForBasicDataCompleted = By.xpath("(//div[@class = 'transaction-card']/div)[1]");
    private final By locForLinkingCompleted = By.xpath("(//div[@class = 'transaction-card']/div)[2]");
    private final By locForBarcodeCompleted = By.xpath("(//div[@class = 'transaction-card']/div)[3]");
    private final By locForArchiveCompleted = By.xpath("(//div[@class = 'transaction-card']/div)[4]");
    private final By locForAttachCompleted = By.xpath("(//div[@class = 'transaction-card']/div)[5]");


    public P12IncomingTransaction(WebDriver driver) {
        this.driver = driver;
    }

    public String getTransactionValidationMessage()
    {
        return Utility.GetText(driver,vMessage);
    }

    public String getTransactionCode(String language){
        Matcher matcher;
        Pattern pattern;
        if (language.equals("Arabic")){
            pattern = Pattern.compile("رقم المعاملة\\s*([\\d/]+)");


        }else{
            pattern = Pattern.compile("Transaction Number\\s*([\\d/]+)");
        }

        matcher = pattern.matcher(getTransactionValidationMessage());

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
    public String getCurrentUrl(){
        return Utility.getCurrentURL(driver);
    }

    public P20BasicData clickOnBasicData(){
        Utility.Clicking_OnElement(driver,basicData);
        return new P20BasicData(driver);
    }


    public P12IncomingTransaction clickOnNoLinkTrans(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locForBasicDataCompleted,"مكتمل"));

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(linkTransNo));
        Utility.Clicking_OnElement(driver,linkTransNo);
        return this;
    }

    public P21LinkTransaction clickOnYesLinkTrans(){

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locForBasicDataCompleted,"مكتمل"));

        Utility.Clicking_OnElement(driver,linkTransYes);
        return new P21LinkTransaction(driver);
    }

    public P22PrintBarcode clickOnPrintBarcode(){

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locForLinkingCompleted,"مكتمل"));
        Utility.Clicking_OnElement(driver,printBarcode);
        return new P22PrintBarcode(driver);
    }

    public P23ArchiveFile clickOnArchiveFile(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locForBarcodeCompleted,"مكتمل"));
        Utility.ScrollingUsingJS(driver,archiveFile);
        Utility.Clicking_OnElement(driver,archiveFile);
        return new P23ArchiveFile(driver);
    }
    public P12IncomingTransaction clickOnNoAttachFile(){

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locForArchiveCompleted,"مكتمل"));
        Utility.ScrollingUsingJS(driver,attachFileNo);
        Utility.Clicking_OnElement(driver,attachFileNo);
        return this;
    }

    public P24AttachFile clickOnYesAttachFile() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locForArchiveCompleted,"مكتمل"));
        Utility.ScrollingUsingJS(driver,attachFileYes);
        Utility.Clicking_OnElement(driver, attachFileYes);
        return new P24AttachFile(driver);
    }

    public P12IncomingTransaction clickOnContinueRegistering(){

        Utility.Clicking_OnElementVisibility(driver,continueRegister);
        return this;
    }


    public P11IncomingInbox clickOnIncomingInbox(){
        Utility.Clicking_OnElement(driver,inbox);
        return new P11IncomingInbox(driver);
    }
}
