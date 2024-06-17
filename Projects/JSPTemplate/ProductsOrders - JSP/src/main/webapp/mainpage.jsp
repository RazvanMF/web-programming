<%--
  Created by IntelliJ IDEA.
  User: i9
  Date: 12.06.2024
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <a href="addpage.jsp"><button>Add product</button></a>
    <form method="post" action="${pageContext.request.contextPath}/ProductServlet">
        <input type="text" id="FilterName"  name="FilterName" placeholder="Filter products"/>
        <input hidden="hidden" name="choice" value="filter">
        <input type="submit" value="Filter">
    </form>
</body>
</html>
