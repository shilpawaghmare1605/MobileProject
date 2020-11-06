package Pages;

import HelperUtility.AppiumHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import page.Object.CheckOutPageObject;

import java.util.HashMap;

/**
 * This class is logic layer of Checkout page. It contains all functions required to interact with Checkout page to verify functionality
 */

public class CheckOutPage extends AppiumHelper {

    static Logger log = LogManager.getLogger(CheckOutPage.class);

    public CheckOutPageObject checkOutPageObject = new CheckOutPageObject();

    public CheckOutPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), checkOutPageObject);
    }

    @Step("Verify Product details between Selected product and Cart ")
    public void verifyProductDetails(HashMap productDetails)
    {
        waitForPageToLoad(checkOutPageObject.proceedToBuy);

        //Check out page
        String checkOutProductName=checkOutPageObject.productName.getText();
        String checkOutPrice=checkOutPageObject.productPrice.getText();
        String checkOutProductPrice=checkOutPrice.substring(0, checkOutPrice.indexOf(".")).replaceAll("[^0-9]", "");
        String productQty=checkOutPageObject.productQty.getText();

        //Product Detail page
        String productNameOnDetailPage=productDetails.get("productName").toString();
        String productPriceOnDetailPage=productDetails.get("productPrice").toString();

        log.info("ProductName on ProductDetail page is:" +productNameOnDetailPage);
        log.info("ProductName on CheckOut page is:" +checkOutProductName);
        log.info("Product Price on ProductDetail page is:" +productPriceOnDetailPage);
        log.info("Product Price on CheckOut page is:" +checkOutProductPrice);

        Assert.assertTrue(productNameOnDetailPage.substring(0,20).contains(checkOutProductName.substring(0,20)));
        Assert.assertTrue(productPriceOnDetailPage.contains(checkOutProductPrice));
        Assert.assertEquals(productQty,"1");
        log.info("Verification has been performed on Product Name, Product Price and Product Qty");
    }
}
