<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2024/9/28
  Time: 下午4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>男性欢迎页面</title>
</head>
<body>
<%
    String username = request.getParameter("username");
%>
<h2>亲爱的<%= username %>，欢迎光临男性频道！</h2>
<ul>
    <p><a href="#">时政要闻</a></p>
    <p><a href="#">军事天地</a></p>
    <p><a href="#">游戏竞技</a></p>
    <p><a href="#">体育报道</a></p>
</ul>

<jsp:include page="footer.jsp"/>
</body>
</html>
