package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.UnableToSetCookieException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P24AttachFile {
    private final WebDriver driver;
    private final By attachFileBtn = By.xpath("//div[@class='drag-drop']/input");
    private final By fileName = By.id("fileName");
    private final By selectAttachFile = By.cssSelector("[type=file]:nth-of-type(1)");
    private final By waitUntilPictureVisible = By.className("sec-img");
    private final By addBtn = By.xpath("//div[@class ='modal-footer']/button[1]");
    private final By saveAttachFileBtn = By.xpath("(//footer[@class='form-footer'])/button[1]");
    private final By attachment = By.className("attach-card");

    public P24AttachFile(WebDriver driver) {
        this.driver = driver;
    }


    public P24AttachFile clickOnAttachFile(){

        Utility.Clicking_OnElementWithoutAnyWait(driver,attachFileBtn);
        return this;
    }

    public P24AttachFile enterFileName(String fileN){
        Utility.SendData(driver,fileName,fileN);
        return this;
    }

    public P24AttachFile enterAttachedFile(String filePathNew){

        Utility.ScrollingUsingJS(driver,selectAttachFile);

        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Utility.uploadingFileUsingSendKey(driver,selectAttachFile,filePathNew);

        return this;
    }

    public P24AttachFile clickOnAdd(){
        new WebDriverWait(driver, Duration.ofSeconds(360))
                .until(ExpectedConditions.presenceOfElementLocated(waitUntilPictureVisible));
        try {
            Thread.sleep(Duration.ofSeconds(4));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Button is still not clicked");
        Utility.Clicking_OnElement(driver,addBtn);

        System.out.println("Button is clicked");
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public P12IncomingTransaction clickOnSaveAttachFile() {

//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(driver -> driver.findElement(saveAttachFileBtn).isEnabled());

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(attachment));

        Utility.Clicking_OnElement(driver, saveAttachFileBtn);
        return new P12IncomingTransaction(driver);
    }
}
