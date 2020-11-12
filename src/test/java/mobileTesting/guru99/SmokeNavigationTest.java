package mobileTesting.guru99;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SmokeNavigationTest {

    AppiumDriver appiumDriver;
    HomePage homePage;
    NewToursPage newToursPage;
    TablePage tablePage;

    String homePageTitle = "Guru99 Bank Home Page";
    String toursPageTitle = "Welcome: Mercury Tours";

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a API 30");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("chromedriverExecutableDir",
                "C:\\Users\\iulia\\.m2\\repository\\webdriver\\chromedriver\\win32\\86.0.4240.22");

        URL serverAddress = new URL("http://0.0.0.0:4723/wd/hub");
        appiumDriver = new AndroidDriver(serverAddress, capabilities);
    }

    @BeforeMethod
    public void homePageNavigation() {
        homePage = new HomePage(appiumDriver);
        homePage.navigate();
    }

    @Test
    public void newToursPageNavigation() {
        homePage.clickOnNewToursButton();
        //assertEquals(toursPageTitle, newToursPage.getPageTitle());
        assertTrue(newToursPage.isMainMenuPresent());
    }

    @Test
    public void tablePageNavigation() {
        homePage.navigateToAgilePage();
        homePage.clickOnTableDemoLink();
        assertTrue(true, tablePage.getPageTitle());
        assertTrue(tablePage.isTablePresent());
    }

    @AfterClass
    public void closeApp() {
        appiumDriver.closeApp();
    }

}
