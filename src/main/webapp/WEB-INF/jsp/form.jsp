<%--
  Created by IntelliJ IDEA.
  User: YU YE
  Date: 2018/6/20
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.type=='login'}">
    <form action="<c:url value="login"/>" method="post">
        <h1>username：</h1>
        <input type="text" name="username">
        <h2>password</h2>
        <input type="password" name="password">
        <br>
        <input type="submit" value="登录">
    </form>
</c:if>
<c:if test="${sessionScope.type=='register'}">
    <form action="<c:url value="register"/>" method="post">
        <h1>username：</h1>
        <input type="text" name="username">
        <h1>password</h1>
        <input type="password" name="password">
        <h1>confirm password</h1>
        <input type="password" name="confirm">
        <br>
        <input type="submit" value="注册">
    </form>
</c:if>

</body>
</html>
