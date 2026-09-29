package com.xxxx.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//4.Cookie的修改：只要某个Cookie的名称、路径和之前的Cookie一致，就代表修改

@WebServlet("/cd4")
public class CookieDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //（1）实例化cookie对象
        Cookie cookie1 = new Cookie("teaname", "lisi");
        Cookie cookie2 = new Cookie("teaage", "60");
        Cookie cookie3 = new Cookie("teaname", "wangwu");

        //(2)响应到客户端
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
