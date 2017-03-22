<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>new User</h1>
<form action="/user/save" method="post">
    <input type="text" name="user.userName">
    <input type="text" name="user.address">
    <button>save</button>
</form>
<ul>
    <c:forEach items="${names}" var="name">
      <li>${name}</li>
    </c:forEach>
</ul>
</body>
</html>
