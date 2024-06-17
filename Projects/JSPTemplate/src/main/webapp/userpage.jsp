<%--
  Created by IntelliJ IDEA.
  User: Razvan
  Date: 13/06/2024
  Time: 15:14
  To change this template use File | Settings | File Templates.

  ALSO, CHECK OUT https://www.tutorialspoint.com/jsp/jsp_expression_language.htm
--%>
<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div> <b>${sessionScope.username}</b>, you've successfully penetrated our defenses.</div>
    <div>Who would've thought you would guess that <b>${sessionScope.password}</b> was our password. :(</div>
</body>
</html>
