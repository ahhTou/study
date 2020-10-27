package com.ahhtou.controller;

import com.ahhtou.common.pojo.BgList;
import com.ahhtou.common.pojo.HelloResult;
import com.ahhtou.nosql.service.SearchRedis;
import com.ahhtou.service.HelloSearcher;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    SearchRedis searchRedis;

    @PostMapping("/isActive")
    public Boolean getStatus() {
        return primeSearcher.getIsStart();
    }

    @PostMapping("/getContent")
    public Set<String> getContent() {
        return primeSearcher.getContent();
    }

    @GetMapping("/getBgList")
    public HelloResult getBgList() {
        BgList bgList = searchRedis.getBgList();
        return new HelloResult(bgList, "获取背景图片信息成功");
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
