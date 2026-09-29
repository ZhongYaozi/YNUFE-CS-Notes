package com.xxxx.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//3.Cookie的有效期设置：setMaxAge(int n),n>0,代表cookie保存在客户端的秒数; n=0:代表删除该cookie;
// n<0,默认的设置，代表浏览器关闭，cookie失效

@WebServlet("/cd3")
public class CookieDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //（1）实例化cookie对象
        Cookie cookie1 = new Cookie("teaname", "lisi");
        Cookie cookie2 = new Cookie("teaage", "60");
        Cookie cookie3 = new Cookie("teaheight", "1.7");
        //设置有效期
        cookie1.setMaxAge(60);
        cookie2.setMaxAge(0);
        cookie3.setMaxAge(-1);

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
