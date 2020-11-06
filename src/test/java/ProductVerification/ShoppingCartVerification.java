package ProductVerification;

import Listener.ListenerTest;
import Modules.BaseApplicationSpecification;
import Pages.SearchPage;
import Pages.WelcomePage;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

@Listeners(ListenerTest.class)
public class ShoppingCartVerification extends BaseApplicationSpecification
{
    AppiumDriver driver;
    WelcomePage loginPage;

    @BeforeTest
    public void setup() throws IOException {
        driver=configureDriver();
        loginPage=new WelcomePage(driver);
    }

    @Test
    @Description("Verification of Search product and Add to cart Screen")
    public void verifyShoppingCart()  {

        SearchPage search=loginPage.navigateToLogin()
                                   .login()
                                   .resetCart()
                                   .navigateToHomePage()
                                   .searchByProductName()
                                   .selectRandomProduct();

        HashMap productDetails=search.getProductDetails();

                        search.addProductToCart()
                               .navigateToCheckout()
                               .verifyProductDetails(productDetails);

    }
}
