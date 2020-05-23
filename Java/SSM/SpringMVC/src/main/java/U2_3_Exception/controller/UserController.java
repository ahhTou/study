package U2_3_Exception.controller;

import U2_3_Exception.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("userControllerForException")
@RequestMapping("/day2_3/user")
public class UserController {

    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("testException执行了");
        // 模拟异常
        try {
            int a = 10/0;
        } catch (Exception e) {
            // 打印异常
            e.printStackTrace();
            throw  new SysException("查询所有用户出现错误");
        }
        return "success";
    }

}
