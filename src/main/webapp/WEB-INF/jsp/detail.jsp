<%--
  Created by IntelliJ IDEA.
  User: YU YE
  Date: 2018/6/15
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>${sessionScope.blog.title}</title>
</head>
<body>
<h1>${sessionScope.blog.title}</h1>
<c:forEach items="${sessionScope.blog.tags}" var="tag">
<small>${tag.tagName}</small>
</c:forEach>
<h3>${sessionScope.blog.passage}</h3>

<a href='${pageContext.request.contextPath}/create'>create</a>
</body>
</html>
