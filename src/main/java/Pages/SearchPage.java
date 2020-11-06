package Pages;

import HelperUtility.AppiumHelper;
import HelperUtility.ReadExcelSheetData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import page.Object.SearchPageObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is logic layer of Search and SearchResults page. It contains all functions required to interact with Search page to verify functionality
 */

public class SearchPage extends AppiumHelper {

    static Logger log = LogManager.getLogger(SearchPage.class);
    Map<String,String> productSearch;

    public SearchPageObject searchPageObject = new SearchPageObject();
    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), searchPageObject);
        productSearch= ReadExcelSheetData.readTestDataSheet("ProductSearch.xlsx");
    }

    @Step("Navigate to Search Bar")
    public void navigateToSearchBar()
    {
        waitForPageToLoad(searchPageObject.txt_searchBar);
        searchPageObject.txt_searchBar.click();
        log.info("Clicked on Search Bar");
    }

    @Step("Reset cart if product is already added")
    public SearchPage resetCart()
    {
        waitForPageToLoad(searchPageObject.cartCount);
        int cartCount=Integer.parseInt(searchPageObject.cartCount.getText());
        if(cartCount>0)
        {
            searchPageObject.cartCount.click();
            List<MobileElement> btnDelete = searchPageObject.btn_delete;
            log.info("Total number of items are present into cart :"+btnDelete.size());
            for(int i=0;i<btnDelete.size();i++)
            {
                btnDelete.get(i).click();
            }
        }
        return this;
    }

    @Step("Navigate to Home page")
    public SearchPage navigateToHomePage()
    {
        waitForPageToLoad(searchPageObject.lnk_burgerIcon);
        searchPageObject.lnk_burgerIcon.click();

        waitForPageToLoad(searchPageObject.btn_home);
        searchPageObject.btn_home.click();
        log.info("Clicked on Home Page present on Menu Drawer menu");
        return this;
    }

    @Step("Search Product")
    public SearchPage searchByProductName() {
        Actions action = new Actions(driver);
        try {
            navigateToSearchBar();

            Thread.sleep(5000);
            waitForElementsToAppear(searchPageObject.searchText);
            searchPageObject.searchText.sendKeys(productSearch.get("ProductName"));

            action.sendKeys(Keys.ENTER).perform();
            log.info(productSearch.get("ProductName")  +": text is entered to search");
        }
        catch(StaleElementReferenceException e){
            log.error("Exception occurred in searchByProductName() method" +e.getMessage());
        }
        catch (Exception e)
        {
            log.error("Exception occurred in searchByProductName() method()"+e.getMessage() );
            // throw(e);
        }
        return this;
    }

    @Step("Select Product random basis from Search Results")
    public SearchPage selectRandomProduct()
    {
        try {
            waitForPageToLoad(searchPageObject.resultCount);
            int searchCount = Integer.parseInt(searchPageObject.resultCount.getText().replaceAll("[^0-9]", ""));
            List<MobileElement> searchResult = searchPageObject.currentSearchResultDisplay;
            int randomNumber = getRandomIntInclusive(2, searchResult.size())-1;
            searchResult.get(randomNumber).click();
            log.info("Product has been selected");
           /* if (searchCount > 10) {
                scroll(171, 1860, 171, 650);
                List<MobileElement> searchResult = searchPageObject.currentSearchResultDisplay;
                int randomNumber = getRandomIntInclusive(1, searchResult.size())-1;
                searchResult.get(randomNumber).click();
            }*/
        }
        catch (Exception e)
        {
            log.error("Exception occurred in selectRandomProduct() method()" );
            throw(e);
        }
        return this;
    }

    @Step("Get selected Product Details ")
    public HashMap getProductDetails()
    {
        String price;
        HashMap<String, String> product;
        try {
            waitForElementsToAppear(searchPageObject.lbl_productName);
            String prodName=searchPageObject.lbl_productName.getText();

            if(!searchPageObject.lbl_productPrice.isDisplayed())
            {
                navigateToHomePage();
                searchByProductName();
                selectRandomProduct();
                getProductDetails();
            }
            price=searchPageObject.lbl_productPrice.getText().substring(7).replaceAll("[^0-9]", "");
            log.info("Product Name :"+ prodName);
            log.info("Product price: "+price);
            product = new HashMap<String, String>();
            product.put("productName",prodName);
            product.put("productPrice",price);

        }
        catch (Exception e) {
            log.error("Exception occurred in getProductDetails() method()" );
            throw(e);
        }
        return product;
    }

    @Step("Add product into Cart")
    public SearchPage addProductToCart()
    {
        try
        {
            scrollTo("Add to Cart");
            searchPageObject.btn_addToCart.click();
            log.info("Clicked on 'Add to Cart' button");
        }
        catch(Exception e){
            log.error("Exception occurred in addProductToCart() method()");
            throw(e);
        }
        return this;
    }

    @Step("Navigate to Checkout page")
    public CheckOutPage navigateToCheckout()
    {
        int cartCount=0;
        try{
            if(searchPageObject.btn_proceedToCheckout.isDisplayed())
            {
                waitForPageToLoad(searchPageObject.lnk_checkOutCart);
                cartCount=Integer.parseInt(searchPageObject.lnk_checkOutCart.getText());
                Assert.assertTrue(cartCount>0);
                searchPageObject.lnk_checkOutCart.click();
                log.info("Clicked on 'Cart' button");
            }
            else{
                waitForPageToLoad(searchPageObject.checkOutCartCount);
                cartCount=Integer.parseInt(searchPageObject.checkOutCartCount.getText());
                Assert.assertTrue(cartCount>0);
                searchPageObject.btn_cart.click();
                log.info("Clicked on 'Cart' button");
            }
        }
        catch(Exception e) {
            log.error("Exception occurred in navigateToCheckout() method()" );
            throw(e);
        }
        return new CheckOutPage(driver);
    }

}
