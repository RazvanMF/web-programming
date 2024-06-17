<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>EDIT ACCOUNT</title>
</head>
<body>
<h1>EDIT AN ACCOUNT</h1>
<div>
    <form action="EditUserServlet" method="post">
        <input type="hidden" name="id" placeholder="ID:" value="${param.id}"/>
        <input type="text" name="name" placeholder="Name:" />
        <input type="text" name="email" placeholder="Email:" />
        <input type="text" name="picture" placeholder="Picture:" />
        <input type="text" name="age" placeholder="Age:" />
        <input type="hidden" name="userid" placeholder="UserID:" value="${param.userid}" />
        <input type="submit" value="ADD!" />
    </form>
</div>
</body>
</html>