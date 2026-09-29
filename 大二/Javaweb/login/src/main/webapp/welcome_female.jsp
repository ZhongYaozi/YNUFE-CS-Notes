<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2024/9/28
  Time: 下午4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>女性欢迎页面</title>
</head>
<body>
<%
    String username = request.getParameter("username");
%>
<h2>亲爱的<%= username %>，欢迎光临女性频道！</h2>
<ul>
    <p><a href="#">美容护肤</a></p>
    <p><a href="#">娱乐快递</a></p>
    <p><a href="#">潮流搭配</a></p>
    <p><a href="#">情感故事</a></p>
</ul>

<jsp:include page="footer.jsp"/>
</body>
</html>
