package com.xhr.controller;


import com.xhr.bean.UserSeenList;
import com.xhr.dao.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {


    ListMapper listMapper;

    @Autowired
    public void setListMapper(ListMapper listMapper) {
        this.listMapper = listMapper;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<UserSeenList> getAll(@RequestBody String username) {
        try {

            System.out.println("\r\n> 请求获取全部");
            List<UserSeenList> allByUsername = listMapper.getAllByUsername(username);
            System.out.println("> 返回全部数据");
            return allByUsername;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("> 请求异常");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addOne")
    @ResponseBody
    public boolean addOne(@RequestBody UserSeenList userSeenList) {
        try {

            System.out.println("\r\n> 请求增加");
            boolean b = listMapper.addOne(userSeenList);
            if (b){
                System.out.println("> 返回结果");
                return true;
            }else {
                System.out.println("> 添加失败");
                return false;
            }

        } catch (Exception e) {

            System.out.println("> 请求异常");
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/updateOne")
    @ResponseBody
    public boolean updateOne(@RequestBody UserSeenList userSeenList) {
        try {
            System.out.println("\r\n> 请求更新");
            return listMapper.updateOne(userSeenList);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("> 请求异常");
            return false;
        }
    }

    @RequestMapping("/delOne")
    @ResponseBody
    public boolean delOne(@RequestBody Integer movieID){
        try {

            System.out.println("\r\n> 请求删除");
            return listMapper.delOne(movieID);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("> 请求异常");
            return false;

        }

    }

}
