<%--
  Created by IntelliJ IDEA.
  User: akor
  Date: 1/22/2021
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ServletAuth</title>
</head>
<body>
<h1>Auth User</h1>
    <p>
        <%
        java.util.Date now = new java.util.Date();
        String someOne = "Date: " + now;
        out.print(someOne);
        %>
    </p>
</body>
</html>
