package cheng.grabber.config;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    private static final Logger log = LoggerFactory.getLogger(WebDriverConfig.class);

    private WebDriver webDriver;

    @Bean
    public WebDriver webDriver() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.addArguments("--user-agent=" + userAgent);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        webDriver = new ChromeDriver(options);
        return webDriver;
    }

    @PreDestroy
    public void cleanupWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            log.info("Cleanup webDriver");
        }
    }
}
