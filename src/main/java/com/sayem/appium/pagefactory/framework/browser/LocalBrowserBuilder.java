package com.sayem.appium.pagefactory.framework.browser;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.sayem.appium.pagefactory.framework.browser.web.*;
import com.sayem.appium.pagefactory.framework.config.TimeoutsConfig;
import com.sayem.appium.pagefactory.framework.exception.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * <p>Builder class for creating a Browser that is running on the same host as the test code.
 * Creates either a {@link com.sayem.appium.pagefactory.framework.browser.web.ChromeBrowser},
 * {@link com.sayem.appium.pagefactory.framework.browser.web.FirefoxBrowser}, or
 * {@link com.sayem.appium.pagefactory.framework.browser.web.InternetExplorerBrowser}.</p>
 * <p/>
 * <p>You can call the more general {@link #getBuilder(WebBrowserType, String)}, or the more specific methods
 * {@link #getChromeBuilder(String)}, {@link #getFirefoxBuilder(String)}, and {@link #getInternetExplorerBuilder(String)}.
 * <p/>
 * Then call the methods to add parameters, such as {@link #withBrowserBinaryPath(String)}, and finally call
 * {@link #build()} to create the Browser instance.
 * </p>
 * <p/>
 * <p>A Browser is basically a wrapper for a WebDriver that greatly simplifies configuration,
 * adds useful utilities, and has methods
 * for loading {@link com.sayem.appium.pagefactory.framework.pages.Page}'s.
 * <p/>
 * Pages provide an object-oriented solution to Selenium testing. You can write Page classes that model a web page
 * in the web app you are testing.</p>
 */

public class LocalBrowserBuilder {
    private static final Logger logger = LoggerFactory.getLogger(LocalBrowserBuilder.class);

    private final WebBrowserType browserType;
    private final String baseTestUrl;

    private TimeoutsConfig timeoutsConfig;

    private Optional<String> webDriverPath = Optional.absent();
    private Optional<String> browserBinaryPath = Optional.absent();
    private Optional<String> browserLocale = Optional.absent();
    private Optional<Integer> startWindowWidth = Optional.absent();
    private Optional<Integer> startWindowHeight = Optional.absent();
    private Optional<Level> browserLogLevel = Optional.absent();
    private Optional<String> browserLogFile = Optional.absent();

    private LocalBrowserBuilder(WebBrowserType browserType, String baseTestUrl) {
        this.browserType = Preconditions.checkNotNull(browserType, "You must provide a non-null browserType!");
        this.baseTestUrl = Preconditions.checkNotNull(baseTestUrl, "You must provide a non-null baseTestUrl!");
        this.timeoutsConfig = TimeoutsConfig.defaultTimeoutsConfig();
    }

    /**
     * Get a LocalBrowserBuilder for the given browser and base URL for the webapp you are testing against.
     *
     * @param browserType - type of Browser, either CHROME, FIREFOX, or IE
     * @param baseTestUrl - base URL for your webapp, e.g. http://my.site.com/base
     */
    public static LocalBrowserBuilder getBuilder(WebBrowserType browserType, String baseTestUrl) {
        return new LocalBrowserBuilder(browserType, baseTestUrl);
    }

    /**
     * Get a LocalBrowserBuilder for CHROME and base URL for the webapp you are testing against.
     *
     * @param baseTestUrl - base URL for your webapp, e.g. http://my.site.com/base
     */
    public static LocalBrowserBuilder getChromeBuilder(String baseTestUrl) {
        return new LocalBrowserBuilder(WebBrowserType.CHROME, baseTestUrl);
    }

    /**
     * Get a LocalBrowserBuilder for FIREFOX and base URL for the webapp you are testing against.
     *
     * @param baseTestUrl - base URL for your webapp, e.g. http://my.site.com/base
     */
    public static LocalBrowserBuilder getFirefoxBuilder(String baseTestUrl) {
        return new LocalBrowserBuilder(WebBrowserType.FIREFOX, baseTestUrl);
    }

    /**
     * Get a LocalBrowserBuilder for IE and base URL for the webapp you are testing against.
     *
     * @param baseTestUrl - base URL for your webapp, e.g. http://my.site.com/base
     */
    public static LocalBrowserBuilder getInternetExplorerBuilder(String baseTestUrl) {
        return new LocalBrowserBuilder(WebBrowserType.IE, baseTestUrl);
    }

    //------------Getters in case the client wants to inspect the config they have so far-----------
    public WebBrowserType getBrowserType() {
        return browserType;
    }

    public String getBaseTestUrl() {
        return baseTestUrl;
    }

    public TimeoutsConfig getTimeoutsConfig() {
        return timeoutsConfig;
    }

    public Optional<String> getWebDriverPath() {
        return webDriverPath;
    }

    public Optional<String> getBrowserBinaryPath() {
        return browserBinaryPath;
    }

    public Optional<String> getBrowserLocale() {
        return browserLocale;
    }

    public Optional<Integer> getStartWindowWidth() {
        return startWindowWidth;
    }

    public Optional<Integer> getStartWindowHeight() {
        return startWindowHeight;
    }

    public Optional<Level> getBrowserLogLevel() {
        return browserLogLevel;
    }

    public Optional<String> getBrowserLogFile() {
        return browserLogFile;
    }

    /**
     * Creates the Browser instance, which includes creating the actual Browser process via the underlying WebDriver.
     *
     * @return - a {@link com.sayem.appium.pagefactory.framework.browser.web.FirefoxBrowser},
     * {@link com.sayem.appium.pagefactory.framework.browser.web.ChromeBrowser},
     * or {@link com.sayem.appium.pagefactory.framework.browser.web.InternetExplorerBrowser}
     * @throws WebDriverException
     */
    public WebBrowser build() throws WebDriverException {
        logger.info("Building Local Browser with the following config: \n{}", toString());
        WebBrowser browser;
        switch (browserType) {
            case FIREFOX:
                browser = new FirefoxBrowser(baseTestUrl, timeoutsConfig, webDriverPath, browserBinaryPath, Optional.<String>absent(), browserLocale, startWindowWidth, startWindowHeight);
                break;
            case CHROME:
                browser = new ChromeBrowser(baseTestUrl, timeoutsConfig, webDriverPath, browserBinaryPath, Optional.<String>absent(), browserLocale, startWindowWidth, startWindowHeight,
                        browserLogLevel, browserLogFile);
                break;
            case IE:
                browser = new InternetExplorerBrowser(baseTestUrl, timeoutsConfig, webDriverPath, browserBinaryPath, Optional.<String>absent(), browserLocale, startWindowWidth, startWindowHeight,
                        browserLogLevel, browserLogFile);
                break;
            default:
                throw new IllegalArgumentException("Only Firefox, Chrome, and IE are currently supported!");
        }
        browser.initializeBrowser();
        return browser;
    }

    public LocalBrowserBuilder withTimeoutsConfig(TimeoutsConfig timeoutsConfig) {
        this.timeoutsConfig = timeoutsConfig == null ? TimeoutsConfig.defaultTimeoutsConfig() : timeoutsConfig;
        return this;
    }

    public LocalBrowserBuilder withWebDriverPath(String pathToWebDriver) {
        this.webDriverPath = Optional.fromNullable(pathToWebDriver);
        return this;
    }

    public LocalBrowserBuilder withBrowserBinaryPath(String pathToBrowserBinary) {
        this.browserBinaryPath = Optional.fromNullable(pathToBrowserBinary);
        return this;
    }

    public LocalBrowserBuilder withBrowserLocale(String browserLocale) {
        this.browserLocale = Optional.fromNullable(browserLocale);
        return this;
    }

    public LocalBrowserBuilder withStartWindowWidth(Integer startWindowWidth) {
        this.startWindowWidth = Optional.fromNullable(startWindowWidth);
        return this;
    }

    public LocalBrowserBuilder withStartWindowHeight(Integer startWindowHeight) {
        this.startWindowHeight = Optional.fromNullable(startWindowHeight);
        return this;
    }

    public LocalBrowserBuilder withBrowserLogLevel(Level browserLogLevel) {
        this.browserLogLevel = Optional.fromNullable(browserLogLevel);
        return this;
    }

    public LocalBrowserBuilder withBrowserLogFile(String browserLogFile) {
        this.browserLogFile = Optional.fromNullable(browserLogFile);
        return this;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("browserType", browserType)
                .add("baseTestUrl", baseTestUrl)
                .add("webDriverPath", webDriverPath)
                .add("browserBinaryPath", browserBinaryPath)
                .add("browserLocale", browserLocale)
                .add("startWindowWidth", startWindowWidth)
                .add("startWindowHeight", startWindowHeight)
                .add("browserLogLevel", browserLogLevel)
                .add("browserLogFile", browserLogFile)
                .toString();
    }
}
