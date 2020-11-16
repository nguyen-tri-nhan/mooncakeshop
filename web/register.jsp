<%-- 
    Document   : register
    Created on : Oct 7, 2020, 12:33:25 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/set2.css" />
    </head>
    <body class="container">
        <c:set var="errors" value="${requestScope.ERROR_USER_ATTRIBUTE}"/>
        <h1>Registation</h1>
        <form action ="register" method="POST">

            <div>
                <span class="input input--fumi">
                    <input class="input__field input__field--fumi" type="text" id="input-24" name="txtEmail" 
                           required="" 
                           value="${param.txtEmail}"/>
                    <label class="input__label input__label--fumi" for="input-24">
                        <i class="fa fa-fw fa-email icon icon--fumi"></i>
                        <span class="input__label-content input__label-content--fumi">Username</span>
                    </label>
                </span>
            </div>
            <div>
                <span class="input input--fumi">
                    <input class="input__field input__field--fumi" type="text" id="input-24" name="txtFirstname" 
                           required=""  minlength="3"
                           value="${param.txtFirstname}"/>
                    <label class="input__label input__label--fumi" for="input-24">
                        <i class="fa fa-fw fa-facebook icon icon--fumi"></i>
                        <span class="input__label-content input__label-content--fumi">First name</span>
                    </label>
                </span>
            </div>
            <div>
                <span class="input input--fumi">
                    <input class="input__field input__field--fumi" type="text" id="input-24" name="txtLastname" 
                           required=""  minlength="3"
                           value="${param.txtLastname}"/>
                    <label class="input__label input__label--fumi" for="input-24">
                        <i class="fa fa-fw fa-male icon icon--fumi"></i>
                        <span class="input__label-content input__label-content--fumi">Last name</span>
                    </label>
                </span>
            </div>
            <div>
                <span class="input input--fumi">
                    <input class="input__field input__field--fumi" type="password" name="txtPassword" required id="input-25" 
                           minlength="8" maxlength="16"
                           value="${param.txtPassword}"/>
                    <label class="input__label input__label--fumi" for="input-25">
                        <i class="fa fa-fw fa-password icon icon--fumi"></i>
                        <span class="input__label-content input__label-content--fumi">Password</span>
                    </label>
                </span>
            </div>
            <div>
                <span class="input input--fumi">
                    <input class="input__field input__field--fumi" type="password" name="txtRepassword" required id="input-25" minlength="8" maxlength="16"
                           value="${param.txtRepassword}"/>
                    <label class="input__label input__label--fumi" for="input-25">
                        <i class="fa fa-fw fa-password icon icon--fumi"></i>
                        <span class="input__label-content input__label-content--fumi">Re-attempt Password</span>
                    </label>
                </span>
            </div>
            <button type="submit" name="btnAction" value="Register">Register</button>

        </form>
        <font color="red">${errors.username}</font> </br>
        <font color="red">${errors.password}</font> </br>
        <p>Already have an account yet? <a href="loginPage">Login</a></p>
    </body>
</html>
