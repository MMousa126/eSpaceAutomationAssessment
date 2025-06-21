package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class itemPage {

    private final WebDriver driver;
    private final By colorBlue = By.id("option-label-color-93-item-50");
    private final By colorGreen = By.id("option-label-color-93-item-53");
    private final By colorRed = By.id("option-label-color-93-item-58");

    private final By sizeXS = By.cssSelector(".swatch-option[aria-label='XS']");
    private final By sizeS = By.cssSelector(".swatch-option[aria-label='S']");
    private final By sizeM = By.cssSelector(".swatch-option[aria-label='M']");
    private final By sizeL = By.cssSelector(".swatch-option[aria-label='L']");
    private final By sizeXL = By.cssSelector(".swatch-option[aria-label='XL']");

    private final By addtoCardBtn = By.id("product-addtocart-button");
    private final By qty = By.id("qty");

    private final By cardCount = By.className("counter-number");

    private final By viewCardpage = By.cssSelector("a.action.viewcart");
    public itemPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By errorSize = By.xpath("//div[@id='super_attribute[143]-error']");
    private final By errorColor = By.xpath("//div[@id='super_attribute[93]-error']");
    private final By errorQuentity = By.id("qty-error");

    // Avilible colors to Choose from is Blue, Green, Red
    public itemPage selectColor(String color){
        color = color.toLowerCase();

        switch (color){
            case "red":
                Utility.Clicking_OnElementVisibility(driver,colorRed);
                break;
            case "green":
                Utility.Clicking_OnElementVisibility(driver,colorGreen);
                break;
            default:
                Utility.Clicking_OnElementVisibility(driver,colorBlue);
        }
        return this;
    }

    // Avilible sizes to Choose from is XS, S, M, L, XL
    public itemPage selectSize(String size){
        size = size.toLowerCase();

        switch (size){
            case "xs":
                Utility.Clicking_OnElementVisibility(driver,sizeXS);
                break;
            case "s":
                Utility.Clicking_OnElementVisibility(driver,sizeS);
                break;
            case "l":
                Utility.Clicking_OnElementVisibility(driver,sizeL);
                break;
            case "xl":
                Utility.Clicking_OnElementVisibility(driver,sizeXL);
                break;
            default:
                Utility.Clicking_OnElementVisibility(driver,sizeM);
        }

        return this;
    }

    // Avilible sizes to Choose from is XS, S, M, L, XL
    public itemPage selectSizeDiff(String size){
        size = size.toUpperCase();
        new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".swatch-option[aria-label='"+size+"']")));
        driver.findElement(By.cssSelector(".swatch-option[aria-label='"+size+"']")).click();
        return this;
    }

    public itemPage enterQuentity(String quantity){
        Utility.clearField(driver,qty);
        Utility.SendData(driver,qty,quantity);
        return this;
    }

    public itemPage emptyQuentity(){
        Utility.clearField(driver,qty);
        return this;
    }

    public itemPage clickOnAddCard(){
        Utility.Clicking_OnElement(driver,addtoCardBtn);
        return this;
    }

    // this method take this parameter quentity -> for waiting only until the number is present in the card
    public String getNoOfProductsAddedToCard(String quentity){
        Utility.ScrollingUsingJS(driver,cardCount);
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(cardCount,quentity));
        return Utility.GetText(driver,cardCount);
    }


    // this method take this parameter quentity -> for waiting only until the number is present in the card
    public itemPage clickOnCard(String quentity){
        Utility.ScrollingUsingJS(driver,cardCount);
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(cardCount,quentity));
        Utility.Clicking_OnElementVisibility(driver,cardCount);
        return this;
    }

    public viewItemsCard clickOnViewCard(){
        Utility.Clicking_OnElementVisibility(driver,viewCardpage);
        return new viewItemsCard(driver);
    }

    public String getErrorMsgForSize(){
        Utility.WaitUntilTheElementIsVisible(driver,errorSize);
        return Utility.GetText(driver,errorSize);
    }

    public String getErrorMsgForColor(){
        Utility.WaitUntilTheElementIsVisible(driver,errorColor);
        return Utility.GetText(driver,errorColor);
    }

    public String getErrorMsgForQuentity(){
        Utility.WaitUntilTheElementIsVisible(driver,errorQuentity);
        return Utility.GetText(driver,errorQuentity);
    }
}
