<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6 0006
  Time: 下午 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">load Json</button>
<script>
    (function () {
       document.querySelector("#btn").onclick=function () {
        var xmlHttp=new XMLHttpRequest();
        xmlHttp.open("get","/data.json");
        xmlHttp.onreadystatechange=function () {
            if(xmlHttp.readyState==4){
                if(xmlHttp.status==200){
                    //获取服务端返回的字符串
                    var result=xmlHttp.responseText;
                    //将字符串转换为JSON
                    var json=JSON.parse(result);
                    for(var i=0;json.length;i++){
                        var user=json[i];
                        alert(user.id+"->"+user.userName+"->"+user.address);
                    }

                }
            }

        };
        xmlHttp.send();
       };
    })();
</script>

</body>
</html>
