<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加节点</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <form id="addForm" action="">
        <legend>添加新节点</legend>
        <label>节点名称</label>
        <input type="text" name="nodename" value="${node.nodename}">

    </form>
    <div class="form-actions">
        <button class="btn btn-primary" id="addBtn">保存</button>
    </div>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {
        $("#addBtn").click(function () {
            $("#addForm").submit();
        });

        $("#addForm").validate({
            errorElement:"span",
            errorClass:"text-error",
            rules:{
                nodename:{
                    required:true,
                }
            },
            messages:{
                nodename:{
                    required:"请输入节点名称",
                }
            },
            submitHandler:function () {
                $.ajax({
                    url:"/admin/newNode",
                    type:'post',
                    data:$("#addForm").serialize(),
                    success:function (json) {
                          if(json.state=="success"){
                              alert("添加成功");
                              window.location.href ="/admin/node";
                          }else {
                              alert(json.message);
                          }
                    },
                    error:function () {
                        alert("添加失败,服务器异常");
                    }
                });
            }
        })

    });
</script>
</body>
</html>

