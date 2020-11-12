package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    By mainElement = By.xpath("//div[@id='navbar-brand-centered']");
    By agileProjectButton = By.xpath("//a[contains(@href,'Agile')]");
    By seleniumDropdown = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Selenium')]");
    //By newToursButton = By.xpath("//a[text()='Agile Project']");
    By tableDemoButton = By.xpath("//a[text()='Table Demo']");
    By loginForm = By.xpath("form[name='frmLogin']");
    By newToursButton = By.xpath("//a[text()='New Tours']");
    By navMenuItems = By.xpath("//li[@class='dropdown']/a");

    public HomePage(AppiumDriver driver) {
        appiumDriver = driver;
    }

    public void navigate() {
        appiumDriver.get("http://demo.guru99.com");
    }

    public void navigateToAgilePage() {
        appiumDriver.findElement(agileProjectButton).click();
    }

    public boolean isLoginFormPresent() {
        return appiumDriver.findElement(loginForm).isDisplayed();
    }

    public void clickOnNewToursButton() {
        appiumDriver.findElement(newToursButton).click();
    }

    public void clickOnTableDemoLink() {
        appiumDriver.findElement(seleniumDropdown).click();
        appiumDriver.findElement(tableDemoButton).click();
    }
}
