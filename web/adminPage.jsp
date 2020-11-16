<%-- 
    Document   : adminPage
    Created on : Oct 13, 2020, 7:08:50 AM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="index"/>
        </c:if>
        <c:if test="${sessionScope.USER.roleid != -1}">
            <c:redirect url="index"/>
        </c:if>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    </head>
    <body>
        <header>
            <p>
                Hello 
                <a href="log">${sessionScope.USER.firstname} ${sessionScope.USER.lastname}</a>
                <a href="logout">Logout</a>
            </p>
        </header>
        <div>
            <form action="adminSearch" method="POST">
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
            <h2>Create Cake</h2>
            <form action="create" method="POST" enctype="multipart/form-data">
                <input type="text" placeholder="name" name="txtCakeName" required=""/></br>
                <textarea name="txtDescription"
                          maxlength="1000"
                          placeholder="Description"
                          cols="100"
                          rows="3" required=""></textarea></br></br>
                <input type="date" required="" name="txtCreateDate"/>
                <input type="date" required="" name="txtExpirationDate"/>
                <input type="number" required="" placeholder="quantity" name="txtQuantity"/>
                <select name="txtCategory">
                    <c:forEach var="categ" items="${requestScope.CATEGORY}">
                        <option value="${categ.id}">${categ.name}</option>
                    </c:forEach>
                </select>
                <input type="text" required="" placeholder="price" name="txtCakePrice" pattern="^[-+]?\d*\.?\d*$"/></br>
                <select name="chkVisible">
                    <option value="true">Visible</option>
                    <option value="false">Invisible</option>
                </select>
                <input type="file" name="fileIMG" accept="image/*" required=""></br>
                <input type="submit" value="Create"/>
            </form>
        </div>
        <div>
            <c:if test="${empty requestScope.LIST_CAKE_ADM}">
                <font color="red"><h2>Your result is empty</h2></font>
                <a href="adminPage">Try again</a>
            </c:if>
            <c:if test="${not empty requestScope.LIST_CAKE_ADM}">
                <h2>Your Cake</h2>
            </c:if>
            <c:forEach var="cake" items="${requestScope.LIST_CAKE_ADM}">
                <font color="red"><p>${requestScope.ERROR_DATE}</p></font>
                <form action="update" method="POST" enctype="multipart/form-data">
                    <input type="hidden" value="${cake.id}" name="txtCakeid"/></br>
                    <input type="text" value="${cake.name}" name="txtCakeName"/></br>
                    <input type="text" value="${cake.descripton}" name="txtDescription"/></br>
                    <input type="date" value="${cake.createDate}" name="txtCreateDate"/></br>
                    <input type="date" value="${cake.expirationDate}" name="txtExpirationDate"/></br>
                    <input type="number" value="${cake.quantity}" name="txtQuantity"/></br>
                    <select name="txtCategory">
                        <c:forEach var="categ" items="${requestScope.CATEGORY}">
                            <option value="${categ.id}">${categ.name}</option>
                        </c:forEach>
                    </select></br>
                    <input type="text" value="${cake.price}" name="txtCakePrice" pattern="^[-+]?\d*\.?\d*$"/></br>
                    <select name="chkVisible">
                        <option value="true">Visible</option>
                        <option value="false">Invisible</option>
                    </select>
                    <img src="${cake.img}"/></br>
                    <input type="file" name="fileIMG" accept="image/*" required=""></br>
                    <input type="submit" onclick="return confirm('Do you want to update?')" value="Update"/>
                    <p></p>
                </form>
            </c:forEach>
        </div>
        <nav>
            <ul class="pagination justify-content-center">

                <c:forEach var="count" begin="1" end="${requestScope.PAGE}">
                    <c:url var="page" value="adminPage">
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
