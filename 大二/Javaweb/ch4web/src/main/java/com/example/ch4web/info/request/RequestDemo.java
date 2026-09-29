package com.example.ch4web.info.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * request 获取请求数据
 */
@WebServlet("/RequestDemo")
public class RequestDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取post 请求体：请求参数
        //1. 获取字符输入流
        BufferedReader br = request.getReader();
        //2. 读取数据
        String line = br.readLine();
        System.out.println(line);
    }
}