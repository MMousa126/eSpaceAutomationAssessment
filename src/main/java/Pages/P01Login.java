package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01Login {


    private final WebDriver driver;
    private final By usernameLoc = By.cssSelector("[name = username]");
    private final By passwordLoc = By.cssSelector("[name = password]");
    private final By loginButtonLoc = By.cssSelector("[type = submit]");

    public P01Login(WebDriver driver){
        this.driver = driver;
    }

    //TODO: Login using Username and Password
    public P01Login enterAuthCrediential(String username, String password){

        Utility.SendData(driver,usernameLoc,username);
        Utility.SendData(driver,passwordLoc,password);
        return this;
    }

    //TODO: Click On Login Button
    public P02HomePage clickOnLogin(){

        Utility.Clicking_OnElement(driver,loginButtonLoc);
        return new P02HomePage(driver);
    }


}
