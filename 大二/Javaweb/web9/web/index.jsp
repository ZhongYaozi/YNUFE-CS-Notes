<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/10/25
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
首页面，欢迎你，<%=request.getSession().getAttribute("username")%>
<br>
<a href="changepwd.jsp">修改密码</a> <a href="order.jsp">查看订单</a>
</body>
</html>
