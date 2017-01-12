<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <a href="/user/add" class="btn btn-primary">增加</a>
    <table class="table">
        <thead>
        <tr>
            <th>姓名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>
                    <a href="/user/${user.id}/edit">编辑</a>
                    <a href="/user/${user.id}/del">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
