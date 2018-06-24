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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/static/css/editormd.css"/>">
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/js/editormd.js"/>"></script>

    <title>Add blog</title>
</head>
<body>
    <%--<form action="<c:url value="create"/>" method="post">--%>
        <%--<h1>标题：</h1>--%>
        <%--<input type="text" name="title">--%>
        <%--<h2>文章</h2>--%>
        <%--<textarea style="width: auto;height: auto" name="passage"></textarea>--%>
        <%--<br>--%>
        <%--<c:forEach items="${sessionScope.tags}" var="tag">--%>
            <%--<input type="checkbox" name="tag" value="${tag.tagName}">${tag.tagName}--%>
            <%--<br>--%>
        <%--</c:forEach>--%>
        <%--<input type="submit" value="发布">--%>
    <%--</form>--%>
    <form action="<c:url value="create"/>" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroup-sizing-default">Default</span>
            </div>
            <input type="text" name="title" class="form-control" aria-label="Title" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect2">Tag select</label>
            <select multiple name="tag" class="form-control" id="exampleFormControlSelect2">
                <c:forEach items="${sessionScope.tags}" var="tag">
                <option>${tag.tagName}</option>
                </c:forEach>
            </select>
        </div>
        <%--<c:forEach items="${sessionScope.tags}" var="tag">--%>
        <%--&lt;%&ndash;<input type="checkbox" name="tag" value="${tag.tagName}">${tag.tagName}&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div class="input-group mb-3">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="input-group-prepend">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="input-group-text">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input type="checkbox" name="tag" aria-label="${tag.tagName} value="${tag.tagName}">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="text" class="form-control" aria-label="${tag.tagName} value="${tag.tagName}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--<div class="form-check">--%>
                <%--<input class="form-check-input" type="checkbox" value="${tag.tagName}" id="defaultCheck1">--%>
                <%--<label class="form-check-label" for="defaultCheck1">--%>
                        <%--${tag.tagName}--%>
                <%--</label>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
        <div id="my-editormd" >
            <textarea id="my-editormd-markdown-doc" name="my-editormd-markdown-doc" style="display:none;"></textarea>
            <!-- 注意：name属性的值-->
            <textarea id="my-editormd-html-code" name="my-editormd-html-code" style="display:none;"></textarea>
        </div>
        <div style="width:90%;margin: 10px auto;">
            <input type="submit" class="btn btn-default" value="Submit" />
        </div>

    </form>
</body>
<script type="text/javascript">
    $(function() {
        editormd("my-editormd", {//注意1：这里的就是上面的DIV的id属性值
            width   : "90%",
            height  : 640,
            syncScrolling : "single",
            path    : "${pageContext.request.contextPath}/static/lib/",//注意2：你的路径
            saveHTMLToTextarea : true//注意3：这个配置，方便post提交表单
        });
    });
</script>
</html>
