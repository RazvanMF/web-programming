<%--
  Created by IntelliJ IDEA.
  User: cosad
  Date: 6/12/2024
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a page!</title>
</head>
<body>
<form action="JournalServlet" method="post">
    <input type="text" name="journalName" placeholder="Journal name">
    <input type="text" name="articleSummary" placeholder="Article summary">
    <input type="submit" value="Insert!">
</form>
</body>
</html>
