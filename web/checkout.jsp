<%-- 
    Document   : checkout
    Created on : Oct 11, 2020, 10:34:40 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <div>
        </div>
        <c:if test="${empty sessionScope.CART}">
            <font color="red"><p>Your cart is empty</p></font>
            <a href="index">Back to shopping</a>
        </c:if>
        <c:if test="${not empty sessionScope.CART}">

        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
            <c:set scope="session" var="total" value="${0}"/>
            <c:set scope="session" var="sum" value="${0}"/>
            <c:forEach var="dto" items="${sessionScope.CART.getCart().values()}" varStatus="counter">
                <form action="remove">
                    <tr>
                    <c:set scope="session" var="money" value=""/>
                    <td>${counter.count}</td>
                    <td>${dto.name}</td>
                    <td>${dto.quantity}</td>
                    <td>${dto.price}</td>
                    <td>${dto.quantity * dto.price}</td>
                    </tr>
                    <c:set scope="session" var="total" value="${total + dto.quantity * dto.price}"/>
                </form>
            </c:forEach>
                <tr>
                    <td>Total</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><fmt:formatNumber type="number" groupingUsed="false" value="${total}"/></td>
                </tr>
            </tbody>
        </table>
        <c:if test="${not empty sessionScope.USER}">
            <div>
                <form action="order" method="POST">
                    username:<input type="text" name="txtUserName" value="${sessionScope.USER.email}" disabled=""/></br>
                    fullname:<input type="text" name="txtFullName" value="${sessionScope.USER.firstname} ${sessionScope.USER.lastname}"/></br>
                    address: <input type="text" name="txtAddress"/></br>
                    phone: <input type="tel" name="txtPhone" pattern="[0-9]{10,11}" placeholder="0123456789"/></br>
                    cash type:
                    <select name="txtType">
                        <option value="1">Cash on delivery</option>
                    </select>
                    <input type="submit" value="Check out"/>
                    <button onclick="location.href='index'">Back to shopping</button>
                </form>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.USER}">
            <div>
                <form action="order" method="POST">
                    username:<input type="text" name="txtUserName" value="guest" disabled=""/></br>
                    fullname:<input type="text" name="txtFullName" minlength="3" maxlength="20" required=""/></br>
                    address: <input type="text" name="txtAddress" minlength="5" maxlength="40" required=""/></br>
                    phone: <input type="tel" name="txtPhone" pattern="[0-9]{10,11}" placeholder="0123456789" required=""/></br>
                    cash type:
                    <select name="txtType">
                        <option value="1">Cash on delivery</option>
                    </select>
                    <input type="submit" value="Check out"/>
                    <button onclick="location.href='index'">Back to shopping</button>
                </form>
            </div>
        </c:if>
        </c:if>
    </body>
</html>
