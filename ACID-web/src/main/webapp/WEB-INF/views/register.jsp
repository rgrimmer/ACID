<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>ACID - Register</title>
        <!-- Scripts -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
        <!-- Styles -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap-theme.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/register.css" />
    </head>
    <body>
        <div class="container">

            <div class="row text-center">
                <a href="${pageContext.request.contextPath}">
                    <img src="${pageContext.request.contextPath}/resources/imgs/logo.png" class="img-rounded">
                </a>
            </div>

            <form class="form-register" role="form" method="POST" action="${pageContext.request.contextPath}/register">
                <h2 class="form-register-heading">Register</h2>
                <label for="inputUsername" class="sr-only">Username</label>
                <input id="inputUsername" name="inputUsername" type="username" class="form-control" placeholder="Username" value="${inputUsername}" required autofocus>

                <label for="inputEmail" class="sr-only">Email address</label>
                <input id="inputEmail" name="inputEmail" type="email" class="form-control" placeholder="Email address" value="${inputEmail}" required>

                <label for="inputPassword" class="sr-only">Password</label>
                <input id="inputPassword" name="inputPassword" type="password" class="form-control" placeholder="Password" required>

                <label for="inputPasswordConfirmation" class="sr-only">Password (confirmation)</label>
                <input id="inputPasswordConfirmation" name="inputPasswordConfirmation" type="password" class="form-control" placeholder="Password (confirmation)" required>

                <button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Register</button>

                <a id="loginLink" href="${pageContext.request.contextPath}/login" class="btn btn-lg btn-info btn-block">Login</a>

                <c:if test="${not empty errorMsg}">
                    <div id="errorBlock" class="alert alert-danger"><strong>${errorMsg}</strong></div>
                </c:if>
            </form>
        </div>
    </body>
</html>
