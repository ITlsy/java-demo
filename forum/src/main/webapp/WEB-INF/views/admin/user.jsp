<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>账号</th>
            <th>注册时间</th>
            <th>最后登录时间</th>
            <th>最后登录IP</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>tom</td>
            <td>2016-12-12 12:33</td>
            <td>2016-12-34 11:11</td>
            <td>23.33.221.56</td>
            <td>
                <a href="">禁用</a>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="pagination pagination-mini pagination-centered">
        <ul id="pagination" style="margin-bottom:20px;"></ul>
    </div>
</div>
    <script>
        $(function () {
            $("#pagination").twbsPagination({
                totalPages:${page.totalPage},
                visiblePages:5,
                first:'首页',
                last:'末页',
                prev:'上一页',
                next:'下一页',
                href: '?p={{number}}'
            });
        });
    </script>
</div>
<!--container end-->
</body>
</html>

