<%--
  Created by IntelliJ IDEA.
  User: YU YE
  Date: 2018/6/14
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Homepage</title>
</head>
<body>
<c:forEach items="${sessionScope.blogList}" var="blog">
    <h1>${blog.title}</h1><small>${blog.articleId}</small>
    <h4>${blog.passage}</h4>
</c:forEach>
<a href='<c:url value="create">'></a>
</body>
</html>
