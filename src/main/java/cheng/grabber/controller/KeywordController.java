package cheng.grabber.controller;

import cheng.grabber.domain.Keyword;
import cheng.grabber.domain.vo.KeywordVo;
import cheng.grabber.service.KeywordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/{id}")
    public Keyword keyword(@PathVariable Integer id) {
        return keywordService.findKeyword(id);
    }

    @GetMapping("/count/{keyword}")
    public Integer getPageCount(@PathVariable String keyword) {
        return keywordService.getPageCount(keyword);
    }

    @PostMapping
    public Keyword addKeyword(@RequestBody KeywordVo vo) {
        return keywordService.addKeyword(vo.getKeyword());
    }
}
