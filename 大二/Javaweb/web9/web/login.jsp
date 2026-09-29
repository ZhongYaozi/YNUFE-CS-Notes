<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/10/25
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>202305002512</title>
    <script src="js/login.js"></script>
    <link rel="stylesheet " href="css/login.css"></link>
</head>
<body>
<form action="lg" method="post" id="loginform">
    用户名:<input type="text" name="uname" id="uname"><br>
    密码：<input type="password" name="upwd" id="upwd"><br>
    <button type="button" onclick="pd()">登录</button>
    <button type="reset">取消</button>
</form>
</body>
</html>
