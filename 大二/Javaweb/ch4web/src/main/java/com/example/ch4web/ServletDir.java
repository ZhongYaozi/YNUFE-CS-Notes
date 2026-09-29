package com.example.ch4web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet(name = "ServletDir", value = "/jsp/*")
@WebServlet(value = "*.do")
public class ServletDir extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println("dir");
        System.out.println("dir");
        //response.sendRedirect("test.jsp");
        //request.getMethod()
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
