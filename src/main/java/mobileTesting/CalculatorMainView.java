package mobileTesting;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class CalculatorMainView {

    AppiumDriver appiumDriver;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'digit_3')]")
    public MobileElement firstDigit;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'dec_point')]")
    public MobileElement dotSign;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'digit_5')]")
    public MobileElement fractionalToFirstDigit;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'digit_2')]")
    public MobileElement secondDigitPart1;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'digit_0')]")
    public MobileElement secondDigitPart2;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'op_add')]")
    public MobileElement plusSign;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'op_mul')]")
    public MobileElement multiplySign;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'op_pct')]")
    public MobileElement percentageSign;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'eq')]")
    public MobileElement resultSign;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'result_final')]")
    public MobileElement actualResultLine;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'clr')]")
    public MobileElement clearAllButton;


    public CalculatorMainView(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
    }

    public void cleanFields() {
        clearAllButton.click();
    }

    public void clickFirstDigit() {
        firstDigit.click();
        dotSign.click();
        fractionalToFirstDigit.click();
    }

    public void clickPlusButton() {
        plusSign.click();
    }

    public void clickSecondDigit() {
        secondDigitPart1.click();
        secondDigitPart2.click();
    }

    public void clickResultButton() {
        resultSign.click();
    }

    public String getActualResult() {
        return actualResultLine.getText();
    }

    public void clickMultiplyButton() {
        multiplySign.click();
    }

    public void clickPercentageButton() {
        percentageSign.click();
    }
}
