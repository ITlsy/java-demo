<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<%@ include file="../include/navbar.jsp" %>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#">首页</a>
                </li>
                <li>
                    <a href="#">资料</a>
                </li>
                <li class="disabled">
                    <a href="#">信息</a>
                </li>
            </ul>
            <div class="row">
                <c:choose>
                    <c:when test="${topic.user.username}=${sessionScope.curr_user.username}">

                        <c:forEach items="${page.items}" var="topic">

                    <div class="span6">
                        <img class="avatar" src="${topic.user.avatar}?imageView2/1/w/40/h/40" alt="">
                       </div>

                    <div class="span6">
                        <h2>
                            简介
                        </h2>
                        <p>
                            昵称 <a href="/home">${topic.user.username}</a>,创建时间:一个月前。

                        </p>
                        <p>
                            <a class="btn" href="#">查看更多 »</a>
                        </p>
                    </div>
                        </c:forEach>
                       </c:when>

                       <c:otherwise>
                           <div class="span6">
                               <img class="avatar" src="${topic.user.avatar}?imageView2/1/w/40/h/40" alt="">
                           </div>

                           <div class="span6">
                               <h2>
                                   简介
                               </h2>
                               <p>
                                   昵称 <a href="/home">${topic.user.username}</a>,创建时间:一个月前。

                               </p>
                               <p>
                                   <a class="btn" href="#">查看更多 »</a>
                               </p>
                           </div>
                       </c:otherwise>
                   </c:choose>

            </div>
        </div>
    </div>

</div>
</body>
</html>
