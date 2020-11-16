<%-- 
    Document   : userdetail
    Created on : Oct 14, 2020, 9:36:53 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.USER.lastname} ${sessionScope.USER.firstname}</title>
        <c:if test="${sessionScope.USER == null}">
            <c:redirect url="index"/>
        </c:if>
    </head>
    <body>
        <a href="track">Back</a>
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="trackdetail" items="${requestScope.TRACK_DETAILS}">
                    <tr>
                        <td>${trackdetail.cakename}</td>
                        <td>${trackdetail.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
