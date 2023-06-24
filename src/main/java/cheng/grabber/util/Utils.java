package cheng.grabber.util;

import cheng.grabber.domain.CompanyTag;
import cheng.grabber.domain.FooterTag;
import cheng.grabber.domain.InfoTag;
import cheng.grabber.domain.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.util.List;

public class Utils {
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
        List<InfoTag> infoTagList = ele.findElement(By.className("job-info")).findElement(By.className("tag-list")).findElements(By.tagName("li")).stream().map(li -> {
            String text = li.getText();
            InfoTag tag = new InfoTag();
            tag.setTag(text);
            tag.setCreateTime(LocalDateTime.now());
            return tag;
        }).toList();
        List<CompanyTag> companyTagList = ele.findElement(By.className("company-tag-list")).findElements(By.tagName("li")).stream().map(li -> {
            String text = li.getText();
            CompanyTag tag = new CompanyTag();
            tag.setTag(text);
            tag.setCreateTime(LocalDateTime.now());
            return tag;
        }).toList();
        List<FooterTag> footerTagList = ele.findElement(By.className("job-card-footer")).findElement(By.className("tag-list")).findElements(By.tagName("li")).stream().map(li -> {
            String text = li.getText();
            FooterTag tag = new FooterTag();
            tag.setTag(text);
            tag.setCreateTime(LocalDateTime.now());
            return tag;
        }).toList();


        Job job = new Job();
        job.setName(name);
        job.setArea(area);
        job.setSalary(salary);
        job.setCompanyName(companyName);
        job.setInfoDesc(infoDesc);
        job.setInfoTagList(infoTagList);
        job.setCompanyTagList(companyTagList);
        job.setFooterTagList(footerTagList);

        return job;
    }

}
