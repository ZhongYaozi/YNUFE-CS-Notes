<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<html>
<head>
    <title>编辑用户信息</title>
    <jsp:useBean id="dao" class="dao.UserDao" scope="request"/>
</head>
<body>
<%
    Integer userid = Integer.valueOf(request.getParameter("uid"));//userid
    User user = dao.findByUserId(userid);
%>
<form method="post" action="/javaWeb_web/editUser?uid=<%=userid%>">
    ID：<label><%=  user.getUserId() %>
</label><br/>
    用户名：<input type="text" name="userName" value="<%= user.getUserName()%>"/><br/>
    密码：<input type="text" name="userPwd" value="<%= user.getPassword()%>"/><br/>
    角色：<input type="text" name="userRole" value="<%= user.getRole()%>"/><br/>
    状态：<input type="text" name="userStatus" value="<%= user.getStatus()%>"/><br/>
    <input type="submit" name="btnSubmit" value="提交"/>
    <input type="button" name="btnBack" value="返回" onclick="javascript:window.history.back();;"/>
</form>
</body>
</html>
