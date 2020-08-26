package com.ahhTou.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// 统计网页在线人数 ： 统计session
public class OnlineCountListener implements HttpSessionListener {

    // 创建session监听
    // 一单创建Session就会触发这个事件
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            onlineCount = onlineCount + 1;
        }

        ctx.setAttribute("OnlineCount", onlineCount);
    }


}
