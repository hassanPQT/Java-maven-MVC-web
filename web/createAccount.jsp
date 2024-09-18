<%-- 
    Document   : createAccount
    Created on : Jul 5, 2024, 8:23:11 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register</h1>
         <form action="registerAction" method="POST">
             <c:set var = "error" value ="${requestScope.CREATE_ERROR}"/> 
            USERNAME* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6-12) characters<br/>
            <c:if test="${not empty error.usernameLengthErr}">
                <font color ="red">
                ${error.usernameLengthErr}
                </font><br/>
            </c:if>
            PASSWORD* <input type="password" name="txtPassword" value="" /> (6-20)characters <br/>
            <c:if test="${not empty error.passwordLengthErr}">
                <font color ="red">
                ${error.passwordLengthErr}
                </font><br/>
            </c:if>
            CONFIRM*  <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty error.confirmNotMatched}">
                <font color ="red">
                ${error.confirmNotMatched}
                </font><br/>
            </c:if>
            FULL NAME* <input type="text" name="txtFullName" value="" /> (2-50)characters <br/>
            <c:if test="${not empty error.fullNameLengthErr}">
                <font color ="red">
                ${error.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Registeration" name="registerAction" />
            <input type="reset" value="Reset" />
        </form>
         <c:if test="${not empty error.duplicatedUsername}">
                <font color ="red">
                ${error.duplicatedUsername}
                </font><br/>
            </c:if>
    </body>
</html>
