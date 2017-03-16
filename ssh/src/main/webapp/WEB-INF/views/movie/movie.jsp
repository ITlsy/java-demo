<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
   <h2> ${movie.title}</h2>
    <hr>
    ${movie.jianjie}
    <hr>
    ${movie.daoyan} | ${movie.rate} | ${movie.sendtime} | ${movie.releaseyear}
    <hr>
    <c:forEach items="${movie.categoryList}" var="category">
        ${category.name}
    </c:forEach>


</div>
</body>
</html>
