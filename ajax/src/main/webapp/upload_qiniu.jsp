<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
</head>
<body>
<div id="picker">选择文件</div>
<div id="result"></div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>
<script>
    $(function () {
        var uploader=WebUploader.create({
            swf:"/static/js/webuploader/Uploader.swf",
            server:"http://up-z1.qiniu.com/",
            fileVal:"file",
            formData:{"token":"${token}"},
            pick:"#picker",
            auto:true
        });

        uploader.on("uploadSuccess",function (file,data) {
           var url="http://oifocnk31.bkt.clouddn.com/"+data.key+"?imageView2/1/w/300/h/300";
           $("<img>").attr("src",url).addClass("img-rounded").appendTo($("#result"));

        });
        uploader.on("uploadError",function (file) {
            alert("上传错误");

        });

    });
</script>
</body>
</html>

