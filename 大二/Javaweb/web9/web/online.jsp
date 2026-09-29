<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/11/1
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
在线人数：<%=request.getSession().getServletContext().getAttribute("num")%>
<a href="logout">退出</a>
</body>
</html>
