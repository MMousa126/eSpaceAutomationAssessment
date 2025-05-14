package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class P03SystemSettings {
    private final WebDriver driver;
    private final By institution = By.xpath("(//a[@routerlinkactive= 'active'])[3]");
    private final By company = By.xpath("(//a[@routerlinkactive= 'active'])[2]");
    private final By individual = By.xpath("(//a[@routerlinkactive= 'active'])[1]");
    private final By addInst = By.xpath("(//span[@class= 'text'])");
    public P03SystemSettings(WebDriver driver) {
        this.driver = driver;
    }

    public P03SystemSettings clickOnInstitution(){

        Utility.Clicking_OnElement(driver,institution);
        return this;
    }

    public P03SystemSettings clickOnCompany(){

        Utility.Clicking_OnElement(driver,company);
        return this;
    }

    public P03SystemSettings clickOnIndividual(){

        Utility.Clicking_OnElement(driver,individual);
        return this;
    }

    public P04institution clickOnAddingInstitution(){

        Utility.Clicking_OnElementWithVisibility(driver,addInst);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new P04institution(driver);
    }

    public P05Company clickOnAddingCompany(){

        Utility.Clicking_OnElementWithVisibility(driver,addInst);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new P05Company(driver);
    }

    public P06Individual clickOnAddingIndividual(){

        Utility.Clicking_OnElementWithVisibility(driver,addInst);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new P06Individual(driver);
    }



}
