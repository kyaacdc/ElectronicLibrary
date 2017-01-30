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
    <meta name="author" content="">

    <title>Welcome to Electronic Library</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h1>Welcome to Electronic Library</h1>
    <br><br>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Hello ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>

    </c:if>

    <br><br>
    <a href="<c:url value="/maincontent"/>" target="_blank"><h3>View Books</h3></a><br><br><br>
    <a href="<c:url value="/books"/>" target="_blank"><h4>Manage Books (only for ROLE_ADMIN)</h4></a>
    <br><br><br>
    <a href="<c:url value="/users"/>" target="_blank"><h4>Manage Users (only for ROLE_ADMIN)</h4></a>
    <br><br><br>
    <a href="<c:url value="/tags"/>" target="_blank"><h4>Manage Tags (only for ROLE_ADMIN)</h4></a>



    <br><br><br><br><br><br>
    <h6>Designed by Yuriy Kozheurov</h6>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>