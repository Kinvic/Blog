<%--
  Created by IntelliJ IDEA.
  User: skindtc
  Date: 28.11.2017
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</head>
<body>
<nav>
    <div class="nav-wrapper">
        <a href="${ctx}/home" class="brand-logo right">MyBlog</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li><a href="${ctx}/home">Home</a></li>
            <li><a href="${ctx}/myposts">My Posts</a></li>
            <li><a href="${ctx}/signIn">LogOut</a></li>
        </ul>
    </div>
</nav>
<div class="center-align">
    <div class="row">
        <div class="col s9 l6 offset-l3">
            <h1><c:out value="${requestScope.post.getTitle()}"/></h1>
            <cite>Post was created by <c:out value="${requestScope.post.getCreator().getUsername()}"/></cite>
            <c:if test="${requestScope.post.getImage() != null}">
                <img src="${img}" width="100%" height="300px"/>
            </c:if>
            <p><c:out value="${requestScope.post.getContent()}"/></p>
            <cite>published <javatime:format value="${requestScope.post.getCreationDate()}" pattern="yyyy-MM-dd HH:mm:ss"/></cite>
            <br>
            <c:if test="${requestScope.post.getCreator().getId() == requestScope.userId}">
                <a class="waves-effect waves-light btn" href="<c:url value="/editpost?id=${post.getId()}" />">Edit</a>
                <a class="waves-effect waves-light btn" href="<c:url value="/delete?id=${post.getId()}" />">Delete</a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
