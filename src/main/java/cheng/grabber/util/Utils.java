package cheng.grabber.util;

import cheng.grabber.domain.CompanyTag;
import cheng.grabber.domain.FooterTag;
import cheng.grabber.domain.InfoTag;
import cheng.grabber.domain.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final Pattern pattern1 = Pattern.compile("(\\d+)-(\\d+)K·(\\d+)薪");
    private static final Pattern pattern2 = Pattern.compile("(\\d+)-(\\d+)K");
    private static final Pattern pattern3 = Pattern.compile("(\\d+)-(\\d+)元/天");

    public static void waitUntilPageComplete(WebDriver webDriver, String baseUrl, String queryParam) {
        String url = baseUrl + "?" + queryParam;
        webDriver.get(url);

        int completeCount = 0;
        for (int i = 0; i < 100; i++) {
            sleep(100);
            String curUrl = webDriver.getCurrentUrl();
            if (curUrl.startsWith(baseUrl)) {
                completeCount++;
            } else {
                completeCount = 0;
            }
            log.debug("count: {}, curUrl: {}", completeCount, curUrl);
            if (completeCount >= 10) {
                break;
            }
        }
    }

    public static int getLastPageNum(WebElement ele) {
        List<WebElement> aList = ele.findElements(By.tagName("a"));
        if (aList.size() > 2) {
            WebElement secondLast = aList.get(aList.size() - 2);
            return Integer.parseInt(secondLast.getText());
        }
        return 1;
    }

    public static Job parseJob(WebElement ele) {
        String name = ele.findElement(By.className("job-name")).getText();
        String area = ele.findElement(By.className("job-area")).getText();
        String salary = ele.findElement(By.className("salary")).getText();
        String companyName = ele.findElement(By.className("company-name")).findElement(By.tagName("a")).getText();
        String infoDesc = ele.findElement(By.className("info-desc")).getText();

        Job job = new Job();
        job.setName(name);
        job.setArea(area);
        job.setSalary(salary);
        parseJobSalary(job);
        job.setCompanyName(companyName);
        job.setInfoDesc(infoDesc);

        ele.findElement(By.className("job-info")).findElement(By.className("tag-list")).findElements(By.tagName("li")).forEach(li -> {
            String text = li.getText();
            InfoTag tag = new InfoTag();
            tag.setTag(text);
            tag.setCreateTime(LocalDateTime.now());
            job.addInfoTag(tag);
        });
        ele.findElement(By.className("company-tag-list")).findElements(By.tagName("li")).forEach(li -> {
            String text = li.getText();
            CompanyTag tag = new CompanyTag();
            tag.setTag(text);
            tag.setCreateTime(LocalDateTime.now());
            job.addCompanyTag(tag);
        });
        ele.findElement(By.className("job-card-footer")).findElement(By.className("tag-list")).findElements(By.tagName("li")).forEach(li -> {
            String text = li.getText();
            FooterTag tag = new FooterTag();
            tag.setTag(text);
            tag.setCreateTime(LocalDateTime.now());
            job.addFooterInfoTag(tag);
        });

        return job;
    }

    public static void parseJobSalary(Job job) {
        String salary = job.getSalary();
        int minSalary = 0, maxSalary = 0;
        double times = 0;
        Job.SalaryTimesType type = Job.SalaryTimesType.MONTH;
        double annualSalaryMin = 0;
        double annualSalaryMax = 0;

        if (salary.contains("薪")) {
            Matcher matcher = pattern1.matcher(salary);
            if (matcher.matches()) {
                minSalary = Integer.parseInt(matcher.group(1));
                maxSalary = Integer.parseInt(matcher.group(2));
                times = Integer.parseInt(matcher.group(3));
                annualSalaryMin = minSalary * times / 10;
                annualSalaryMax = maxSalary * times / 10;
            } else {
                log.error("{}", salary);
            }
        } else if (salary.contains("K")) {
            Matcher matcher = pattern2.matcher(salary);
            if (matcher.matches()) {
                minSalary = Integer.parseInt(matcher.group(1));
                maxSalary = Integer.parseInt(matcher.group(2));
                times = 12;
                annualSalaryMin = minSalary * times / 10;
                annualSalaryMax = maxSalary * times / 10;
            } else {
                log.error("{}", salary);
            }
        } else if (salary.contains("天")) {
            Matcher matcher = pattern3.matcher(salary);
            if (matcher.matches()) {
                minSalary = Integer.parseInt(matcher.group(1));
                maxSalary = Integer.parseInt(matcher.group(2));
                times = 21.75;
                type = Job.SalaryTimesType.DAY;
                annualSalaryMin = minSalary * times * 12 / 1000;
                annualSalaryMax = maxSalary * times * 12 / 1000;
            } else {
                log.error("{}", salary);
            }
        }

        job.setSalaryMin(minSalary);
        job.setSalaryMax(maxSalary);
        job.setSalaryTimes(times);
        job.setSalaryTimesType(type);
        job.setAnnualSalaryMin(annualSalaryMin);
        job.setAnnualSalaryMax(annualSalaryMax);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
