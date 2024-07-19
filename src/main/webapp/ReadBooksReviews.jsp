<%@ page import="org.example.dto.DTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.dto.impl.BookReviewDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read book</title>
</head>
<body>
<%
    String result = (String) request.getAttribute("result");
%>
<pre><%= result %></pre>
</body>
</html>
