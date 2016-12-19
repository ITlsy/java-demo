<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/12 0012
  Time: 上午 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/simditor/styles/simditor.css">
</head>
<body>
<div class="container">
    <form action="/send" method="post">
        <textarea name="message" id="editor" autofocus></textarea>
        <button class="btn btn-primary">发布</button>

    </form>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/simditor/scripts/module.min.js"></script>
<script src="/static/js/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/js/simditor/scripts/uploader.min.js"></script>
<script src="/static/js/simditor/scripts/simditor.min.js"></script>

<script>
    $(function () {
     var edit=new Simditor({
         textarea:$("#editor"),
         placeholder:"please",
         toolbar:true,

     });
    });
</script>

</body>
</html>
