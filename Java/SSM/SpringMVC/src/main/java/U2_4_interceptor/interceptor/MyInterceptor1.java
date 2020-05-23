package U2_4_interceptor.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {



    @Override
    /**
     * 预处理，controller方法执行前
     * return true 放行，执行下一个拦截器，如果没有执行controller中的方法
     * return false 不放行
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了");
//        request.getRequestDispatcher("/WEB_INF/pages/error.jsp")
//                .forward(request, response);
        return true;
    }

    /**
     * 后处理，controller方法执行后，success.jsp执行前

     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("后拦截器执行了");

    }


    /**
     * success.jsp 页面执行后，该方法会执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion拦截器执行了");
    }


}
