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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>" />
    <link rel="stylesheet" href="<c:url value="/static/css/editormd.preview.css"/>" />
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/lib/marked.min.js"/>"></script>
    <script src="<c:url value="/static/lib/prettify.min.js"/>"></script>
    <script src="<c:url value="/static/lib/raphael.min.js"/>"></script>
    <script src="<c:url value="/static/lib/underscore.min.js"/>"></script>
    <script src="<c:url value="/static/lib/sequence-diagram.min.js"/>"></script>
    <script src="<c:url value="/static/lib/flowchart.min.js"/>"></script>
    <script src="<c:url value="/static/lib/jquery.flowchart.min.js"/>"></script>
    <script src="<c:url value="/static/js/editormd.js"/>"></script>
    <title>${sessionScope.blog.title}</title>
</head>
<body>
    <h1>${sessionScope.blog.title}</h1>
    <c:forEach items="${sessionScope.blog.tags}" var="tag">
    <small>${tag.tagName}</small>
    </c:forEach>
    <div id="doc-content">
        <textarea style="display:none;">${sessionScope.blog.passage}</textarea>
    </div>
    <a href='${pageContext.request.contextPath}/create'>create</a>
    <script type="text/javascript">
        var testEditor;
        $(function () {
            testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
                // markdown:markdown,
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tex: true, // 默认不解析
                flowChart: true, // 默认不解析
                sequenceDiagram: true, // 默认不解析
                codeFold: true
            });});
    </script>
</body>
</html>
