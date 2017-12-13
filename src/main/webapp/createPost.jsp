<%--
  Created by IntelliJ IDEA.
  User: skindtc
  Date: 28.11.2017
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <title>Create Post</title>
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
    <form class="col s12 offset-l3" action="<c:url value="/createPost"/>" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="input-field col s6">
                <input name="title" id="input_text" type="text" data-length="100">
                <label class="active" for="input_text">Title</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <textarea name="content" id="textarea1" class="materialize-textarea" data-length="3000"></textarea>
                <label for="textarea1">Post Text</label>
            </div>
        </div>
        <div class="row">
            <input type="file" name="image">
        </div>
        <input type="submit" class="waves-effect waves-light btn" value="Create">
        <input name="status" type="checkbox" class="filled-in" id="filled-in-box" checked="checked"/>
        <label for="filled-in-box">Publish</label>
    </form>
    </div>
</div>
</body>
</html>
