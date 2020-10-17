package com.ahhtou.controller;

import com.ahhtou.bean.BgList;
import com.ahhtou.service.BaseService;
import com.ahhtou.utils.var.CommonVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/base")
public class BaseController {

    @Resource
    CommonVariable commonVariable;

    @Resource
    BaseService baseService;

    @GetMapping("/getBgList")
    public BgList getBgList() {

        BgList bgList = new BgList();
        bgList.setBgList(baseService.getBgImages());
        bgList.setSize(commonVariable.getSize());
        return bgList;
    }
}
