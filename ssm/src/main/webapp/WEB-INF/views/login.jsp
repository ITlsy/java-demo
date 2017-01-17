<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/16 0016
  Time: 下午 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>
    <form method="post">
        <legend>系统登录</legend>
        <div class="form-group">
            <label>账号</label>
            <input type="text" name="username" class="form-control">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" name="password" class="form-control">
        </div>
        <div class="form-group">
            <button class="btn">登录</button>
        </div>

    </form>
</div>
</body>
</html>
