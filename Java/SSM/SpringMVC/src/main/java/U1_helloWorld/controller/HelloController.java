package U1_helloWorld.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

// 控制类
@Controller
@RequestMapping(path = "/user")
public class HelloController {

    // 请求映射
    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("Hello String");
        return "success";
    }

    /**
     * RequestMapping注解
     *
     * @return
     */
//    @RequestMapping(path = "/testRequestMapping")
//
//    @RequestMapping(value = "/testRequestMapping")
//
//    @RequestMapping("/testRequestMapping")

    //必须是post提交类型
//    @RequestMapping(
//            value = "/testRequestMapping",
//            method = {RequestMethod.POST})
    //必须提交username
//    @RequestMapping(
//            value = "/testRequestMapping",
//            params = {"username"}
//    )

    //必须提交username而且值为配置规定的
//    @RequestMapping(
//            value = "/testRequestMapping",
//            params = {"username=ahhTou"}
//    )

    //必须包含 请求头Accept
    @RequestMapping(
            value = "/testRequestMapping",
            params = {"username=ahhTou"},
            headers = {"Accept"}
    )
    public String testRequestMapping() {
        System.out.println("测试RequestMapping...");
        return "success";
    }


    @RequestMapping("testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("测试...");
        System.out.println(request);
        HttpSession session = request.getSession();
        System.out.println(session);
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        System.out.println(response);
        return "success";
    }


}
