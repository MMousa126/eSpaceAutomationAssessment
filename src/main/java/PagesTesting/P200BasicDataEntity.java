package PagesTesting;

import Utilities.LogsUtility;
import Utilities.Utility;
import com.aventstack.extentreports.templating.FreemarkerTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class P200BasicDataEntity {
    private final WebDriver driver;
    private final By mainEntity= By.id("dropdownMainIncomingEntity");
    private final By searchEntity = By.xpath("(//input[@class = 'form-control'])[1]");
    private final By searchableEntity = By.xpath("(//button[@class = 'dropdown-item'])[2]");
    private final By searchSEntity = By.xpath("(//input[@class = 'form-control'])[2]");
    private final By searchableSEntity = By.xpath("(//button[@class = 'dropdown-item'])[3]");
    private final By secondaryEntity= By.xpath("(//button[@type = 'button'])[3]");
    private final By noOfAttachments= By.id("attachmentsCount");
    private final By outgoingNo= By.id("issuedNumber");
    private final By georgianDate= By.xpath("(//div[@id= 'dateType'])/label[1]");
    private final By hijriDate= By.xpath("(//div[@id= 'dateType'])/label[2]");
    private final By dateLocator = By.id("issuedDate");
    private final By dateGLocator = By.cssSelector("[formcontrolname = issuedDate]");
    private final By entityDetails = By.id("entityDetails");

    public P200BasicDataEntity(WebDriver driver) {
        this.driver = driver;
    }

    public P200BasicDataEntity selectMainEntity(String entity){

        Utility.ScrollingUsingJS(driver,mainEntity);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Utility.Clicking_OnElement(driver,mainEntity);
        Utility.SendData(driver,searchEntity,entity);

        try{
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.textToBe(searchableEntity,entity));
            Utility.Clicking_OnElement(driver,searchableEntity);
        }catch (NoSuchElementException e){
            LogsUtility.LoggerInfo("No such element Exist");
        }
        return this;
    }

    public P200BasicDataEntity selectSecondaryEntity(String Sentity){

        Utility.ScrollingUsingJS(driver,secondaryEntity);

        Utility.Clicking_OnElement(driver,secondaryEntity);
        Utility.SendData(driver,searchSEntity,Sentity);

        try{
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.textToBe(searchableSEntity,Sentity));
            Thread.sleep(2000);
            Utility.Clicking_OnElement(driver,searchableSEntity);
        }catch (NoSuchElementException e) {
            LogsUtility.LoggerInfo("No such element Exist");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public P200BasicDataEntity enterNoOfAttachments(int noOfEntity){

        Utility.ScrollingUsingJS(driver,noOfAttachments);
        if (noOfEntity > 50 || noOfEntity < 0)
            Utility.SendData(driver,noOfAttachments, String.valueOf(5));

        else
            Utility.SendData(driver,noOfAttachments, String.valueOf(noOfEntity));

        return this;
    }

    public P200BasicDataEntity enterOutgoingNo(String number){
        Utility.ScrollingUsingJS(driver,outgoingNo);
        Utility.SendData(driver,outgoingNo,number);
        return this;
    }

    public P200BasicDataEntity selectEnterDate(String dateType){
        Utility.ScrollingUsingJS(driver,georgianDate);

        dateType = dateType.replace("\\s", "");
        if (dateType.equals("ميلادي") || dateType.equals("georgian") || dateType.equals("Georgian")) {
            Utility.Clicking_OnElement(driver, georgianDate);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Utility.SendData(driver,dateGLocator,Utility.TodayGeorgianDate());
            System.out.println("Mousa" + Utility.TodayGeorgianDate() + "Mousa");
        }
        else {
            Utility.Clicking_OnElement(driver,hijriDate);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Utility.Clicking_OnElementWithVisibility(driver,dateLocator);
            try {
                Thread.sleep(500);
                Robot robot = new Robot();
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }

//
//            Utility.SendData(driver,dateLocator,Utility.TodayHijriDate());
            System.out.println("Mousa" + Utility.TodayHijriDate() + "Mousa");
        }

        return this;
    }

    public P20BasicData enterEntityTopic(String entityTopic){
        Utility.ScrollingUsingJS(driver,entityDetails);
        Utility.SendData(driver,entityDetails,entityTopic);
        return new P20BasicData(driver);
    }

}
