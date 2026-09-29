<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加用户</title>
</head>
<body>
<form method="post" action="/javaWeb_web/addUser">
    ID：<input type="text" name="userId"/><br/>
    用户名：<input type="text" name="userName"/><br/>
    密码：<input type="text" name="userPwd"/><br/>
    角色：<input type="text" name="userRole"/><br/>
    状态：<input type="text" name="userStatus"/><br/>
    <input type="submit" name="btnSubmit" value="提交"/>
    <input type="button" name="btnBack" value="返回" onclick="javascript:window.history.back();"/>
</form>
</body>
</html>
