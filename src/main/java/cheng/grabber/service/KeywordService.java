package cheng.grabber.service;

import cheng.grabber.domain.Job;
import cheng.grabber.domain.Keyword;
import cheng.grabber.repo.JobRepository;
import cheng.grabber.repo.KeywordRepository;
import cheng.grabber.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class KeywordService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final JobRepository jobRepository;
    private final KeywordRepository keywordRepository;
    private final String zhipinBaseUrl;
    private final String zhipinQueryParam;
    private final WebDriver webDriver;
    private final Map<String, String> cityMap;

    public KeywordService(JobRepository jobRepository, KeywordRepository keywordRepository, String zhipinBaseUrl, String zhipinQueryParam, WebDriver webDriver, @Qualifier("cityMap") Map<String, String> cityMap) {
        this.jobRepository = jobRepository;
        this.keywordRepository = keywordRepository;
        this.zhipinBaseUrl = zhipinBaseUrl;
        this.zhipinQueryParam = zhipinQueryParam;
        this.webDriver = webDriver;
        this.cityMap = cityMap;
    }

    public Keyword saveKeyword(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    public void deleteKeyword(Integer id) {
        keywordRepository.deleteById(id);
    }

    public Keyword findKeyword(Integer id) {
        return keywordRepository.findById(id).orElse(null);
    }

    public Iterable<Keyword> findAllKeywords() {
        return keywordRepository.findAll();
    }

    public int getPageCount(String str, String city) {
        String cityCode = cityMap.getOrDefault(city, null);
        if (cityCode == null) return 0;

        String queryParam = String.format(zhipinQueryParam, str, cityCode, 1);
        Utils.waitUntilPageComplete(webDriver, zhipinBaseUrl, queryParam);

        WebElement optionPages = webDriver.findElement(By.className("options-pages"));
        int lastPage = Utils.getLastPageNum(optionPages);
        log.info("There has {} pages about {}", lastPage, str);
        return lastPage;
    }

    public Keyword addKeyword(String str, String city) {
        String cityCode = cityMap.getOrDefault(city, null);
        if (cityCode == null) return new Keyword();

        String queryParam;

        int lastPage = getPageCount(str, city);

        Keyword keyword = new Keyword();
        keyword.setKeyword(str);
        keyword.setCity(city);
        keyword.setCreateTime(LocalDateTime.now());

        keywordRepository.save(keyword);

        for (int i = 1; i <= lastPage; i++) {
            queryParam = String.format(zhipinQueryParam, str, cityCode, i);
            Utils.waitUntilPageComplete(webDriver, zhipinBaseUrl, queryParam);

            WebElement jobListBox = webDriver.findElement(By.className("job-list-box"));
            List<WebElement> jobList = jobListBox.findElements(By.cssSelector("li[ka]"));

            for (WebElement ele : jobList) {
                Job job = Utils.parseJob(ele);
                keyword.addJob(job);
                jobRepository.save(job);
                log.info("{} | {} | {}", job.getName(), job.getSalary(), job.getCompanyName());
            }
        }

        return keyword;
    }
}
