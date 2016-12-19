<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6 0006
  Time: 下午 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="text" id="username">
<span id="loading" style="display: none"><img src="../static/img/loding.gif " alt=""></span>
<span id="text"></span>

<script src="../static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {

        $(document).ajaxSend(function () {
            $("#loading").show();
        });
        $(document).ajaxComplete(function () {
            $("#loading").hide();

        });
        $("#username").blur(function () {
            var name=$(this).val();
            /* $.ajax({
                url:"/ajax",
                type:"post",
                data:{"name":name,"age":24},
                timeout:15000,
                beforeSend:function () {
                    //请求发送之前的函数
                   // $("#loading").text("请求中...");
                    $("#loading").show();
                    $("#text").text("");

                },
                success:function (data) {
                    //alert(data);
                    $("#text").text(data);
                },
                error:function () {
                  alert("服务器异常");
                },
                complete:function () {
                    //无论success和error都会执行
                    //$("#loading").text("");
                    $("#loading").hide();


                }

            });*/

            /*$.post("/ajax",{"name":name,"age":23}).done(function (data) {
                $("#text").text(data);
            }).error(function () {
                alert("服务器异常");
            });*/


            $.get("/ajax", {"name": "ajax"}, function (data) {
                   alert(data);

               });

        });
    });
</script>
</body>
</html>
