package com.ahhTou.controller;

import com.ahhTou.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/base")
public class BaseController {

    @Resource
    BaseService baseService;

    @GetMapping("/getBgList")
    public Object getBgList() {
        try {
            return baseService.getBgImages();
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }
}
