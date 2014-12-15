package com.sayem.appium.pagefactory.framework.browser.mobile;

/**
 * Created by Tapan on 8/13/14.
 */

public enum MobilePlatformName {
    IOS("iOS"),
    ANDROID("Android");

    private String platformName;

    private MobilePlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformName() {
        return platformName;
    }
}
