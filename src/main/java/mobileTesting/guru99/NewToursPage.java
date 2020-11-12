package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NewToursPage extends BasePage {

    By navMenu = By.xpath("//nav[@class='navbar navbar-default']");

    public NewToursPage(AppiumDriver driver) {
        appiumDriver = driver;
    }

    public boolean isMainMenuPresent() {
        return appiumDriver.findElement(navMenu).isDisplayed();
    }
}
