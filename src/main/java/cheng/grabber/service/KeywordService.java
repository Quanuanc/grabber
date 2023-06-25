package cheng.grabber.service;

import cheng.grabber.domain.Job;
import cheng.grabber.domain.Keyword;
import cheng.grabber.repo.KeywordRepository;
import cheng.grabber.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KeywordService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final KeywordRepository keywordRepository;
    private final String zhipinBaseUrl;
    private final String zhipinQueryParam;
    private final WebDriver webDriver;

    public KeywordService(KeywordRepository keywordRepository, String zhipinBaseUrl, String zhipinQueryParam, WebDriver webDriver) {
        this.keywordRepository = keywordRepository;
        this.zhipinBaseUrl = zhipinBaseUrl;
        this.zhipinQueryParam = zhipinQueryParam;
        this.webDriver = webDriver;
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

    public int getPageCount(String str) {
        String queryParam = String.format(zhipinQueryParam, str, 1);
        Utils.waitUntilPageComplete(webDriver, zhipinBaseUrl, queryParam);

        WebElement optionPages = webDriver.findElement(By.className("options-pages"));
        int lastPage = Utils.getLastPageNum(optionPages);
        log.info("There has {} pages about {}", lastPage, str);
        return lastPage;
    }

    public Keyword getKeywordFromZhipin(String str) {
        String queryParam;

        int lastPage = getPageCount(str);

        Keyword keyword = new Keyword();
        keyword.setKeyword(str);
        keyword.setCreateTime(LocalDateTime.now());

        for (int i = 1; i <= lastPage; i++) {
            queryParam = String.format(zhipinQueryParam, str, i);
            Utils.waitUntilPageComplete(webDriver, zhipinBaseUrl, queryParam);

            WebElement jobListBox = webDriver.findElement(By.className("job-list-box"));
            List<WebElement> jobList = jobListBox.findElements(By.cssSelector("li[ka]"));

            for (WebElement ele : jobList) {
                Job job = Utils.parseJob(ele);
                keyword.addJob(job);
                log.info(job.toString());
            }
        }

        return keyword;
    }
}
