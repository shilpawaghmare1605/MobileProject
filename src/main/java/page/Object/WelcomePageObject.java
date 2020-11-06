package page.Object;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is page factory of Welcome page. It contains required elements present on page
 */

public class WelcomePageObject {

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/signin_to_yourAccount")
    public MobileElement lbl_heading;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
    public MobileElement btn_signIn;
}
