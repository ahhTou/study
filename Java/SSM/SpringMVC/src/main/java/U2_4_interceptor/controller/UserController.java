package U2_4_interceptor.controller;

import U2_3_Exception.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("userControllerForInterceptor")
@RequestMapping("/day2_4/user")
public class UserController {

    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("testInterceptor执行了");

        return "success";
    }

}
