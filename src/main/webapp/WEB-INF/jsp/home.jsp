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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Homepage</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<c:url value="${pageContext.request.contextPath}/" />">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/my">${sessionScope.username} <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/create">Create</a>
            </li>
        </ul>
        <form action="<c:url value="search" />" method="get" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="keyword">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<%--<form action="<c:url value="search" />" method="get" class="form-inline">--%>
    <%--<div class="input-group input-group-sm mb-3">--%>
        <%--<div class="input-group-prepend">--%>
            <%--<span class="input-group-text" id="inputGroup-sizing-default">Search</span>--%>
        <%--</div>--%>
        <%--<input type="text" name="keyword" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">--%>
    <%--</div>--%>
    <%--<button type="submit" class="btn btn-primary mb-2">Submit</button>--%>
<%--</form>--%>
    <c:forEach items="${sessionScope.blogList}" var="blog">
        <a href="${pageContext.request.contextPath}/${blog.articleId}">
            <h1>${blog.title}</h1>
        </a>
        <br>
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
        <br>
    </c:forEach>

</body>
</html>
