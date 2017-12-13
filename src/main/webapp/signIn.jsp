<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: skindtc
  Date: 28.11.2017
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
</head>

<body>
<div class="row">
    <form class="col s12 offset-l5" action="<c:url value="/signIn"/>" method="post">
        <div class="row">
            <div class="input-field col s2">
                <input name="login" id="user-name-label" type="text" class="validate">
                <label class="active" for="user-name-label">UserName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s2">
                <input name="password" id="password" type="password" class="validate">
                <label class="active" for="password">Password</label>
            </div>
        </div>
        <input type="submit" class="waves-effect waves-light btn" value="Login">
        <a class="waves-effect waves-light btn" href="<c:url value="/signUp"/>">Register</a>
    </form>
</div>
</body>
</html>
