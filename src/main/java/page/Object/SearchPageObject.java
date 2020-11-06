package page.Object;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * This is page factory of Search page. It contains all elements present on page
 */

public class SearchPageObject {

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
    public MobileElement txt_searchBar;

    @AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
    public WebElement searchText;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_results_count_text")
    public MobileElement resultCount;

    @AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_vertical_stack_view']/android.widget.LinearLayout")
    public List<MobileElement> currentSearchResultDisplay;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/action_bar_burger_icon']")
    public MobileElement lnk_burgerIcon;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/drawer_item_title']")
    public MobileElement btn_home;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/gno_greeting_text_view']")
    public MobileElement lbl_firstName;

    @AndroidFindBy(xpath="//*[@resource-id='title_feature_div']/android.view.View")
    public MobileElement lbl_productName;

    @AndroidFindBy(xpath="//*[@resource-id='atfRedesign_priceblock_priceToPay']/android.widget.EditText")
    public MobileElement lbl_productPrice;

    @AndroidFindBy(xpath="//*[@resource-id='add-to-cart-button']")
    public MobileElement btn_addToCart;

    @AndroidFindBy(xpath="//*[@resource-id='a-autoid-1-announce']")
    public MobileElement btn_proceedToCheckout;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/action_bar_cart_count']")
    public MobileElement cartCount;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/action_bar_cart_count']")
    public MobileElement lnk_checkOutCart;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count']")
    public MobileElement checkOutCartCount;

    @AndroidFindBy(xpath="//*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image']")
    public MobileElement btn_cart;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Delete']")
    public List<MobileElement> btn_delete;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='a-autoid-0-announce']")
    public MobileElement btn_deliverToThisAdd;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
    public MobileElement btn_continue;

}
