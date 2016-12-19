<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/14 0014
  Time: 下午 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
    <link rel="stylesheet" href="/static/cs-s/bootstrap.min.css">
</head>
<body>
<div id="picker">选择文件</div>
<button id="btn">开始上传</button>
<ul id="fileList"></ul>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>
<script type="text/template" id="bar">
    <div class="progress">
        <div class="progress-bar" id="{{id}}" style="width: 0%;">
            <span class="sr-only"></span>
        </div>
    </div>
</script>
<script>
    $(function () {
        var uploader = WebUploader.create({
            swf: "/static/js/webuploader/Uploader.swf",
            server: "/uploader",
            pick: "#picker",
            fileVal: "file"
        });

        uploader.on("fileQueued", function (file) {
            var html = "<li id='" + file.id + "'>" + file.name + "</li>";
            $("#fileList").append($(html));

        });
        uploader.on("uploadProgress", function (file, percentage) {
            var num = parseInt(percentage * 100);
            var $bar = $("#" + file.id).find("#bar_" +file.id);
            if(!$bar[0]){
            var template=$("#bar").html();
            template=template.replace("{{id}}","bar_"+file.id);
            $("#"+file.id).append($(template));

            }else {
                $bar.css("width",num+"%");
            }


        });
        uploader.on("uploadSuccess", function (file) {
            $("#fileList").find("#" + file.id).css("color", "green");

        });
        uploader.on("uploadError", function (file) {
            $("#fileList").find("#" + file.id).css("color", "red");

        });
        uploader.on("uploadComplete", function (file) {
            $("#"+file.id).find("#bar_"+file.id).parent().remove();
        });

        $("#btn").click(function () {
            uploader.upload();

        });


    });
</script>
</body>
</html>
