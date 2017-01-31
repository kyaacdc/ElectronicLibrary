<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Electronic Library File Uploader</title>
</head>
<body>
<a href="/books">Back to  Book Admin menu</a>

<h1>Welcome to Electronic Library File Uploader</h1>

Click on below links for Upload this Book.<br/><br/>
<form:form method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
    <h4>Please select a file to upload :</h4>
    <input type="file" name="file" />
    <input type="submit" value="upload" />
</form:form>
<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>
</body>
</html>