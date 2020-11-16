<%-- 
    Document   : logpage
    Created on : Oct 14, 2020, 6:58:16 AM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log</title>
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="index"/>
        </c:if>
        <c:if test="${sessionScope.USER.roleid != -1}">
            <c:redirect url="index"/>
        </c:if>
    </head>
    <body>
        <div>
            <a href="adminPage">Back</a>
            <table border="1">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Do</th>
                        <th>Action</th>
                        <th>Date</th>
                    </tr>
                </thead>


                <c:forEach var="log" items="${requestScope.LOG}">
                    <tbody>
                        <tr>
                            <td>${log.username}</td>
                            <td>${log.act}</td>
                            <td>${log.description}</td>
                            <td>${log.actionDate}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
