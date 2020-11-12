package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    AppiumDriver appiumDriver;

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 15);
        wait.until(ExpectedConditions.titleContains(": MyTest"));
        return appiumDriver.getTitle();
    }
}
