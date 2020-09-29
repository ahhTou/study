package com.ahhTou.controller;

import com.ahhTou.bean.BgList;
import com.ahhTou.service.BaseService;
import com.ahhTou.utils.var.CommonVariable;
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
