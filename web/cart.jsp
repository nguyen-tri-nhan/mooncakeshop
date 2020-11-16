<%-- 
    Document   : cart
    Created on : Oct 11, 2020, 12:19:56 AM
    Author     : Lenovo
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Cart</h1>
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
                        <!--<th></th>-->
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
                            <td>
                                <input type="hidden" value="${dto.id}" name="txtID"/>
                                <input type="submit" onclick="return confirm('Do you want to remove?')" value="Remove"/>
                            </td>
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
        <a href="index">Back to shopping</a>

        <button onclick="location.href = 'checkout'" type="button">Check out</button>
        <button onclick="location.href = 'cancel'" type="button">Cancel</button>
    </c:if>
</body>
</html>
