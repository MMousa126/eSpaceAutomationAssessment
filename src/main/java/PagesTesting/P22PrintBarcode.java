package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.UnableToSetCookieException;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class P22PrintBarcode {
    private final WebDriver driver;
    private final By nextBtn = By.xpath("(//div[@class='modal-footer'])/button");
    private final By printNO = By.xpath("//div[@class='form-info']/input");
    private final By printBtn = By.xpath("(//div[@class='modal-footer'])/button[2]");

    public P22PrintBarcode(WebDriver driver) {
        this.driver =driver;
    }


    public P22PrintBarcode printBarcodeData(String noOfCopy){
        Utility.Clicking_OnElement(driver,nextBtn);
        Utility.SendData(driver,printNO,noOfCopy);
        Utility.Clicking_OnElement(driver,printBtn);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }
    public P12IncomingTransaction finishStepPrint(String barcodeName){

        try {
            Thread.sleep(2000);
            Robot robot = new Robot();
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(3000);
            Utility.typeString(robot,barcodeName);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(2000);
        } catch (InterruptedException | AWTException e) {
            throw new RuntimeException(e);
        }
        return new P12IncomingTransaction(driver);
    }
}
