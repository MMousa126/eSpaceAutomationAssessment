package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class paymentMethod {
    private final WebDriver driver;

    private final By placeOrderLoc = By.cssSelector("button[class*='checkout']");
    private final By greattingMagLoc = By.cssSelector("[data-ui-id = page-title-wrapper]");

    private final By checkYes = By.cssSelector("[name = billing-address-same-as-shipping]");
    public paymentMethod(WebDriver driver) {
        this.driver = driver;
    }

    public paymentMethod clickOnPlaceOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));
        Utility.ScrollingUsingJS(driver,placeOrderLoc);
        Utility.Clicking_OnElement(driver,placeOrderLoc);
        return this;
    }

    public String getCurrentURL(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));

        return Utility.getCurrentURL(driver);
    }

    public String getGreatingMSG(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));
        Utility.waitPageToReload(driver);
        return Utility.GetText(driver,greattingMagLoc);
    }

    public paymentMethod clickOnPay(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));

        if (! (Utility.ByToWebElement(driver,checkYes).isSelected())){
            Utility.Clicking_OnElementVisibility(driver,checkYes);
        }

        return this;
    }

}
