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

<form action="/findBooks" name="Search By:">
    <table class="tg" name="Search By:">
        <tr>
            <th width="120">
                <select name=findOption size=1>
                    <option value=2 selected>(default)</option>
                    <option value=1>Tittle (exactly)</option>
                    <option value=2>Tittle</option>
                    <option value=3>Description</option>
                </select>
            </th>
            <th width="120">
                <input type="text" title="searchValue" name="searchValue" placeholder="Please input SEARCH value"/>
            </th>
            <th width="120">
                <input type="submit" value="Search">
            </th>
        </tr>
    </table>
</form>

<form action="/bookSortByCriteria" name="Sort By:">
    <table class="tg" name="Sort By:">
        <tr>
            <th width="120">
                <h2>Sort By:</h2>
            </th>
            <th width="120">
                <select name=criteria size=1>
                    <option value=0>ID</option>
                    <option value=1>ID (reversed)</option>
                    <option value=2>Tittle</option>
                    <option value=3>Tittle (reversed)</option>
                    <option value=4>Author</option>
                    <option value=5>Author (reversed)</option>
                    <option value=6>ISBN</option>
                    <option value=7>ISBN (reversed)</option>
                    <option value=8>Likes</option>
                    <option value=9>Likes (reversed)</option>
                    <option value=10>Dislikes</option>
                    <option value=11>Dislikes (reversed)</option>
                    <option value=12 selected>(default)</option>
                </select>
            </th>
            <th width="120">
                <input type="submit" value="Sort">
            </th>
        </tr>
    </table>
</form>

<h1>Add/Edit Book</h1>

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
                <form:label path="descr">
                    <spring:message text="Descr"/>
                </form:label>
            </td>
            <td>
                <form:input path="descr"/>
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
            <td colspan="2">
                <c:if test="${!empty book.bookTitle}">
                    <input type="submit"
                           value="<spring:message text="Edit Book"/>"/>
                </c:if>
                <c:if test="${empty book.bookTitle}">
                    <input type="submit"
                           value="<spring:message text="Add Book"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

<h1>Book List</h1>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="30">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="100">ISBN</th>
            <th width="300">Description</th>
            <th width="80">Image</th>
            <th width="80">Path</th>
            <th width="40">Likes</th>
            <th width="40">Dislikes</th>
            <th width="40">Tags</th>
            <th width="40">Edit</th>
            <th width="40">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/bookdata/${book.id}">${book.bookTitle}</a></td>
                <td>${book.bookAuthor}</td>
                <td>${book.isbn}</td>
                <td>${book.descr}</td>
                <td>
                    <form action="/fileUpload">
                        <input type="hidden" name="id" value=${book.id}>
                        <input type="hidden" name="isimage" value=1>
                        <input type="submit" value="Upload"/>
                    </form>
                    <form action="/fileDownload">
                        <input type="hidden" name="id" value=${book.id}>
                        <input type="hidden" name="isimage" value=1>
                        <input type="submit" value="Download"/>
                    </form>
                </td>
                <td>
                    <form action="/fileUpload">
                        <input type="hidden" name="id" value=${book.id}>
                        <input type="hidden" name="isimage" value=0>
                        <input type="submit" value="Upload"/>
                    </form>
                    <form action="/fileDownload">
                        <input type="hidden" name="id" value=${book.id}>
                        <input type="hidden" name="isimage" value=0>
                        <input type="submit" value="Download"/>
                    </form>
                </td>
                <td>${book.likes}</td>
                <td>${book.dislikes}</td>
                <td>
                    <c:forEach items="${listTags}" var="tag">
                        <c:if test="${tag.bookid == book.id}">
                            ${tag.tagname}
                        </c:if>
                    </c:forEach>
                </td>
                <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<form action="/removeAllBooks">
    <label>REMOVE All Books form library!!! (Be carefully):</label>
    <input type="text" title="removeAllBooks" name="isRemoveAllBooks" placeholder="Type YES/NO (ignore case)"/>
    <input type="submit" value="Remove All"/>
</form>

<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>
</body>
</html>
