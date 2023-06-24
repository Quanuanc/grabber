package cheng.grabber.controller;

import cheng.grabber.service.GrabberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grab")
public class GrabberController {
    private final GrabberService grabberService;

    public GrabberController(GrabberService grabberService) {
        this.grabberService = grabberService;
    }


    @GetMapping("/pageCount/{keyword}")
    public Integer pageCount(@PathVariable String keyword) {
        return grabberService.getPageCount(keyword);
    }
}
