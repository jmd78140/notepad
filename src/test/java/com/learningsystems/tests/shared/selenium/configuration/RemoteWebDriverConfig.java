package com.learningsystems.tests.shared.selenium.configuration;


import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.learningsystems.tests.shared.selenium.annotations.LazyConfiguration;
import com.learningsystems.tests.shared.selenium.annotations.WebdriverScopeBean;

@Profile("grid")
@LazyConfiguration
public class RemoteWebDriverConfig {
    @Value("${selenium.grid.url}")
    private URL url;

    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    @Primary
    public WebDriver remoteFirefoxDriver(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new RemoteWebDriver(this.url, firefoxOptions);
    }

    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    @Primary
    public WebDriver remoteEdgeDriver(){
        EdgeOptions edgeOptions = new EdgeOptions();
        return new RemoteWebDriver(this.url, edgeOptions);
    }

    @WebdriverScopeBean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    @Primary
    public WebDriver remoteChromeDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        return new RemoteWebDriver(this.url, chromeOptions);
    }
}