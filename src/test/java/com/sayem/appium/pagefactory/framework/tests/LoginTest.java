package com.sayem.appium.pagefactory.framework.tests;

import com.sayem.appium.pagefactory.framework.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @BeforeMethod
    public void setUp() throws Exception {
        mobile = android();
    }

    @Test
    public void loginTest() throws Exception {
        mobile.loadTopLevelPage(LoginPage.class)
                .login();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        mobile.quit();
    }
}
