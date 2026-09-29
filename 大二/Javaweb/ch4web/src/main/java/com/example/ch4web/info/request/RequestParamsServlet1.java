package com.example.ch4web.info.request;

import java.io.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RequestParamsServlet1")
public class RequestParamsServlet1 extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //设置request对象的解码方式
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("username");
        //name=new String(name.getBytes("iso8859-1"),"utf-8");
        String password = request.getParameter("password");
        System.out.println("用户名:" + name);
        System.out.println("密  码:" + password);

        request.setAttribute("key1", 100);
        request.setAttribute("key2", "czx");
        response.getWriter().println(request.getAttribute("key1") + ":" + request.getAttribute("key2"));

        // 获取参数名为“hobby”的值
        String[] hobbys = request.getParameterValues("hobby");
        System.out.print("爱好:");
        for (int i = 0; i < hobbys.length; i++) {
            System.out.print(hobbys[i] + ",");
        }
        System.out.println();

        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            // username:zhangsan
            System.out.print(key + ":");

            //获取值
            String[] values = map.get(key);
            for (String value : values) {
                System.out.print(value + " ");
            }

            System.out.println();
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
