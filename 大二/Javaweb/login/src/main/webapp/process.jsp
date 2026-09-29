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
    <title>处理登录信息</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String gender = request.getParameter("gender");

    if (username == null || username.trim().isEmpty()) {
%>
<p style="color: red;">用户名不能为空！</p>
<a href="login.jsp">返回登录页面</a>
<%
    } else {
        if (gender.equals("male")) {
            response.sendRedirect("welcome_male.jsp?username=" + username);
        } else {
            response.sendRedirect("welcome_female.jsp?username=" + username);
        }
    }
%>
<jsp:include page="footer.jsp"/>

</body>
</html>
