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
<form action="<c:url value="search" />" method="get">
    <input type="text" name="keyword">
    <input type="submit">
</form>
    <c:forEach items="${sessionScope.blogList}" var="blog">
        <a href="${pageContext.request.contextPath}/${blog.articleId}">
            <h1>${blog.title}</h1>
        </a>
        <<br>
        <c:if test="${sessionScope.type!='tag'}">
            <h3>
                <c:forEach items="${blog.tags}" var="tag">
                    <a href="${pageContext.request.contextPath}/tag/${tag.tagId}">
                            ${tag.tagName}
                    </a>
                </c:forEach>
            </h3>
        </c:if>

        <%--<small>${blog.articleId}</small>--%>
        <h4>${blog.passage}</h4>
    </c:forEach>
    <a href='${pageContext.request.contextPath}/create'>create</a>
</body>
</html>
