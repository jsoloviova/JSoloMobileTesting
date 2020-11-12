package mobileTesting;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;

import static org.testng.Assert.assertEquals;

public class CalculatorTest {

    AppiumDriver appiumDriver;
    CalculatorMainView calculatorMainView;

    // a=3.5 , b=20
    String sumER = "23.5";
    String multiplyingER = "70";
    String percentageER = "0.7";

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a API 30");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");

        URL serverAddress = new URL("http://0.0.0.0:4723/wd/hub");

        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
        capabilities.setCapability("appPackage", "com.google.android.calculator");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        appiumDriver = new AndroidDriver(serverAddress, capabilities);
        appiumDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    @BeforeMethod
    public void calculatorMain() {
        calculatorMainView = new CalculatorMainView(appiumDriver);
    }

    @Test
    public void sumTest() {
        calculatorMainView.clickFirstDigit();
        calculatorMainView.clickPlusButton();
        calculatorMainView.clickSecondDigit();
        calculatorMainView.clickResultButton();
        assertEquals(sumER, calculatorMainView.getActualResult(),
                "AR: " + calculatorMainView.getActualResult() + " doesn't match ER: " + sumER);
    }

    @Test
    public void multiplicationTest() {
        calculatorMainView.clickFirstDigit();
        calculatorMainView.clickMultiplyButton();
        calculatorMainView.clickSecondDigit();
        calculatorMainView.clickResultButton();
        assertEquals(multiplyingER, calculatorMainView.getActualResult(),
                "AR: " + calculatorMainView.getActualResult() + " doesn't match ER: " + multiplyingER);
    }

    @Test
    public void percentageTest() {
        calculatorMainView.clickFirstDigit();
        calculatorMainView.clickMultiplyButton();
        calculatorMainView.clickSecondDigit();
        calculatorMainView.clickPercentageButton();
        calculatorMainView.clickResultButton();
        assertEquals(percentageER, calculatorMainView.getActualResult(),
                "AR: " + calculatorMainView.getActualResult() + " doesn't match ER: " + percentageER);
    }

    @AfterMethod
    public void fieldsCleaning() {
        calculatorMainView = new CalculatorMainView(appiumDriver);
        calculatorMainView.cleanFields();
    }

    @AfterClass
    public void closeApp() {
        appiumDriver.closeApp();
    }
}
