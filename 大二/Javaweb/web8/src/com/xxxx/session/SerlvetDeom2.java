package com.xxxx.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/lg")
public class SerlvetDeom2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        if ("admin".equals(uname) && "admin".equals(upwd)) {
            //登陆成功后，保存用户到Session域对象中
            request.getSession().setAttribute("username", uname);
            response.sendRedirect("index.jsp");
        } else {
            response.getWriter().write("<script>alert('error!');window.location='login.jsp'</script>");
        }


    }
}
