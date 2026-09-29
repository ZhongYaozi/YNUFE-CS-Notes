package com.xxxx.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//1.Cookie的创建
@WebServlet("/cd1")
public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //（1）实例化cookie对象
        Cookie cookie1 = new Cookie("stuname", "zhangsan");
        Cookie cookie2 = new Cookie("stuage", "20");
        //(2)响应到客户端
        response.addCookie(cookie1);
        response.addCookie(cookie2);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
