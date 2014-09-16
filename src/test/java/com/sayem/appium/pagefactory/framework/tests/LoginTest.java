package com.sayem.appium.pagefactory.framework.tests;

import com.sayem.appium.pagefactory.framework.browser.mobile.MobileBrowser;
import com.sayem.appium.pagefactory.framework.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest{

    @Test
    public void loginTest(LoginPage loginPage) throws Exception {
        MobileBrowser mobile = BaseTest.invoke();
        loginPage.login();
        mobile.quit();
    }
}
