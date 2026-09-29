<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head></head>
<body>
<c:set value="云南财经" var="school" scope="request"/>
<c:set value="www.ynufe.edu.cn" var="url" scope="request"/>
School：<c:out value="${school}"/><br>
URL：<c:out value="${url}"/><br>
<hr>
使用标签删除属性后<br>
<c:remove var="school" scope="request"/>
<c:remove var="url" scope="request"/>
School：<c:out value="${school}"/><br>
URL：<c:out value="${url}"/><br>
</body>
</html>
