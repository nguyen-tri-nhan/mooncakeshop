<%-- 
    Document   : index
    Created on : Oct 6, 2020, 10:36:41 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <c:if test="${sessionScope.USER.roleid == -1}">
            <c:redirect url="adminPage"/>
        </c:if>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    </head>
    <body>
        <header>
            <c:if test="${sessionScope.USER != null}">
                <p>
                    Hello 
                    <c:if test="${sessionScope.USER.roleid != -1}">
                        <a href="cartPage">View Cart</a>
                    </c:if>
                    <a href="track">${sessionScope.USER.firstname} ${sessionScope.USER.lastname}</a>
                    <a href="logout">Logout</a>
                </p>
            </c:if>
            <c:if test="${sessionScope.USER == null}">
                <a href="loginPage">Login</a>
                <a href="cartPage">View Cart</a>
            </c:if>
        </header>
        <div>
            <div>
                <form action="userSearch" method="POST">
                    <input type="text" placeholder="Search" name="txtName"/>
                    <input type="text" value="0" name="txtMinPrice" pattern="^[-+]?\d*\.?\d*$"/>
                    <input type="text" value="1000000" name="txtMaxPrice" pattern="^[-+]?\d*\.?\d*$"/>
                    <select name="txtCategory">
                        <option value="0">---</option>
                        <c:forEach var="categ" items="${requestScope.CATEGORY}">
                            <option value="${categ.id}">${categ.name}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Search"/>
                </form>
                <c:if test="${empty requestScope.LIST_CAKE_ADM}">
                    <font color="red"><h2>Your result is empty</h2></font>
                    <a href="adminPage">Try again</a>
                </c:if>
                <c:forEach var="cake" items="${requestScope.LIST_CAKE}">
                    <c:if test="${sessionScope.USER.roleid != -1}">
                        <form action="addcart" method="POST">
                            <input type="hidden" value="${cake.id}" name="txtCakeid"/></br>
                            <details>
                                <summary><h2>${cake.name}</h2></summary>
                                <p>${cake.descripton}</p>
                            </details>
                            <style>
                                details summary::-webkit-details-marker {
                                    display:none;
                                }
                            </style>
                            <img src="${cake.img}"/></br>
                            <input type="number" name="txtNumber" value="1" max="${cake.quantity}"/>
                            <input type="submit" value="${cake.price} VND"/>
                        </form>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <nav>
            <ul class="pagination justify-content-center">

                <c:forEach var="count" begin="1" end="${requestScope.PAGE}">
                    <c:url var="page" value="index">
                        <c:param name="pagenum" value="${count}"></c:param>
                    </c:url>
                    <li class="page-item">
                        <a class="page-link" href="${page}">${count}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </body>
</html>
