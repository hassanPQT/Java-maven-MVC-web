
<%-- 
    Document   : search
    Created on : Jul 4, 2024, 10:04:09 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color = "red">
        Welcome, ${sessionScope.USER.fullname}
        </font>
        <h1>Search Page</h1>
        <form action="searchAction">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search" />
        </form>
        <form action="logoutAction">
            <input type="submit" value="Logout" name="Logout" />
        </form><br/>
        <c:set var="SearchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty SearchValue }">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountAction">

                            <tr>
                                <td>${counter.count}.</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                    <c:if test="${not empty requestScope.ERROR.passwordIsEmptyError
                                                  and requestScope.ERROR_PK == dto.username }"><br/>
                                          <font color="red">
                                          ${requestScope.ERROR.passwordIsEmptyError}
                                          </font>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtFullname" value="${dto.fullname}" />
                                     <c:if test="${not empty requestScope.ERROR.fullnameIsEmptyError
                                                  and requestScope.ERROR_PK == dto.username }"><br/>
                                          <font color="red">
                                          ${requestScope.ERROR.fullnameIsEmptyError}
                                          </font>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="DeleteAccountServlet">
                                        <c:param name="pk" value="${dto.username}"></c:param>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"></c:param>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update"/>
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}"/>
                                </td>
                            </tr>
                        </form> 
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color="red">
                No record matched!!!
                </font>
            </h2>
        </c:if>
    </c:if>
</body>
</html>
