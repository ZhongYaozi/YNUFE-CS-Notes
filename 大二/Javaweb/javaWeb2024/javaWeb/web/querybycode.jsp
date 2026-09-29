<%-- 
    Document   : querybycode
    Created on : 2022-7-11, 16:05:49
    Author     : cc
    Desc :  PPT示例ch6
--%>

<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询customer信息</title>
</head>
<body>
http://localhost:8090/javaWeb_web/querybycode.jsp?discount_code=5
<%
    //获得用户提交的code
    String discount_code = request.getParameter("discount_code");

    //连接数据库用到的对象
    Connection conn = null;
    PreparedStatement prst = null;
    ResultSet rs = null;

    //连接数据库用到的参数信息
    String url = "jdbc:mysql://localhost:3306/test";
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "sasasa";

    //查询数据库的SQL语句
    String sql = "select customer_id , name from customer where discount_code = ?";

    Class.forName(driver);
    conn = DriverManager.getConnection(url, user, password);
    prst = conn.prepareStatement(sql);
    prst.setString(1, discount_code);
    rs = prst.executeQuery();
%>
<table>
    <%
        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString(1)%>
        </td>
        <td><%= rs.getString(2)%>
        </td>
    </tr>
    <%
        }
        if (rs != null) {
            rs.close();
        }
        if (prst != null) {
            prst.close();
        }
        if (conn != null) {
            conn.close();
        }
    %>
</table>
</body>
</html>
