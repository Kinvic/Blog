
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  </head>
  <body>
  <nav>
      <div class="nav-wrapper">
          <a href="#" class="brand-logo right">MyBlog</a>
          <ul id="nav-mobile" class="left hide-on-med-and-down">
              <li><a href="<c:url value="/myposts" />">My Posts</a></li>
              <li><a href="<c:url value="/createPost"/>">Create Post</a></li>
              <li><a href="<c:url value="/signIn"/>">LogOut</a></li>
          </ul>
      </div>
  </nav>
  <br>
  <form action="<c:url value="/findpost"/>" class="row" method="post">
      <div class="input-field col s11 m6 offset-l3">
          <input name="search" placeholder="Search" id="search" type="text" class="validate" value="${query}">
          <label for="search"></label>
      </div>
      <div class="col s3">
          <button type="submit" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">search</i></button>
      </div>
  </form>
<c:forEach items="${requestScope.posts}" var="post">
  <div class="row">
      <div class="col s12 m6 offset-l3">
          <div class="card-panel hoverable">
          <div class="card blue-grey darken-1">
              <div class="card-content white-text">
                  <span class="card-title">
                  <div class="center-align">
                      <c:out value="${post.getTitle()}"/>
                  </div>
                  </span>
                  <p class="truncate"><c:out value="${post.getContent()}"/></p>
              </div>
              <div class="card-action">
                  <a href="${ctx}/post?id=${post.getId()}">Read</a>
              </div>
          </div>
          </div>
      </div>
  </div>
</c:forEach>
<div class="center-align">
<ul class="pagination">
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="active"><a href="${ctx}/home?page=${currentPage}">${currentPage}</a></li>
            </c:when>
            <c:otherwise>
                <li class="waves-effect"><a href="${ctx}/home?page=${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>
</div>
<br>
</body>
</html>
