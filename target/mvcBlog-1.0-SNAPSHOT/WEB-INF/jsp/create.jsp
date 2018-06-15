<%--
  Created by IntelliJ IDEA.
  User: YU YE
  Date: 2018/6/14
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Add blog</title>
</head>
<body>
    <form action="<c:url value="create"/>" method="post">
        <h1>标题：</h1>
        <input type="text" name="title">
        <h2>文章</h2>
        <textarea name="passage"></textarea>
        <br>
        <input type="submit" value="发布">
    </form>
</body>
</html>
