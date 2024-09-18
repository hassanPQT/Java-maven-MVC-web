<%-- 
    Document   : orderSuccess
    Created on : Jul 7, 2024, 9:38:34 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Success</title>
    </head>
    <body>
        <h1>Order Success!!!</h1>

        <h3>Your OrderID: ${sessionScope.ORDER.id}</h3><br/>
        <font>Time: ${sessionScope.ORDER.date}</font><br/>
        <font>Customer name: ${sessionScope.ORDER.customer}</font><br/>
        <font>Email: ${sessionScope.ORDER.email}</font><br/>
        <font>Shipping address: ${sessionScope.ORDER.address}</font><br/>
        <font>Total: ${sessionScope.ORDER.total}</font><br/>

        <h3>Order Detail</h3>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart.items}" varStatus="counter">
                        <tr>
                            <th>
                                ${counter.count}.
                            </th>
                            <th>
                                ${item.key}
                            </th>
                            <th>
                                ${item.value}
                            </th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
</html>
