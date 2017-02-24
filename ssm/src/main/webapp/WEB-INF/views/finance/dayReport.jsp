<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务日报</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="finance_day"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" class="form-control" id="date">
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">财务日报</h3>

                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="exportExcel" class="btn btn-default"><i class="fa fa-file-o"></i> 导出Excel</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>流水号</th>
                            <th>创建日期</th>
                            <th>类型</th>
                            <th>金额</th>
                            <th>业务模块</th>
                            <th>业务流水</th>
                            <th>状态</th>
                            <th>备注</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">今日收支统计</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div id="inChart" style="width: 100%;height: 300px"></div>
                        </div>
                        <div class="col-md-6">
                            <div id="outChart" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/echarts.min.js"></script>
<script>
    $(function () {
            $("#date").val(moment().format("YYYY-MM-DD"));

            $("#date").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            endDate:moment().format("YYYY-MM-DD")
            }).on("changeDate",function(e){
            var today = e.format(0,'yyyy-mm-dd');
            table.ajax.reload(false,null);
            /*table.ajax.reload();
            loadPie();*/
            });

        var table=$(".table").DataTable({
            "lengthChange":false,
            "pageLength":25,
                "serverSide":true,
            "ajax":{
                "url":"/finance/day/load",
               "type":"get",
               "data":function (obj) {
                   obj.day =$("#date").val()
               }
            },
           "searching":false,
           "order":[[0,'desc']],
           "ordering":false,
           "columns":[
               {"data":"id","name":"id"},
               {"data":"serialNumber"},
               {"data":"createDate"},
               {"data":"type"},
               {"data":"money"},
               {"data":"module"},
               {"data":"moduleSerialNumber"},
               {"data":"state"},
               {"data":"mark"},
               {"data":function (row) {
                   if(row.state=="未确认"){
                       return "<a href='javascript:;' class='confirm_btn' rel='"+row.id+"'>确认</a>";
                   }else {
                       return "";
                   }
               }}
           ],
           "columnDefs":[
               {targets:[0],visible: false}
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

        $(document).delegate(".confirm_btn","click",function () {
               var id=$(this).attr("rel");
               layer.confirm("确认已收(付)款?",function (index) {
                   layer.close(index);
                   $.post("/finance/confirm/"+id).done(function (resp) {
                        if(resp.status=='success'){
                            layer.msg("确认成功");
                            table.ajax.reload(false,null);
                        }else {
                            layer.msg(resp.message);
                        }
                   }).error(function () {
                       layer.msg("服务器忙，请稍后");
                   });
               });
        });

        //导出Excel文件
        $("#exportExcel").click(function () {
           var day=$("#date").val();
           window.location.href="/finance/day/"+day+"/data.xls";
        });

        //echart图
        var inChart=echarts.init($("#inChart")[0]);
        var outChart=echarts.init($("#outChart")[0]);
        var option={
            title:{
                left:"center"
            },
            tooltip:{},
            legend:{
                left:20,
                orient:'vertical',
                data:[]
            },
            series:[]
        };
        inChart.setOption(option);
        outChart.setOption(option);
        
        function loadPie() {
            //收入统计
            $.get("/finance/day/in/"+$("#date").val()+"/pie").done(function (resp) {
                if(resp.status=='success'){
                    var nameArray=[];
                    for (var i=0;i<resp.data.length;i++){
                        var obj=resp.data[i];
                        nameArray.push(obj.name);
                    }
                    inChart.setOption({
                        title:{
                            text:"收入统计"
                        },
                        legend:{
                            data:nameArray
                        },
                        series:[{
                            type:'pie',
                            name:"金额",
                            data:resp.data
                        }]

                    });
                }else {
                    layer.msg(resp.message);
                }
                
            }).error(function () {
               layer.msg("加载饼图异常");
            });

            //支出统计
            $.get("/finance/day/out/"+$("#date").val()+"/pie").done(function (resp) {
            if(resp.status=='success'){
                var nameArray=[];
                for (var i=0;i<resp.data.length;i++){
                    var obj=resp.data[i];
                    nameArray.push(obj.name);
                }
                outChart.setOption({
                    title:{
                        text:"支出统计"
                    },
                    legend:{
                        data:nameArray
                    },
                    series:[{
                        type:'pie',
                        name:"金额",
                        data:resp.data
                    }]
                });
            }else {
                layer.msg(resp.message);
            }
            }).error(function () {
                layer.msg("加载饼图异常")
            });
        }
        loadPie();

    });
</script>
</body>
</html>
