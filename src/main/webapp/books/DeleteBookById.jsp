<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>
<form action="http://localhost:8080/books/delete" method="post">
    <input type="text" name="bookId" placeholder="Book ID to delete"><br><br>
    <input type="submit" value="Submit">
</form>
</body>

<br><hr>
<a href="/">Go home</a>
</html>
