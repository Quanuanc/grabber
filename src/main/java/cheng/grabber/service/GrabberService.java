package cheng.grabber.service;

import cheng.grabber.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GrabberService {
    private static final String BOSS_URL = "https://www.zhipin.com/web/geek/job?query=%s&page=%d";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final WebDriver webDriver;

    public GrabberService(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public int getPageCount(String keyword) {
        String url = String.format(BOSS_URL, keyword, 1);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        webDriver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement optionPages = webDriver.findElement(By.className("options-pages"));
        int lastPage = Utils.getLastPageNum(optionPages);
        log.info("There has {} pages about Java", lastPage);

        return lastPage;
    }
}
