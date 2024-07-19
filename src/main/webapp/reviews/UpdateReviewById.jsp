<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update review</title>
</head>
<body>
<form action="http://localhost:8080/reviews/update" method="post">
  <input type="text" name = "reviewId" placeholder="Review ID"><br><br>
  <input type="text" name="bookId" placeholder="New book Id"><br><br>
  <input type="text" name="reviewText" placeholder="New review text"><br><br>
  <input type="submit" value="Submit">
</form>
<br><hr>
<a href="/">Go home</a>
</body>
</html>
