<%@ page import="org.example.dto.DTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read review</title>
</head>
<body>
<form action="http://localhost:8080/reviews/read" method="post">
    <input type="text" name="reviewId" placeholder="Review ID"><br><br>
    <input type="submit" value="Submit">
</form>

<%
    String result = (String) request.getAttribute("result");

%>

<pre>
<%= result %>
</pre>

<br><hr>
<a href="/">Go home</a>

</body>
</html>
