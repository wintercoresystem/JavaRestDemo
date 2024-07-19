<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete review</title>
</head>
<body>
<form action="http://localhost:8080/reviews/delete" method="post">
    <input type="text" name="reviewId" placeholder="Review ID"><br><br>
    <input type="submit" value="Submit">
</form>
<br><hr>
<a href="/">Go home</a>
</body>
</html>
