<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>ACID - Login</title>
        <!-- Scripts -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
        <!-- Styles -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap-theme.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/login.css" />
    </head>
    <body>
        <div class="container">

            <div class="row text-center">
                <a href="${pageContext.request.contextPath}">
                    <img src="${pageContext.request.contextPath}/resources/imgs/logo.png" class="img-rounded">
                </a>
            </div>

            <form class="form-signin" role="form" method="POST" action="${pageContext.request.contextPath}/login">
                <h2 class="form-signin-heading">Login</h2>

                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" value="${inputEmail}" required autofocus>

                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" value="${inputPassword}" required>

                <button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

                <a id="registerBtn" href="${pageContext.request.contextPath}/register" class="btn btn-lg btn-info btn-block">Create an account</a>
                
                <c:if test="${not empty errorMsg}">
                    <div id="errorBlock" class="alert alert-danger"><strong>${errorMsg}</strong></div>
                </c:if>
            </form>
        </div>
    </body>
</html>
