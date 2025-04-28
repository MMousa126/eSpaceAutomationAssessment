package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01Login {

    public final WebDriver driver;

    private final By userName = By.cssSelector("[formcontrolname = username]");
    private final By passWord = By.cssSelector("[formcontrolname = password]");
    private final By loginbutton = By.xpath("//button[@class = 'btn success-btn']");


    public P01Login(WebDriver driver) {
        this.driver = driver;
    }


    public P01Login enterUsername(String username){
        Utility.SendData(driver,userName,username);
        return this;
    }

    public P01Login enterPassword(String password){
        Utility.SendData(driver,passWord,password);
        return this;
    }

    public P02LandingPage clickOnLogin(){
        Utility.Clicking_OnElement(driver,loginbutton);
        return new P02LandingPage(driver);
    }


}
