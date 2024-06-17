<%--
  Created by IntelliJ IDEA.
  User: i9
  Date: 12.06.2024
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
    <form method="post" action="ProductServlet">
        <input type="text" name="name" placeholder="Name" required/>
        <input type="text" name="description" placeholder="Description" required/>
        <input hidden="hidden" name="choice" value="add">
        <input type="submit" value="Submit">
    </form>
    <a href="mainpage.jsp"><button>Go back</button></a>
</body>
</html>
