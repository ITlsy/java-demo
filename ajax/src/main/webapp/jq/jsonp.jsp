<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/8 0008
  Time: 下午 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">JSONP</button>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
       $("#btn").click(function () {
           $.getJSON("/jsonp?method=?").done(function (data) {
               alert(data.userName + " -> " + data.address);
           }).error(function () {
               alert("服务器异常");
           });
       }) ;

    });
</script>
<%--<script>
    function callback(user) {
        alert("hello," + user.userName);
    }
</script>
<script src="/jsonp?method=callback"></script>--%>
</body>
</html>
