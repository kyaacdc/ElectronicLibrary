<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

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

<form action="/bookfind">
    <label for="bookTitle">Exactly Search One book by Title:</label>
    <input type="text" title="bookTitle" name="bookTitle" placeholder="bookTitle"/>
    <input type="submit" value="Search"/>
</form>

<form action="/bookfindTitle">
    <label for="bookTitle">Search list books by Title:</label>
    <input type="text" title="bookTitle" name="bookTitle" placeholder="bookTitle"/>
    <input type="submit" value="Search"/>
</form>

<form action="/bookfindDescr">
    <label for="description">Search list books by Description:</label>
    <input type="text" title="description" name="description" placeholder="description"/>
    <input type="submit" value="Search"/>
</form>

<h1>Book List</h1>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">ISBN</th>
            <th width="80">Description</th>
            <th width="120">Image</th>
            <th width="120">Path</th>
            <th width="120">Likes</th>
            <th width="120">Dislikes</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/bookdata/${book.id}" target="_blank">${book.bookTitle}</a></td>
                <td>${book.bookAuthor}</td>
                <td>${book.isbn}</td>
                <td>${book.description}</td>
                <td>${book.image}</td>
                <td>${book.path}</td>
                <td>${book.likes}</td>
                <td>${book.dislikes}</td>
                <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h2>Add or Update a Book Info</h2>

<form action="/fileDownload">
    <h5>Download file of Book from Server</h5>
    <input type="submit" value="Download file of new Book"/>
</form>

<form action="/fileUpload">
    <h5>Upload new file of Book on Server</h5>
    <input type="submit" value="Upload file of new Book"/>
</form>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" commandName="book">
    <table>
        <c:if test="${!empty book.bookTitle}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
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
                <form:label path="bookTitle">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookTitle"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="bookAuthor">
                    <spring:message text="Author"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookAuthor"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isbn">
                    <spring:message text="ISBN"/>
                </form:label>
            </td>
            <td>
                <form:input path="isbn"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="image">
                    <spring:message text="Image"/>
                </form:label>
            </td>
            <td>
                <form:input path="image"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="path">
                    <spring:message text="Path"/>
                </form:label>
            </td>
            <td>
                <form:input path="path"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="likes">
                    <spring:message text="Likes"/>
                </form:label>
            </td>
            <td>
                <form:input path="likes"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dislikes">
                    <spring:message text="Dislikes"/>
                </form:label>
            </td>
            <td>
                <form:input path="dislikes"/>
            </td>
        </tr>
        <td colspan="2">
            <c:if test="${!empty book.bookTitle}">
                <input type="submit"
                       value="<spring:message text="Edit Info"/>"/>
            </c:if>
            <c:if test="${empty book.bookTitle}">
                <input type="submit"
                       value="<spring:message text="Add Info"/>"/>
            </c:if>
        </td>
        </tr>
    </table>
</form:form>

</body>
</html>
