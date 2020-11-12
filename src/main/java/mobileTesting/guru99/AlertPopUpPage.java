package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;

public class AlertPopUpPage extends BasePage {

    public AlertPopUpPage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
    }

    public void closeAlertMessage() {
        Alert alert = appiumDriver.switchTo().alert();
        alert.accept();
        //return appiumDriver.findElement(By.xpath("//android.widget.LinearLayout")).isDisplayed();
    }
}
