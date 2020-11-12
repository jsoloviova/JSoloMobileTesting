package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class TablePage extends BasePage {

    By tableOnPage = By.xpath("//tbody");

    public TablePage(AppiumDriver driver) {
        appiumDriver = driver;
    }

    public boolean isTablePresent() {
        return appiumDriver.findElement(tableOnPage).isDisplayed();
    }
}
