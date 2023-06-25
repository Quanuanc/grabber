package cheng.grabber.controller;

import cheng.grabber.domain.*;
import cheng.grabber.domain.vo.KeywordVo;
import cheng.grabber.service.KeywordService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping
    public KeywordVo addKeyword(@RequestBody KeywordVo vo) {
        Keyword keyword = new Keyword();
        keyword.setKeyword(vo.getKeyword());
        keyword.setCreateTime(LocalDateTime.now());
        Job job = new Job();
        InfoTag infoTag = new InfoTag();
        FooterTag footerTag = new FooterTag();
        CompanyTag companyTag = new CompanyTag();
        job.addInfoTag(infoTag);
        job.addCompanyTag(companyTag);
        job.addFooterInfoTag(footerTag);
        keyword.addJob(job);
        keyword = keywordService.saveKeyword(keyword);

        KeywordVo keywordVo = new KeywordVo();
        BeanUtils.copyProperties(keyword, keywordVo);

        return keywordVo;
    }
}
