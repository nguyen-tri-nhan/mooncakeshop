<%-- 
    Document   : user
    Created on : Oct 14, 2020, 9:35:35 PM
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
        <a href="index">Back to shopping</a>
        <table border="1">
            <thead>
                <tr>
                    <th>OrderDate</th>
                    <th>Full name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="track" items="${requestScope.TRACK}">
                <form action="trackDetail">
                    <tr>
                        <td>${track.orderdate}</td>
                        <td>${track.fullname}</td>
                        <td>${track.phone}</td>
                        <td>${track.address}</td>
                        <td>${track.type}</td>
                        <td>
                            <input type="hidden" name="txtID" value="${track.id}"/>
                            <input type="submit" value="View"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
