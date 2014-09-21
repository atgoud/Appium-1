package com.sayem.appium.pagefactory.framework.tests;

import com.sayem.appium.pagefactory.framework.browser.MobileBrowserBuilder;
import com.sayem.appium.pagefactory.framework.browser.mobile.MobileBrowser;
import com.sayem.appium.pagefactory.framework.config.TimeoutsConfig;
import com.sayem.appium.pagefactory.framework.exception.WebDriverException;

public abstract class BaseTest {

    protected MobileBrowser mobile;

    public static MobileBrowser android() throws WebDriverException {

        TimeoutsConfig timeouts = TimeoutsConfig.builder()
                .pageLoadTimoutSeconds(10)
                .implicitWaitTimeoutMillis(20000)
                .build();

        return MobileBrowserBuilder.getAndroidBuilder("http://127.0.0.1:4723/wd/hub")
                .withTimeoutsConfig(timeouts)
                .withPlatformVersion("4.4")
                .withDeviceName("Moto X")
                .withApp("/Users/ssayem/IdeaProjects/Appium/src/test/resources/app-debug-unaligned.apk")
                .withAppPackage("com.company.companyandroid")
                .withAppActivity("com.company.android.activities.HomeActivity")
                .build();
    }
}