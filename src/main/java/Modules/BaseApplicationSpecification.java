package Modules;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 This class setup the driver as per platform details provided in config.properties file.
 */

public class BaseApplicationSpecification {

    static String testingType;
    static String deviceType;
    static String deviceName;
    static String platformName;
    static String platformVersion;
    static String uniqueDeviceId;
    static String appPackage;
    static String appActivity;
    static String apk;
    static String screenRotation;
    static AppiumDriverLocalService appiumService;
    static AppiumDriver<MobileElement> driver;
    static Logger log = LogManager.getLogger(BaseApplicationSpecification.class);

     static void setup() throws IOException
    {
        PropertyConfigurator.configure("log4j.properties");
        Properties properties=new Properties();
        String fileName =System.getProperty("user.dir")+"\\src\\test\\resources\\Config Files\\config.properties";
        InputStream inputStream = new FileInputStream(fileName);
        properties.load(inputStream);

        testingType=properties.getProperty("testingType");
        apk=properties.getProperty("apk");
        deviceType=properties.getProperty("deviceType");
        deviceName=properties.getProperty("deviceName");
        platformName= properties.getProperty("platformName");
        platformVersion=properties.getProperty("platformVersion");
        uniqueDeviceId=properties.getProperty("uniqueDeviceId");
        appPackage=properties.getProperty("appPackage");
        appActivity=properties.getProperty("appActivity");
        screenRotation=properties.getProperty("screenRotation");

    }

    public static AppiumDriver<MobileElement> configureDriver() throws IOException
    {
        setup();

        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.UDID, uniqueDeviceId);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        if(apk.equals("yes"))
        {
            File f=new File("C:\\Users\\shilpaw\\Documents\\OTHER\\SampleMobilePorject\\src\\main\\resources\\Apk");
            File fs=new File(f,"Amazon_shopping.apk");
            cap.setCapability(MobileCapabilityType.APP,fs.getAbsolutePath());
        }
        else
        {
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        }
        if(platformName.equals("Android")) {
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            cap.setCapability("deviceOrientation", "portrait");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        }

        startAppiumService();

        log.info("Completed DesiredCapabilities");
        driver=new AndroidDriver<>(appiumService.getUrl(),cap);
        log.info("Completed AndroidDriver configuration");
        return driver;
    }

    static void startAppiumService() {
           if (appiumService != null && appiumService.isRunning()) {
              log.info("Appium server is already running");
               return;
           }
       String nodeExePath="C:/Program Files/nodejs/node.exe";
       String appiumJSPath="C:/Program Files/Appium/resources/app/node_modules/appium/build/lib/main.js";

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(AppiumServiceBuilder.BROADCAST_IP_ADDRESS)
                .usingAnyFreePort()
                .usingDriverExecutable(new File(nodeExePath))
                .withAppiumJS(new File(appiumJSPath))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        appiumService = AppiumDriverLocalService.buildService(builder);

        appiumService.start();
        log.info("Appium server started running");
    }

    public static void stopAppiumService(){
        if (appiumService != null && appiumService.isRunning()){
            appiumService.stop();
            log.info("Appium server stopped running");
        }
    }
}
