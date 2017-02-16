<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备管理</title>
    <!-- Tell tde browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <!-- tdeme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from tde css/skins
         folder instead of downloading all of tdem to reduce tde load. -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
<%@include file="../include/head.jsp"%>

    <!-- =============================================== -->

    <!-- Left side column. contains tde sidebar -->
    <%@include file="../include/side.jsp"%>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>

                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 业务</a></li>
                <li><a href="/device/list">设备管理</a></li>
                <li class="active">设备库存</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">设备列表</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="box">

                        <div class="box-body">
                            <table class="table table-bordered">
                                <tr>
                                    <th>设备号</th>
                                    <th>设备名称</th>
                                    <th>总数量</th>
                                    <th>库存量</th>
                                    <th>单位</th>
                                    <th>租金(/每单位/每天）</th>
                                    <th>修改时间</th>
                                    <th>创建时间</th>
                                </tr>
                        <%--<c:forEach items="${deviceList}" var="device">--%>
                                <c:forEach items="${page.items}" var="device">
                                <tr>
                                    <td>${device.id}</td>
                                    <td>${device.deviceName}</td>
                                    <td>${device.totalNum}</td>
                                    <td>${device.currentNum}</td>
                                    <td>${device.unit}</td>
                                    <td>${device.rentMoney}</td>
                                    <td>${device.updateTime}</td>
                                    <td>${device.createTime}</td>

                                </tr>
                        </c:forEach>
                            </table>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <ul style="margin:5px 0px" id="pagination" class="pagination pull-right"></ul>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.3
        </div>
        <strong>Copyright &copy; 2017 <a href="http://hngc.com">河南功成</a>.</strong> All rights
        reserved.
    </footer>


</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:4,
            href:"/device/list?p={{number}}",
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"末页"
        });
    });
</script>
</body>
</html>
