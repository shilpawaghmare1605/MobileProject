package page.Object;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is page factory of Checkout page. It contains all elements present on page
 */

public class CheckOutPageObject {

    @AndroidFindBy(xpath = "//*[@resource-id='activeCartViewForm']//android.widget.TextView")
    public MobileElement productName;

    @AndroidFindBy(xpath = "//*[@resource-id='activeCartViewForm']//android.widget.ListView/android.view.View")
    public MobileElement productPrice;

    @AndroidFindBy(xpath = "//android.view.View[contains(@resource-id,'quantity-label')]//android.widget.TextView")
    public MobileElement productQty;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed to Buy']")
    public MobileElement proceedToBuy;
}
