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
<a href="/welcome">Back to main menu</a>

<h1>Tags List</h1>

<c:if test="${!empty listTags}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">name</th>
            <th width="120">BookName</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listTags}" var="tag">
            <tr>
                <td>${tag.id}</td>
                <td>${tag.tagname}</td>
                <td>
                    <c:forEach items="${listBooks}" var="book">
                        <c:if test="${book.id == tag.bookid}">
                            ${book.bookTitle}
                        </c:if>
                    </c:forEach>
                </td>
                <td><a href="<c:url value='/tags/edit/${tag.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/tags/remove/${tag.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h2>Add or Update Tag Info</h2>

<c:url var="addAction" value="/tags/add"/>

<form:form action="${addAction}" commandName="tag">
    <table>
        <c:if test="${!empty tag.tagname}">
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
                <form:label path="tagname">
                    <spring:message text="tagname"/>
                </form:label>
            </td>
            <td>
                <form:input path="tagname"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="bookid">
                    <spring:message text="bookid"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookid"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty tag.tagname}">
                    <input type="submit"
                           value="<spring:message text="Edit Info"/>"/>
                </c:if>
                <c:if test="${empty tag.tagname}">
                    <input type="submit"
                           value="<spring:message text="Add Info"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>

