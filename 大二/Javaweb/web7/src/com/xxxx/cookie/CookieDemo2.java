package com.xxxx.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//2.Cookie的获取
@WebServlet("/cd2")
public class CookieDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Cookies集合
        Cookie[] cookies = request.getCookies();
        //遍历Cookies集合，输出每个Cookie的name和value
//        for(int i=0;i< cookies.length; i++){              //for方式1
//            System.out.println(cookies[i].getName());
//            System.out.println(cookies[i].getValue());
//        }
        if (cookies != null)
            for (Cookie c : cookies) {                           //for方式2
                System.out.println(c.getName() + ":" + c.getValue());
            }
        else {
            System.out.println("cookies 为空");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
