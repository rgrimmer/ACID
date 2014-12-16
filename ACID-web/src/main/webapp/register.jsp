<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACID | Register</title>
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
                    <img src="http://terryshoemaker.files.wordpress.com/2013/03/placeholder1.jpg" class="img-rounded">
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
                
                <a id="loginLink" href="login.jsp" class="btn btn-lg btn-info btn-block">Login</a>
            </form>
        </div>
    </body>
</html>
