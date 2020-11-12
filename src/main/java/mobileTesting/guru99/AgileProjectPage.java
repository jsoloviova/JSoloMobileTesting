package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class AgileProjectPage extends BasePage {

    By loginInput = By.xpath("//input[@name='uid']");
    By passwordInput = By.xpath("//input[@name='password']");
    By loginButton = By.xpath("//input[@value='LOGIN']");
    By welcomeMessage = By.xpath("//marquee[@class='heading3']");
    String invalidCredentialsAlert = "User or Password is not valid";

    public AgileProjectPage(AppiumDriver driver) {
        appiumDriver = driver;
    }

    public void enterCredentials(String login, String password) {
        appiumDriver.findElement(loginInput).sendKeys(login);
        appiumDriver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        appiumDriver.getKeyboard().pressKey(Keys.ENTER);
    }

    public String getWelcomeMessage() {
        return appiumDriver.findElement(welcomeMessage).getText();
    }

    public boolean inputFieldIsVisible() {
        return appiumDriver.findElement(loginInput).isDisplayed();
    }


}
