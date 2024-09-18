<%-- 
    Document   : checkOut
    Created on : Jul 6, 2024, 9:58:15 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
    </head>
    <body>
          <h1>Purchase Information</h1>
        <form action="paymentAction">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart.items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                            <tr>
                                <th>
                                    ${counter.count}.
                                </th>
                                <th>
                                    ${entry.key}
                                </th>
                                <th>
                                    ${entry.value}
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            Name <input type="text" name="txtCustomerName" value="${param.txtCustomerName}" /><br/>
            Address* <input type="text" name="txtAddress" value="" /><br/>
            Email* <input type="text" name="txtEmail" value="" /><br/>
            <input type="submit" value="Purchase" />
        </form>
    </body>
</html>
