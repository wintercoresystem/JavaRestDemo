<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update book</title>
</head>
<body>
<form action="http://localhost:8080/books/update" method="post">
  <input type="text" name = "bookId" placeholder="Book ID"><br><br>
  <input type="text" name="bookTitle" placeholder="New BookTitle"><br><br>
  <input type="text" name="description" placeholder="New Description"><br><br>
  <input type="submit" value="Submit">
</form>

<br><hr>
<a href="/">Go home</a>
</body>
</html>
