package page.Object;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is page factory of Login page. It contains all elements present on page
 */

public class LoginPageObject {

    @AndroidFindBy(xpath = "//*[@resource-id='ap_email_login']")
    public MobileElement txt_userName;

    @AndroidFindBy(xpath = "//*[@text='Continue']")
    public MobileElement btn_continue;

    @AndroidFindBy(xpath = "//*[@resource-id='ap_password']")
    public MobileElement txt_password;

    @AndroidFindBy(xpath = "//*[@resource-id='signInSubmit']")
    public MobileElement btn_login;
}
