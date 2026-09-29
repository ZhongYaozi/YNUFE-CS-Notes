<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head></head>
<body>

session域中userName属性的值为：
<c:set var="userName" value="ynufe" scope="session"/>
<c:out value="${userName}"/><br>
session域中bookName属性的值为：
<c:set var="bookName" scope="session">
    Java Web
</c:set>
<c:out value="${bookName}"/>
<hr>

<jsp:useBean id="user" class="entity.User"/>
<c:set value="ynufe" target="${user}" property="username"/>
User对象的username属性的值为：<c:out value="${user.username}"/><br>
<c:set value="123" target="${user}" property="password"/>
User对象的password属性的值为：<c:out value="${user.password}"/>
<hr>
<%
    HashMap map = new HashMap();
    request.setAttribute("preferences", map);
%>
<c:set target="${preferences }" property="color" value="green"/>
Map对象中color关键字的值为：<c:out value="${preferences.color}"/>
</body>
</html>
