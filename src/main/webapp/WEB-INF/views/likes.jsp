<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Tags Page</title>

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
<a href="/manage">Back to admin menu</a>

<h2>Manage Rate Info</h2>


<h1>Book Rating - Likes / Dislikes</h1>

<table>
    <tr>
        <td valign="top">
            <table class="tg">
                <tr>
                    <th width="100">LikeId</th>
                    <th width="100">BookTitle</th>
                    <th width="100">Username</th>
                    <th width="100">Likes</th>
                    <th width="60">Delete</th>
                </tr>

                <c:forEach items="${listLikes}" var="like">
                    <tr>
                        <td>${like.id}</td>
                        <td>
                            <c:forEach items="${listBooks}" var="book">
                                <c:if test="${like.bookId == book.id}">
                                    ${book.bookTitle}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${listUsers}" var="user">
                                <c:if test="${like.userId == user.id}">
                                    ${user.username}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${like.amount}</td>
                        <td>
                            <form action="<c:url value='/likes/remove/${like.id}'/>">
                                <input type="hidden" name="islike" value=1>
                                <input type="hidden" name="bookId" value=${like.bookId}>
                                <input type="hidden" name="userId" value=${like.userId}>
                                <p><input type="submit" value="Delete"></p>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>

        <td valign="top">
            <table class="tg">
                <tr>
                    <th width="100">DislikeId</th>
                    <th width="100">BookTitle</th>
                    <th width="100">Username</th>
                    <th width="100">Dislikes</th>
                    <th width="60">Delete</th>
                </tr>

                <c:forEach items="${listDislikes}" var="dislike">
                    <tr>
                        <td>${dislike.id}</td>
                        <td>
                            <c:forEach items="${listBooks}" var="book">
                                <c:if test="${dislike.bookId == book.id}">
                                    ${book.bookTitle}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${listUsers}" var="user">
                                <c:if test="${dislike.userId == user.id}">
                                    ${user.username}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${dislike.amount}</td>
                        <td>
                            <form action="<c:url value='/likes/remove/${dislike.id}'/>">
                                <input type="hidden" name="islike" value=0>
                                <input type="hidden" name="bookId" value=${dislike.bookId}>
                                <input type="hidden" name="userId" value=${dislike.userId}>
                                <p><input type="submit" value="Delete"></p>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>


<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>

</body>
</html>

