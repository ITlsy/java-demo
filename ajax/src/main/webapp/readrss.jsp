<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6 0006
  Time: 下午 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">read rss</button>

<script>
    (function() {
       document.querySelector("#btn").onclick=function () {
         var xmlHttp=new XMLHttpRequest();
         xmlHttp.open("get","rss.xml");
         xmlHttp.send();

       }

    })();

</script>
</body>
</html>
