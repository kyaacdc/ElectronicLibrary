<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>BookData</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

</head>
<body>
<a href="/maincontent">Back to Book menu</a>

<h1>${book.bookTitle}</h1>
<h1>Book Rating - Likes</h1>

<table class="tg">
    <tr>
        <th width="100">Username</th>
        <th width="100">Likes</th>
    </tr>

    <c:forEach items="${listLikes}" var="like">
    <tr>
        <td>
            <c:forEach items="${listUsers}" var="user">
                <c:if test="${like.userId == user.id}">
                    ${user.username}
                </c:if>
            </c:forEach>
        </td>
        <td>${like.amount}</td>
    </tr>
    </c:forEach>
</table>

<h1>Book Rating - Dislikes</h1>

<table class="tg">
    <tr>
        <th width="100">Username</th>
        <th width="100">Dislikes</th>
    </tr>

    <c:forEach items="${listDislikes}" var="dislike">
        <tr>
            <td>
                <c:forEach items="${listUsers}" var="user">
                    <c:if test="${dislike.userId == user.id}">
                        ${user.username}
                    </c:if>
                </c:forEach>
            </td>
            <td>${dislike.amount}</td>
        </tr>
    </c:forEach>
</table>

<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>

</body>
</html>