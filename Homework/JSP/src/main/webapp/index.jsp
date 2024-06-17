<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>NSA-Flavoured KEYSTROKE Over JSP/Servlets</title>
</head>
<body>
    <h1>NSA-Flavoured KEYSTROKE Over JSP/Servlets</h1>
    <div>
        <div>
            <p>LOGIN HERE</p>
        </div>
        <form action="LoginServlet" method="post">
            <input type="text" name="username" placeholder="Username:" />
            <input type="password" name="password" placeholder="Password:" />
            <input type="submit" value="ENTER THE MAINFRAME" />
        </form>
        <div>
            <p>DON'T HAVE AN ACCOUNT? REGISTER ONE NOW!</p>
        </div>
        <form action="RegisterServlet" method="post">
            <input type="text" name="regusername" placeholder="Username:" />
            <input type="password" name="regpassword" placeholder="Password:" />
            <input type="submit" value="LOAD DATA INTO MAINFRAME" />
        </form>
    </div>
</body>
</html>