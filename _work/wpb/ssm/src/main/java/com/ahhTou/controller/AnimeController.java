package com.ahhTou.controller;

import com.ahhTou.bean.Anime;
import com.ahhTou.service.AnimeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    @Resource
    AnimeService animeService;

    @RequestMapping("/pages")
    @ResponseBody
    public Integer howMuchPages(){
        System.out.println("> 返回多少页的数据");
        return animeService.getHowMuchColumn();
    }

    @RequestMapping("/{page}")
    @ResponseBody
    public List<Anime> getOnePage(@PathVariable(name = "page") Integer page){
        System.out.println("> 返回一页的数据");
        return animeService.getOnePage(page);
    }

}
