<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/12 0012
  Time: 下午 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form method="post">
    <input type="hidden" name="id" value="${user.id}">
    <div class="form-group">
        <label>账号</label>
        <input type="text" name="username" value="${user.username}" class="form-control">
    </div>
    <div class="form-group">
        <label>密码(如果不修改请留空)</label>
        <input type="password" name="password" value="" class="form-control">
    </div>
    <div class="form-group">
        <button class="btn btn-primary">保存</button>
    </div>
</form>
</div>
</body>
</html>
