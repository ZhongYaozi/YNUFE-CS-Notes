package com.xxxx.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/sd1")
public class SessionDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("Session ID:" + session.getId());
        System.out.println("创建时间：" + session.getCreationTime());
        System.out.println("上次访问时间：" + session.getLastAccessedTime());
        System.out.println("是否新会话：" + session.isNew());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
