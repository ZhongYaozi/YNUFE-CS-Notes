<%@page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head></head>
<body>
<%
    String username = request.getParameter("username");
    username = new String(username.getBytes("iso-8859-1"), "utf-8");
    String country = request.getParameter("country");
    country = new String(country.getBytes("iso-8859-1"), "utf-8");
%>
UserName=<%= username%>
Country=<%= country%>
</body>
</html>
