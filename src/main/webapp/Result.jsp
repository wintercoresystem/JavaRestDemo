<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<%
    String serviceMessage = (String) request.getAttribute("serviceMessage");
%>

<p><strong>Service Message:</strong> <%= serviceMessage %></p>

<a href="/">Go home</a>
</body>
</html>
