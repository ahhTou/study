package U1_helloWorld.controller;

import U1_helloWorld.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * 常用的注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"}) //把msg=美美存入到session域对中
public class AnnoController {

    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "name") String username) {
        System.out.println("执行了");
        System.out.println(username);
        return "success";
    }

    /**
     * 获取请求体的内容
     *
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) throws UnsupportedEncodingException {
        System.out.println(body);
        return "success";
    }


    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id) {
        System.out.println(id);
        return "success";
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header) {
        System.out.println(header);
        return "success";
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println(cookieValue);
        return "success";
    }




    /**
     * 该方法会先执行,无返回值
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user) {
        System.out.println(user);
        return "success";
    }
    @ModelAttribute
    public void showUser(String name, Map<String, User> map) {
        System.out.println("showUser执行了");
        // 通过用户名查询数据库（模拟）
        User user = new User();
        user.setName(name);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc",user);
    }

    /**
     * 该方法会先执行,有返回值
     */
//    @RequestMapping("/testModelAttribute")
//    public String testModelAttribute(User user) {
//        System.out.println(user);
//        return "success";
//    }
//    @ModelAttribute
//    public User showUser(String name) {
//        System.out.println("showUser执行了");
//        // 通过用户名查询数据库（模拟）
//        User user = new User();
//        user.setName(name);
//        user.setAge(20);
//        user.setDate(new Date());
//        return user;
//    }


    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Model model) {
        System.out.println("SessionAttribute执行了");
        model.addAttribute("msg","美美");
        return "success";
    }

    @RequestMapping("/getSessionAttribute")
    public String getSessionAttribute(ModelMap modelMap) {
        System.out.println("getSessionAttribute执行了");
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("/delSessionAttribute")
    public String delSessionAttribute(SessionStatus status) {
        System.out.println("delSessionAttribute执行了");
        status.setComplete();
        return "success";
    }
}
