<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACID | Login</title>
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
                <a href="index.html">
                    <img src="http://terryshoemaker.files.wordpress.com/2013/03/placeholder1.jpg" class="img-rounded">
                </a>
            </div>

            <form class="form-signin" role="form">
                <h2 class="form-signin-heading">Login</h2>

                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>

                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>

                <button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

                <a id="registerBtn" href="register.jsp" class="btn btn-lg btn-info btn-block">Create an account</a>
            </form>
        </div>
    </body>
</html>
