<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: DELL  灵越5000
  Date: 2022/7/9
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<html>
<head>
    <title>$Title$</title>
    <jsp:useBean id="dao" class="dao.UserDao" scope="request"/>
</head>
<body>
czx test:使用idea创建javaWeb<br>
http://localhost:8090/javaWeb_web/ <br>

<br>

<%
    User user = dao.findByUserId(1);
%>
<input type="text" name="username" value="aaa"/>
<input type="text" name="username2" value="<%=user.getUserName()%>"/>
<input type="text" name="username3" value="<%=user%>"/>
$END$
</body>
</html>
