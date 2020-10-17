package com.ahhtou.controller;

import com.ahhtou.service.HelloSearcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/core")
public class RootController {

    @Resource
    HelloSearcher primeSearcher;

    @PostMapping("/isActive")
    public Boolean getStatus() {
        return primeSearcher.getIsStart();
    }

    @PostMapping("/getContent")
    public Set<String> getContent() {
        return primeSearcher.getContent();
    }

    @PostMapping("/start")
    public Boolean start() {
        primeSearcher.restart();
        return true;
    }


    @PostMapping("/shutDown")
    public Boolean shutDown() {
        primeSearcher.shutdown();
        return true;
    }

}
