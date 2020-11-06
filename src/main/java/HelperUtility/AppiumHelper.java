package HelperUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppiumHelper {
    public AppiumDriver driver;
    static Logger log = LogManager.getLogger(AppiumHelper.class);

    public AppiumHelper(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void waitForPageToLoad(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(id));
    }

    public void waitForElementsToAppear(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(id));
    }

    public WebElement waitForElement(WebElement arg) {
        waitForPageToLoad(arg);
        WebElement el = arg;
        return el;
    }

    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        new TouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endx,starty)).release().perform();
    }

    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.8);
        int endx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        new TouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endx,starty)).release();
    }


    public void scroll(int sourceX, int sourceY, int destinationX, int destinationY) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(sourceX, sourceY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(destinationX, destinationY))
                .release()
                .perform();
    }

    public MobileElement scroll(MobileElement sourceElement, String elementText,String scrollBy)
    {
        String locator;
        switch (scrollBy) {
            case "TEXT":
                locator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+elementText+"\"))";
                break;
            case "INDEX":
                locator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().index("+Integer.parseInt(elementText)+"))";
                break;
            case "RESOURCE_ID":
                locator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\""+elementText+"\"))";
                break;
            default:
                locator = null;
        }
        if (driver instanceof AndroidDriver) {
            return sourceElement.findElement(MobileBy.AndroidUIAutomator(locator));
        }
        return null;
    }


    public static int getRandomIntInclusive(double min, double max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        double randomNum=Math.floor(Math.random() * (max - min + 1)) + min;
        return (int)randomNum;
    }


    public void scrollTo(String text)
    {
        ((AndroidDriver)driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
    }

    public void rotateScreen(boolean screenRotation)
    {
      //  ScreenOrientation orientation = driver.getOrientation();
        if(screenRotation)
            driver.rotate(ScreenOrientation.LANDSCAPE);
        else
            driver.rotate(ScreenOrientation.PORTRAIT);
    }
}
