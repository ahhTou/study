package U1_helloWorld.controller;

import U1_helloWorld.domain.Account;
import U1_helloWorld.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/param")
public class ParamController {


    /**
     * 请求参数绑定入门
     * @return
     */
    @RequestMapping("/testParam")
    public String testParam(String username, String password){
        System.out.println("执行了");
        System.out.println("用户名:"+ username);
        System.out.println("密码:"+ password);
        return "success";
    }

    /**
     * 请求参数绑定把数据封装到JavaBean中
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println("执行了....");
        System.out.println(account);
        return "success";
    }

    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println("执行了....");
        System.out.println(user);
        return "success";
    }

}
