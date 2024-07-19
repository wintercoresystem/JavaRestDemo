<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add book</title>
    </head>
    <body>
        <form action="http://localhost:8080/books/create" method="post">
            <input type="text" name="bookTitle" placeholder="BookTitle"><br><br>
            <input type="text" name="description" placeholder="Description"><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>

    <br><hr>
    <a href="/">Go home</a>
</html>
