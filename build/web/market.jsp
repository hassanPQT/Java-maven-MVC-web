<%-- 
    Document   : market
    Created on : Jul 5, 2024, 10:36:46 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Shopping Cart</h1>
        <form action="CartServlet">
            Choose Book <select name="ddlbook">
                <option>jpsbooks</option>
                <option>naruto</option>
                <option>onepiece</option>
                <option>C++</option>
                <option>JAVA</option>
                <option>OOP</option>
                <option>JSP</option>
            </select><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction"  />
            <input type="submit" value="View cart" name="btAction" />
        </form>
    </body>
</html>
