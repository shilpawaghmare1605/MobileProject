package LoginVerification;

import Listener.ListenerTest;
import Modules.BaseApplicationSpecification;
import Pages.WelcomePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;

@Listeners(ListenerTest.class)
public class Login extends BaseApplicationSpecification {

    AppiumDriver<MobileElement> driver;
    WelcomePage loginPage;

    @BeforeTest
    public void setup() throws IOException {
        driver=configureDriver();
        loginPage=new WelcomePage(driver);
    }

    @Test
    @Description("Verification of login")
    public void verifyUserLogin()
    {
        loginPage.navigateToLogin()
                 .login();
    }

}
