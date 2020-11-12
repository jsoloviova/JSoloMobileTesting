package mobileTesting.guru99;

import io.appium.java_client.AppiumDriver;

public class BasePage {

    AppiumDriver appiumDriver;

    public String getPageTitle() {
        return appiumDriver.getTitle();
    }
}
