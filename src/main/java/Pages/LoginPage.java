package Pages;

import HelperUtility.AppiumHelper;
import HelperUtility.ReadExcelSheetData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import page.Object.LoginPageObject;

import java.util.Map;

/**
 * This class is logic layer of Login page. It contains all functions required to interact with  login page to verify functionality
 */

public class LoginPage extends AppiumHelper {

    static Logger log = LogManager.getLogger(LoginPage.class);
    public LoginPageObject loginPageObject = new LoginPageObject();
    Map<String,String> loginDetails;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObject);
        loginDetails=ReadExcelSheetData.readTestDataSheet("LoginDetails.xlsx");
    }

    @Step("Enter Username ")
    public void enterUsername() {
        try {
            waitForPageToLoad(loginPageObject.btn_continue);
            log.info(loginDetails.get("Username"));
            loginPageObject.txt_userName.sendKeys(loginDetails.get("Username"));
            log.info("Username :"+loginDetails.get("Username"));
            loginPageObject.btn_continue.click();
            log.info("Email or Phone number is entered and clicked on Continue button");
        }
        catch (Exception e)
        {
            log.error("Exception occurred in enterUsername() method()" );
            throw(e);
        }
    }

    @Step("Enter Password and login")
    public void enterPassword()
    {
        try {
            waitForPageToLoad(loginPageObject.btn_login);
            log.info(loginDetails.get("Password"));
            loginPageObject.txt_password.sendKeys(loginDetails.get("Password"));
            log.info("Password :"+loginDetails.get("Password"));
            loginPageObject.btn_login.click();
            log.info("Password is entered and clicked on Login button");
        } catch (Exception e) {
            log.error("Exception occurred in enterPassword() method()" );
            throw(e);
        }
    }

    public SearchPage login()  {
        enterUsername();
        enterPassword();
        return new SearchPage(driver);
    }
}
