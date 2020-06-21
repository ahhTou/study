package com.ahhTou.controller;

import com.ahhTou.bean.User;
import com.ahhTou.dao.DataMapper;
import com.ahhTou.dao.LoginMapper;
import com.ahhTou.service.DataService;
import com.ahhTou.service.LoginService;
import com.ahhTou.service.impl.AnimeServiceImpl;
import com.ahhTou.untils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/data")
public class DataController {

    @Resource
    DataService dataService;

    @Resource
    LoginService loginService;

    @RequestMapping("/basic")
    @ResponseBody
    public User getUserBasicData(HttpServletRequest req) {
        final String token = req.getHeader("token");
        String tokenKey = Token.verify(token);

        System.out.println("> Token解析出的用户名:" + tokenKey);
        System.out.println("> 通过用户名查找信息");

        User basicData = dataService.getBasicData(tokenKey);

        if (basicData.getToken().equals(token)) {
            System.out.println("> Token对比正确");
            System.out.println("> 更新Token");
            try {
                String newToken = Token.create(basicData.getUsername());
                basicData.setToken(newToken);
                loginService.setToken(basicData);
                System.out.println("> 更新Token正确，返回数据");
                return basicData;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("> 更新Token出现异常");
                return null;
            }


        } else {
            System.out.println("> Token对比错误");
        }

        return null;
    }


}