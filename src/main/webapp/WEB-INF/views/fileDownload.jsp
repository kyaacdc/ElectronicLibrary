<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Electronic Library Book Downloader</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/common.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="form-container">
    <h1>Welcome to Electronic Library Book Downloader</h1>

    Click on below links for Open or Download this Book.<br/><br/>

    <a href="<c:url value='/download/externalview' />">View This File</a>
    <br/>
    <a href="<c:url value='/download/external' />">Download This File</a>

</div>
</body>
</html>