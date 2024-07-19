<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add review</title>
    </head>
    <body>
        <form action="http://localhost:8080/reviews/create" method="post">
            <input type="text" name="bookId" placeholder="Book ID"><br><br>
            <input type="text" name="reviewText" placeholder="Text of the review"><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>

    <br><hr>
    <a href="/">Go home</a>
</html>
