<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                <a href="index.html">
                    <img src="${pageContext.request.contextPath}/resources/imgs/logo.png" class="img-rounded">
                </a>
            </div>

            <form class="form-register" role="form">
                <h2 class="form-register-heading">Register</h2>
                <label for="inputEmail" class="sr-only">Username</label>
                <input id="inputUsername" type="username" class="form-control" placeholder="Username" required autofocus>

                <label for="inputEmail" class="sr-only">Email address</label>
                <input id="inputEmail" type="email" class="form-control" placeholder="Email address" required>

                <label for="inputPassword" class="sr-only">Password</label>
                <input id="inputPassword" type="password" class="form-control" placeholder="Password" required>

                <label for="inputPasswordConfirmation" class="sr-only">Password (confirmation)</label>
                <input id="inputPasswordConfirmation" type="password" class="form-control" placeholder="Password (confirmation)" required>

                <button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Register</button>

                <a id="loginLink" href="${pageContext.request.contextPath}/login" class="btn btn-lg btn-info btn-block">Login</a>
            </form>
        </div>
    </body>
</html>
