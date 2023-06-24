package cheng.grabber.controller;

import cheng.grabber.domain.Job;
import cheng.grabber.domain.Keyword;
import cheng.grabber.service.KeywordService;
import cheng.grabber.vo.KeywordVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    private final KeywordService keywordService;

    @PostMapping
    public Keyword addKeyword(@RequestBody KeywordVo vo) {
        Keyword res = new Keyword();

        res.setKeyword(vo.getKeyword());
        res.setCreateTime(LocalDateTime.now());
        Job job1 = new Job();
        Job job2 = new Job();
        res.setJobList(List.of(job1, job2));

        return keywordService.saveKeyword(res);
    }
}
