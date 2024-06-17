<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ADD ACCOUNT</title>
</head>
<body>
    <h1>ADD AN ACCOUNT</h1>
    <div>
        <form action="UserServlet" method="post">
            <input type="text" name="name" placeholder="Name:" />
            <input type="text" name="email" placeholder="Email:" />
            <input type="text" name="picture" placeholder="Picture:" />
            <input type="text" name="age" placeholder="Age:" />
            <input type="submit" value="ADD!" />
        </form>
    </div>
</body>
</html>
