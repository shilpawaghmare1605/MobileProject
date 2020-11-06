package Pages;

import HelperUtility.AppiumHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import page.Object.WelcomePageObject;

/**
 * This class is logic layer of Welcome page. It contains all functions required to interact with Welcome page to verify functionality
 */

public class WelcomePage extends AppiumHelper
{
    static Logger log = LogManager.getLogger(WelcomePage.class);
    public WelcomePageObject loginPageObject = new WelcomePageObject();

    public WelcomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObject);
    }

    @Step("Navigate to Login page")
    public LoginPage navigateToLogin()
    {
        waitForPageToLoad(loginPageObject.lbl_heading);
        loginPageObject.lbl_heading.isDisplayed();
        loginPageObject.btn_signIn.click();
        log.info("Clicked on 'Sign In' button");
        return new LoginPage(driver);
    }
}
