package com.ahhTou.controller;

import com.ahhTou.service.AnimeService;
import com.ahhTou.untils.MyObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    @Resource
    AnimeService animeService;

    @RequestMapping("/pages")
    @ResponseBody
    public Integer howMuchPages() {
        System.out.println("> 返回多少页的数据");
        return animeService.getHowMuchColumn();
    }

    @RequestMapping("/{page}")
    @ResponseBody
    public String getOnePage(@PathVariable(name = "page") Integer page) {
        System.out.println("> 返回一页的数据");
        try {
            System.out.println("> 调用objectMapper");
            String json = MyObjectMapper.createMyObjectMapper().writeValueAsString(animeService.getOnePage(page));
            return MyObjectMapper.createMyObjectMapper().writeValueAsString(json);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/delOne")
    @ResponseBody
    public Boolean delOne(@RequestBody Integer id) {
        System.out.println("> 准备删除id为:" + id + "的番剧");
        return animeService.delAnimeById(id);
    }

}
