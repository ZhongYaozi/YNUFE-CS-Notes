package com.xxxx.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/last")
public class LastAccessTime extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.解决请求和响应时的中文乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //2.获取请求中携带的cookies
        Cookie[] cookies = req.getCookies();
        //定义变量用于保存上一次的访问时间，初始值为null
        String lasttime = null;
        //3.遍历cookies集合，如果存在指定名称的cookies（lastaccess）,那么就获取该cookies的值（上次的访问时间）赋值给lasttime
        for (Cookie c : cookies) {
            if ("lastaccess".equals(c.getName())) {
                lasttime = c.getValue();
                break;
            }
        }
        //4.判断lasttime是否为null,如果是即为第一次访问，如果不为null,就代表之前访问过；
        if (lasttime == null) {
            resp.getWriter().write("<h1>欢迎第一次访问！</h1>");
        } else {
            resp.getWriter().write("您上一次访问时间：" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(Long.parseLong(lasttime))));   //lasttime默认以时间戳的形式存在
        }
        //5.创建或修改名称为lastaccess的Cookie,他的值就是当前的系统时间
        String stime = String.valueOf(new Date().getTime());
        Cookie cookie1 = new Cookie("lastaccess", stime);
        cookie1.setMaxAge(60 * 60);
        resp.addCookie(cookie1);

    }
}

