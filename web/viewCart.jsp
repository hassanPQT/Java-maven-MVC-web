<%-- 
    Document   : viewCart
    Created on : Jul 6, 2024, 10:32:09 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your cart include</h1>
        <!-- lay cart-->
        <c:set var="cart" value="${sessionScope.CART}"/>
        <!--lay items-->
        <c:if test = "${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="removeItemFromCartAction">
                    <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                    <tr>
                        <td>
                            ${counter.count}
                        </td>
                        <td>
                            ${entry.key}
                        </td>
                        <td>
                            ${entry.value}
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="${entry.key}"/>
                        </td>
                        
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="shoppingPage">Add more book to your cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction" />
                        </td>
                    </tr>
                </form>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty cart.items}">
            <h2>No cart exist</h2>
        </c:if>
            <form action="checkOutPage">
                <input type="submit" value="checkOut" />
            </form>
    </body>
</html>
