<%-- 
    Document   : login
    Created on : Jul 4, 2024, 9:57:06 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="loginAction" method="POST">
            Username: <input type="text" name="txtUsername" value="" /><br/>
            Password: <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <a href="shoppingPage">click here to buy book</a><br/>
        <a href="register">click here to Register</a>
    </body>
</html>
