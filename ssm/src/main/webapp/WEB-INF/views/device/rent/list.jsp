<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_device_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">租赁合同列表</h3>

                    <div class="box-tools pull-right">
                        <a href="/device/rent/new" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>流水号</th>
                            <th>租赁公司</th>
                            <th>电话</th>
                            <th>租赁日期</th>
                            <th>归还日期</th>
                            <th>状态</th>
                            <th>租金</th>
                            <th>违约金</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp"%>
<script>
    var table=$(".table").DataTable({
        "lengthMenu":[5,10,15,20],
        "serverSide":true,
        "ajax":{
            "url":"/setting/device/load",
            "type":"post",
            "data":function (obj) {
                obj.deviceName=$("#q_device_name").val();

            }

        },
        "searching":false,//不使用自带的搜索
        "order":[[0,'desc']],//默认排序方式
        "columns":[
            {"data":"id","name":"id"},
            {"data":"name"},
            {"data":"unit"},
            {"data":"totalNum","name":"total_num"},
            {"data":"currentNum","name":"current_num"},
            {"data":"price","name":"price"},
            {"data":function(obj){
                return "<a href='javascript:;' rel='"+obj.id+"' class='delLink'>删除</a>";
            }}
        ],
        "columnDefs":[
            {targets:[0],visible: false},
            {targets:[1,2,6],orderable:false}
        ],
        "language":{ //定义中文
            "search": "搜索:",
            "zeroRecords":    "没有匹配的数据",
            "lengthMenu":     "显示 _MENU_ 条数据",
            "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
            "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
            "loadingRecords": "加载中...",
            "processing":     "处理中...",
            "paginate": {
                "first":      "首页",
                "last":       "末页",
                "next":       "下一页",
                "previous":   "上一页"
            }
        }

    });
</script>
</body>
</html>
