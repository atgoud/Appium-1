package com.sayem.appium.pagefactory.framework.browser;

import com.sayem.appium.pagefactory.framework.config.TimeoutsConfig;

public class test {
    private final TimeoutsConfig timeoutsConfig;

    public test(TimeoutsConfig timeoutsConfig) {
        this.timeoutsConfig = timeoutsConfig;
    }

    public TimeoutsConfig getTimeoutsConfig() {
        return timeoutsConfig;
    }
}
