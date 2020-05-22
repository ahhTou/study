package U2.controller;

import U2.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/day2/user")
public class UserController {

    @RequestMapping("/testString")
    public String testString(Model model) {
        System.out.println("testString执行了");
        User user = new User();
        user.setAge(30);
        user.setPassword("123");
        user.setUsername("ahhTou");
        model.addAttribute("user", user);
        return "success";
    }

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid执行了");
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);

        // 重定向
//        response.sendRedirect(request.getContextPath() + "/day2_index.jsp");

        // 设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        // 直接会进行相应
        response.getWriter().print("hello偷偷");
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(Model model) {

        ModelAndView mv = new ModelAndView();

        System.out.println("testString执行了");
        User user = new User();
        user.setAge(30);
        user.setPassword("123");
        user.setUsername("ahhTou");

        mv.addObject("user", user);

        mv.setViewName("success");

        return mv;
    }


    /**
     * 使用关键字的方式进行转发或者重定向
     *
     * @param
     * @return
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect() {
        System.out.println("testForwardOrRedirect执行了");

        // 请求转发
//        return "forward:/WEB-INF/pages/success.jsp";

        // 重定向
        return "redirect:/day2_index.jsp";
    }

    //    @RequestMapping("/testAjax")
//    public @ResponseBody User testAjax(@RequestBody User user){
//        System.out.println("Ajax....");
//        // 客户端发送ajax的请求，传的是json字符串，后端吧json字符串封装到user中
//        System.out.println(user);
//        // 做响应，模拟查询数据库
//        user.setUsername("ahhTouPro");
//        user.setAge(40);
//        // 最响应
//        return user;
//    }
    @RequestMapping("/testAjax")
    public @ResponseBody
    Map<String, String> testAjax(@RequestBody Map<String, String> map) {
        System.out.println("Ajax....");
        // 客户端发送ajax的请求，传的是json字符串，后端吧json字符串封装到user中
        map.put("username", "ahhTouPro");
        return map;
    }
}
