<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</body>
</html>
