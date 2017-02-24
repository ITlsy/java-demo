<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务派遣</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_device_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">劳务外包</h3>

                    <div class="box-tools pull-right">
                        <a href="/device/work" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
                <div class="box-body">

                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>租赁公司</label>
                                <input type="text" class="form-control" id="companyName" tabindex="1">
                            </div>
                            <div class="form-group">
                                <label>地址</label>
                                <input type="text" class="form-control"  tabindex="4" id="address">
                            </div>
                            <div class="form-group">
                                <label>开始日期</label>
                                <input type="text" class="form-control" id="beginDate" readonly tabindex="7">
                            </div>
                            <div class="form-group">
                                <label>佣金金额</label>
                                {{total}}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>法人代表</label>
                                <input type="text" class="form-control" tabindex="2" id="linkMan">
                            </div>
                            <div class="form-group">
                                <label>电话</label>
                                <input type="text" class="form-control" tabindex="5" id="tel">
                            </div>
                            <div class="form-group">
                                <label>结束日期</label>
                                <input type="text" class="form-control" id="lastDate" tabindex="8">
                            </div>
                            <div class="form-group">
                                <label>预付款</label>
                                {{preCost}}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司电话</label>
                                <input type="text" class="form-control" tabindex="3" id="telephone">
                            </div>
                            <div class="form-group">
                                <label>身份证号</label>
                                <input type="text" class="form-control" tabindex="6" id="cardNum">
                            </div>
                            <div class="form-group">
                                <label>总天数</label>
                                <input type="text" class="form-control" tabindex="9" id="totalDays" readonly>
                            </div>
                            <div class="form-group">
                                <label>尾款</label>
                               {{lastCost}}
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="box-header">
                                <h3 class="box-title">工种列表</h3>
                                <div class="box-tools pull-right">
                                    <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>工种名称</th>
                                        <th>工种单位佣金</th>
                                        <th>工种数量</th>
                                        <th>小计</th>
                                        <th>#</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr v-if="workArray.length==0">
                                        <td colspan="5">暂无数据</td>
                                    </tr>
                                    <tr v-for="work in workArray">
                                        <td>{{work.name}}</td>
                                        <td>{{work.price}}</td>
                                        <td>{{work.num}}</td>
                                        <td>{{work.total}}</td>
                                        <td>
                                            <a href="javascript:;" @click="remove(work)"><i class="fa fa-trash text-danger"></i></a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                        <div class="box" style="padding-left: 20px">
                            <div class="box-header">
                                <span class="title"><i class="fa fa-user"></i> 合同上传</span>
                            </div>
                            <form action="" class="form-horizontal">
                                <hr>
                                <p style="padding-left: 20px">注意事项</p>
                                <ul>
                                    <li>上传合同扫描件要求清晰可见</li>
                                    <li>合同必须公司法人签字盖章</li>
                                </ul>
                                <div class="form-actions">
                                    <div id="picker">上传合同</div>
                                    <ul id="fileList">
                                    </ul>
                                    <button class="btn btn-default pull-right" type="button">重置</button>
                                    <button class="btn btn-primary pull-right " type="button" @click="saveWork">保存</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
         <div class="modal fade" id="myModal">
           <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择工种</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="workName">
                            <label>工种名称</label>
                            <select id="workId" style="width: 400px;" class="form-control">
                                <option value="">选择工种</option>
                                <c:forEach items="${workList}" var="work">
                                    <option value="${work.id}">${work.workName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>工种单位佣金</label>
                            <input type="text" class="form-control" id="workPrice" readonly>
                        </div>
                        <div class="form-group">
                            <label>工种数量</label>
                            <input type="text" class="form-control" id="currentNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>派遣数量</label>
                            <input type="text" class="form-control" id="outNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addWork">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    </div>


<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script src="/static/plugins/vue.js"></script>
<script src="/static/plugins/layer/layer.js"></script>

<script>
    var fileArray=[];

    $(function () {
        $("#beginDate").val(moment().format("YYYY-MM-DD"));
        $("#lastDate").datepicker({
           format:"yyyy-mm-dd",
            language:"ZH-CN",
            autoclose:true,
            startDate:moment().add(1,'days').format("YYYY-MM-DD")
        }).on("changeDate",function (e) {
            var beginDay=moment();
            var lastDay=moment(e.format(0,'yyyy-mm-dd'));
            var days=lastDay.diff(beginDay,'days')+1;
            $("#totalDays").val(days);
        });

        $("#workId").select2();
        $("#workId").change(function () {
           var id=$(this).val();
           if(id){
               $.get("/device/work/work.json",{"id":id}).done(function (resp) {
                   if(resp.status=='success'){
                       var work=resp.data;
                       $("#workName").val(work.workName);
                       $("#workPrice").val(work.workPrice);
                       $("#currentNum").val(work.currentNum);

                   }else {
                       layer.msg(resp.message);
                   }
               }).error(function () {
                  layer.msg("服务器忙，请稍后再试");
               });
           }
        });


        var uploder = WebUploader.create({
            swf : "js/uploader/Uploader.swf",
            server: "/file/upload",
            pick: '#picker',
            auto : true,
            fileVal:'file'
        });
        uploder.on("uploadSuccess",function (file,resp) {
            layer.msg("上传成功");
            var html="<li>"+resp.data.sourceFileName+"</li>";
                $("#fileList").append(html);
                var json={
                    sourceName:resp.data.sourceFileName,
                    newName:resp.data.newFileName
                };
                fileArray.push(json);
        });
        uploder.on("uploadError",function () {
           layer.msg("服务器忙，请稍后");
        });

    });

    var app=new Vue({
        el:"#app",
        data:{
            workArray:[]
        },
        methods:{
            addWork:function () {
                var id=$("#workId").val();
               var flag=false;
               for (var i=0;i<this.$data.workArray.length;i++){
                   var item=this.$data.workArray.length;
                   if(item.id==id){
                       this.$data.workArray[i].num=parseFloat(this.$data.workArray[i].num)+parseFloat($("#outNum").val());
                       this.$data.workArray[i].total =parseFloat(this.$data.workArray[i].num)*parseFloat($("#workPrice").val());
                       flag=true;
                       break;
                   }
               }
               if (!flag){
                   var json={};
                   json.id=id;
                   json.name=$("#workName").val();
                   json.price=$("#workPrice").val();
                   json.num=$("#outNum").val();
                   json.total=parseFloat(json.price)*parseFloat(json.num);
                   this.$data.workArray.push(json);
               }
                
            },
            remove:function(work){
                layer.confirm("确定要删除吗",function(index){
                    app.$data.workArray.splice(app.$data.workArray.indexOf(work),1);
                    layer.close(index);
                });
            },
            saveWork:function () {
                var json={
                    workArray:this.$data.workArray,
                    fileArray:fileArray,
                    companyName:$("#companyName").val(),
                    address:$("#address").val(),
                    telephone:$("#telephone").val(),
                    linkMan:$("#linkMan").val(),
                    tel:$("#tel").val(),
                    cardNum:$("#cardNum").val(),
                    beginDate:$("#beginDate").val(),
                    lastDate:$("#lastDate").val(),
                    totalDays:$("#totalDays").val()

                };
                //将json转化为字符串
                $.ajax({
                   url:"/device/work/new",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType:"application/json;charset=UTF-8",
                    success:function (resp) {
                        if(resp.status=='success'){
                        layer.confirm("保存成功",{btn:['继续添加','打印合同']},function () {
                           window.history.go(0);
                        },function () {
                            window.location.href="/device/work/"+resp.data;
                        });
                        }else{
                            layer.msg(resp.message);
                        }
                    },
                    error:function () {
                        layer.msg("服务器忙，请稍后");
                    }
                });
            }
        },

        computed:{
            total : function(){
                var result = 0;
                for (var i=0;i<this.$data.workArray.length;i++){
                    var item=this.$data.workArray[i];
                    result=+item.total;
                }
                return result;
            },
            preCost:function(){
                return this.total * 0.2;
            },
            lastCost:function () {
                return this.total-this.preCost;
            }
        }

    });


</script>
</body>
</html>
