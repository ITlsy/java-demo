<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://up-z1.qiniu.com/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="x:userid" value="001">
    <input type="file" name="file">
    <button>上传</button>

</form>
<c:if test="${not empty fileName}">
    <img src="http://oifocnk31.bkt.clouddn.com/${fileName}?imageView2/1/w/200/h/200">

</c:if>
</body>
</html>
