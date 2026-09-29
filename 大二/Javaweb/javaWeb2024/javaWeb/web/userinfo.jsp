<%--
  Created by IntelliJ IDEA.
  User: DELL  灵越5000
  Date: 2022/6/24
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>用户管理</title>
    <jsp:useBean id="dao" class="dao.UserDao" scope="request"/>
    <!--jsp:setProperty name="dao" property="title" value="Hello" /-->
</head>
<body>
<% List<User> list = dao.listAll();%>

用户名：
<input type="text" id="userName" name="userName"/>
<input type="button" id="btnQuery" value="查询"
       onclick="javascript:window.location.href='queryUser?userName='+getUserName();"/>
<br>
<table border="1" bgcolor="#e0ffff">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>角色</th>
        <th>状态</th>
        <th>删除</th>
    </tr>
    <%
        for (User user : list) {
    %>
    <tr>
        <td><a href="editUser.jsp?uid=<%= user.getUserId()%>"><%= user.getUserId() %>
        </a></td>
        <td><%= user.getUserName()%>
        </td>
        <td><%= user.getPassword()%>
        </td>
        <td><%= user.getRole()%>
        </td>
        <td><%= user.getStatus()%>
        </td>
        <td><a href="deleteUser?uid=<%= user.getUserId()%>" onclick="return confirm('提示:确定删除此用户？')">删除</a></td>
    </tr>
    <%}%>
</table>
<br>
<table bgcolor=lightgrey>
    <tr>
        <td><a href="addUser.jsp">添加用户</a></td>
    </tr>
</table>
<!--<input type="button" id="btnAdd" value="增加 " onclick="javascript:window.location.href='addUser.jsp'"/>-->
<script language="JavaScript">
    function getUserName() {
        var username = document.getElementById("userName");
        //alert(username.value);

        return username.value;
    }
</script>
</body>
</html>
