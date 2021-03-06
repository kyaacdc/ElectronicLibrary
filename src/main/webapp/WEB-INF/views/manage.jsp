<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="name" content="">

    <title>Welcome to Manage Panel of Electronic Library</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<a href="/welcome">Back to main menu</a>

<div class="container">
    <h1>Welcome to Manage Panel of Electronic Library</h1>
    <br><br>
    <a href="<c:url value="/maincontent"/>" ><h4>View Books</h4></a><br><br><br>
    <a href="<c:url value="/books"/>"><h4>Manage Books</h4></a>
    <br><br>
    <a href="<c:url value="/users"/>"><h4>Manage Users</h4></a>
    <br><br>
    <a href="<c:url value="/tags"/>"><h4>Manage Tags</h4></a>
    <br><br>
    <a href="<c:url value="/comments"/>"><h4>Manage Comments</h4></a>
    <br><br>
    <a href="<c:url value="/likes"/>"><h4>Manage Books Rate</h4></a>

</div>
<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>