package com.sayem.appium.pagefactory.framework.tests;

import com.sayem.appium.pagefactory.framework.browser.MobileBrowserBuilder;
import com.sayem.appium.pagefactory.framework.browser.mobile.MobileBrowser;
import com.sayem.appium.pagefactory.framework.config.TimeoutType;
import com.sayem.appium.pagefactory.framework.config.TimeoutsConfig;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class WhatsAppTest {

    @Test
    public void whatsAppTest() throws Exception {

        TimeoutsConfig timeouts = TimeoutsConfig.builder()
                .pageLoadTimoutSeconds(10)
                .implicitWaitTimeoutMillis(2000)
                .build();

        MobileBrowser mobileBrowser = MobileBrowserBuilder.getAndroidBuilder("http://127.0.0.1:4723/wd/hub")

                .withTimeoutsConfig(timeouts)
                .withPlatform("MAC")
                .withPlatformVersion("4.4")
                .withDeviceName("Moto X")
                .withApp("/Users/ssayem/Downloads/Mobile/src/test/resources/com.whatsapp.apk")
                .withAppPackage("com.whatsapp")
                .withAppActivity("com.whatsapp.Main")
                .build();

        mobileBrowser.getActions().click(
                By.id("com.whatsapp:id/eula_accept"), TimeoutType.DEFAULT);
        mobileBrowser.quit();
    }

}