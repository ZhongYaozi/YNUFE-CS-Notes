package com.example.ch4web.info.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletReq1", value = "/ServletReq1")
public class ServletReq1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("key1", 100);
        request.setAttribute("key2", "czx");

        response.getWriter().println("test");

        request.getRequestDispatcher("/ServletReq2").include(request, response);

        //response.getWriter().println(request.getAttribute("key1")+":"+request.getAttribute("key2"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
