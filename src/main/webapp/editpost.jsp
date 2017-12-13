<%--
  Created by IntelliJ IDEA.
  User: skindtc
  Date: 29.11.2017
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Post</title>
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
            <li><a href="<c:url value="/home"/>">Home</a></li>
            <li><a href="<c:url value="/myposts"/>">My Posts</a></li>
            <li><a href="<c:url value="/signIn"/>">LogOut</a></li>
        </ul>
    </div>
</nav>
<br>
<div class="row">
    <div class="valign-wrapper">
        <form class="col s12 offset-l3" action="<c:url value="/editpost"/>" method="post">
            <input type="hidden" name="id" value="${requestScope.post.getId()}"/>
            <div class="row">
                <div class="input-field col s6">
                    <input name="title" id="input_text" type="text" data-length="40" value="${requestScope.post.getTitle()}">
                    <label class="active" for="input_text">Title</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <input name="content" id="textarea" class="materialize-textarea" data-length="1000" value="${requestScope.post.getContent()}">
                    <label class="active" for="textarea">Post Text</label>
                </div>
            </div>
            <input type="submit" class="waves-effect waves-light btn" value="Edit">
            <c:choose>
                <c:when test="${requestScope.post.isPosted() == true}">
                    <input name="status" type="checkbox" class="filled-in" id="filled-in-box" checked="checked"/>
                </c:when>
                <c:otherwise>
                    <input name="status" type="checkbox" class="filled-in" id="filled-in-box"/>
                </c:otherwise>
            </c:choose>
            <label for="filled-in-box">Publish</label>
        </form>
    </div>
</div>
</body>
</html>
