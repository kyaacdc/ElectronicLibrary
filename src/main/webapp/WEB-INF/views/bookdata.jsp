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
<h1>Book Details</h1>

<table class="tg">
  <tr>
    <th width="30">ID</th>
    <th width="100">Title</th>
    <th width="100">Author</th>
    <th width="80">ISBN</th>
    <th width="300">Description</th>
    <th width="120">Image</th>
    <th width="120">Path</th>
    <th width="60">Likes</th>
    <th width="60">Dislikes</th>
  </tr>
  <tr>
    <td>${book.id}</td>
    <td>${book.bookTitle}</td>
    <td>${book.bookAuthor}</td>
    <td>${book.isbn}</td>
    <td>${book.descr}</td>
    <td><img src="${book.image}"/></td>
    <td>
      <form action="/fileDownload">
        <input type="hidden" name="id" value=${book.id}>
        <input type="hidden" name="isimage" value=0>
        <input type="submit" value="Download"/>
      </form>
    </td>
    <td>${book.likes}
      <form action="/changeRate">
        <p>
          <select name=setRate size=1>
            <option value=1 selected>1</option>
            <option value=2>2</option>
            <option value=3>3</option>
            <option value=4>4</option>
            <option value=5>5</option>
          </select>
        </p>
        <input type="hidden" name="id" value=${book.id}>
        <input type="hidden" name="islike" value=1>
        <input type="hidden" name="username" value=${pageContext.request.userPrincipal.name}>
        <p><input type="submit" value="OK"></p>
      </form>
    </td>
    <td>${book.dislikes}
      <form action="/changeRate">
        <p>
          <select name=setRate size=1>
            <option value=1 selected>1</option>
            <option value=2>2</option>
            <option value=3>3</option>
            <option value=4>4</option>
            <option value=5>5</option>
          </select>
        </p>
        <input type="hidden" name="id" value=${book.id}>
        <input type="hidden" name="islike" value=0>
        <input type="hidden" name="username" value=${pageContext.request.userPrincipal.name}>
        <p><input type="submit" value="OK"></p>
      </form>
    </td>
  </tr>
</table>

  <h2>Comments</h2>

<form action="/addComment">
  <label>Add Comment</label>
  <input type="hidden" name="bookId" value=${book.id}>
  <input type="hidden" name="username" value=${pageContext.request.userPrincipal.name}>
  <input type="text" name="descr"/>
  <input type="submit" value="Add"/>
</form>

  <c:if test="${!empty listComments}">
    <table class="tg">
      <tr>
        <th width="40">User</th>
        <th width="2000">Comment</th>
        <th width="60">CommentID</th>
      </tr>
      <c:forEach items="${listComments}" var="comment">
        <tr>
          <td>
            <c:forEach items="${listUsers}" var="user">
              <c:if test="${comment.userId == user.id}">
                ${user.username}
              </c:if>
            </c:forEach>
          </td>
          <td>${comment.description}</td>
          <td>${comment.id}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>

  <br><br><br><br><br><br>
  <h6>@ Designed by Yuriy Kozheurov</h6>

</body>
</html>