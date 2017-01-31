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

<h1>Book List</h1>

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
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/bookdata/${book.id}" target="_blank">${book.bookTitle}</a></td>
                <td>${book.bookAuthor}</td>
                <td>${book.isbn}</td>
                <td>${book.descr}</td>
                <td>${book.image}</td>
                <td>${book.path}</td>
                <td>${book.likes}</td>
                <td>${book.dislikes}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br><br><br><br><br><br>
<h6>@ Designed by Yuriy Kozheurov</h6>
</body>
</html>

