package cheng.grabber.runner;

import cheng.grabber.domain.Job;
import cheng.grabber.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.Duration;
import java.util.List;

//@Component
public class BossRunner implements ApplicationRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String BOSS_URL = "https://www.zhipin.com/web/geek/job?query=%s&page=%d";

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        String keyword = "Golang";
        Integer firstPage = 1;
        String url = String.format(BOSS_URL, keyword, firstPage);

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(url);
        Thread.sleep(5000);

        WebElement optionPages = driver.findElement(By.className("options-pages"));
        int lastPage = Utils.getLastPageNum(optionPages);
        log.info("There has {} pages about Java", lastPage);

        for (int i = 1; i <= lastPage; i++) {
            url = String.format(BOSS_URL, keyword, i);
            driver.get(url);
            Thread.sleep(5000);


            WebElement jobListBox = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[2]/div[2]/div/div[1]/div[1]/ul"));
            List<WebElement> jobList = jobListBox.findElements(By.cssSelector("li[ka]"));

            for (WebElement ele : jobList) {
                Job job = Utils.parseJob(ele);
                log.info(job.toString());
            }
        }

        driver.quit();
    }


}
