<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head></head>
<body>
使用"|"和","作为分隔符<br>
<c:forTokens var="token"
             items="Spring,Summer|autumn,winter" delims="|,">
    ${token}
</c:forTokens>
<hr>
使用"--"作为分隔符<br>
<c:forTokens var="token" items="Day--Week--Month--Year" delims="--">
    ${token}
</c:forTokens>
</body>
</html>
