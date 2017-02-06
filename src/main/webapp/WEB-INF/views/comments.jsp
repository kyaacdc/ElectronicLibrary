<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Comments Page</title>

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
<a href="/manage">Back to main menu</a>

<h2>Add/Edit Comment Info</h2>

<c:url var="addAction" value="/comments/add"/>

<form:form action="${addAction}" commandName="comment">
    <table>
        <c:if test="${!empty comment.description}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="id"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="bookId">
                    <spring:message text="bookId"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userId">
                    <spring:message text="userId"/>
                </form:label>
            </td>
            <td>
                <form:input path="userId"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty comment.description}">
                    <input type="submit"
                           value="<spring:message text="Edit Info"/>"/>
                </c:if>
                <c:if test="${empty comment.description}">
                    <input type="submit"
                           value="<spring:message text="Add Info"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

<h1>Comments List</h1>

<c:if test="${!empty listComments}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Description</th>
            <th width="120">Book</th>
            <th width="120">User</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listComments}" var="comment">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.description}</td>
                <td>
                    <c:forEach items="${listBooks}" var="book">
                        <c:if test="${book.id == comment.bookId}">
                            ${book.bookTitle}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${listUsers}" var="user">
                        <c:if test="${user.id == comment.userId}">
                            ${user.username}
                        </c:if>
                    </c:forEach>
                </td>
                <td><a href="<c:url value='/comments/edit/${comment.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/comments/remove/${comment.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>
</body>
</html>


