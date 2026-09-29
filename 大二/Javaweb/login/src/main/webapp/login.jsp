<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2024/9/28
  Time: 下午4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h2>用户登录</h2>
<form action="process.jsp" method="post">
    用户名: <input type="text" name="username"/><br/><br/>
    性别:
    <input type="radio" name="gender" value="male" checked="checked"/> 男
    <input type="radio" name="gender" value="female"/> 女<br/><br/>
    <input type="submit" value="登录"/>
</form>
<jsp:include page="footer.jsp"/>

</body>
</html>
