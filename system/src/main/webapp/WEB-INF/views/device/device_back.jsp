<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/21 0021
  Time: 下午 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备入库</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
    <!-- 文件上传 -->
    <link rel="stylesheet" href="js/uploader/webuploader.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/head.jsp"%>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
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
                <li><a href="#">设备租赁</a></li>
                <li class="active">设备入库</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">设备入库</h3>

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
                            <form role="form" >
                                <div class="box-body" class="form-group">

                                    <!--设备 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="device_name">设备名称：&nbsp</label>
                                                <input type="text" class="" disabled="disabled"  name="device_name" value="扣件" >
                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div >
                                                <label for="totalNum">应还数量：&nbsp</label>
                                                <input type="text" class="" disabled="disabled"  name="totalNum" value="10000"><span>个</span>

                                            </div>
                                        </div>


                                        <div class="col-lg-3">
                                            <div>
                                                <label for="">实还数量：&nbsp</label>
                                                <input type="text" class="" id="" value="9500"><span>个</span>
                                            </div>
                                        </div>
                                    </div>


                                    </br>
                                    <div class="row">

                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="">丢失数量：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="" value="500（自动算出）">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="number">应&nbsp;&nbsp赔&nbsp付：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="" value="1000.00（自动算出）">
                                            </div>
                                        </div>

                                    </div>


                                    <div class="row">

                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label for="">损坏数量：&nbsp</label>
                                                <input type="text"  class="" id="daynumber" value="1200">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="number">应&nbsp;&nbsp赔&nbsp付：&nbsp</label>
                                                <input type="text" disabled="disabled" class="" id="" value="2000.00（自动算出）">
                                            </div>
                                        </div>

                                    </div>

                                    <!--公司 -->
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="" >
                                                <label for="">剩余租金：&nbsp</label>
                                                <input type="text" disabled="disabled"  class="" id="" value="8000.00">

                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div >
                                                <label for="">违约金额：&nbsp</label>
                                                <input type="text" class="" name="" disabled="disabled" value="0.00（自动算出）" >
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div >
                                                <label for="">结算金额：&nbsp</label>
                                                <input type="text" class="" name="" disabled="disabled" value="11000.00（自动算出）" >
                                            </div>
                                        </div>
                                    </div>


                                    <div> <br/></div>






                                    <div class="row">

                                        <div class="col-lg-3">

                                        </div>
                                        <div class="col-lg-3">
                                            <div class="box-footer">
                                                <button type="submit" class="btn btn-primary">结算</button>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="submit" class="btn btn-primary">重置</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- /.box-body -->

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
<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- datepicker -->
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="js/uploader/webuploader.min.js"></script>
<script>
    <!-- $(function() {		$( "#datepicker").datepicker();	});
    -->
    $(function () {
        $("#datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            //clearBtn: true,//清除按钮
            //todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
        //文件上传
        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "#",
            pick: '#picker',
            auto : true,
            fileVal:'file',
            /*accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        });

    });

</script>
</body>
</html>

