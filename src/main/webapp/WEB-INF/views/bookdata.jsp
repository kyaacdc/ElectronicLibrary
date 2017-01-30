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
<a href="/welcome">Back to main menu</a>
<h1>Book Details</h1>

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
  <tr>
    <td>${book.id}</td>
    <td>${book.bookTitle}</td>
    <td>${book.bookAuthor}</td>
    <td>${book.isbn}</td>
    <td>${book.descr}</td>
    <td>${book.image}</td>
    <td>${book.path}</td>
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
        <p><input type="submit" value="OK"></p>
      </form>
    </td>
  </tr>
  <br><br><br><br><br><br>
  <h6>@ Designed by Yuriy Kozheurov</h6>
</table>
</body>
</html>