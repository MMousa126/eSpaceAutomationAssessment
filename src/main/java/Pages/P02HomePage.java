package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02HomePage {
    private final WebDriver driver;
    private final By searchBox = By.cssSelector("div .oxd-main-menu-search >input");
    private final By adminUsers = By.cssSelector("div .oxd-main-menu-item");

    public P02HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHomePageURL(){
        return Utility.getCurrentURL(driver);
    }

    public P02HomePage searchInSidemenu(String searchData){
        Utility.SendData(driver,searchBox,searchData);
        return this;
    }

    public P03UserManagment clickOnAdminUsers(){
        Utility.Clicking_OnElement(driver,adminUsers);
        return new P03UserManagment(driver);
    }


}
