<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>ACID - New Project</title>
        <!-- Scripts -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
        <!-- Styles -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap-theme.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/newProject.css" />
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed"
                            data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                            aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">ACID</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <div class="navbar-form navbar-right">
                        <a id="logoutBtn" class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </div>
            </div>
        </nav>
                    
        <div class="container">

            <div class="row text-center">
                <a href="${pageContext.request.contextPath}">
                    <img id="logo" src="${pageContext.request.contextPath}/resources/imgs/logo.png" class="img-rounded">
                </a>
            </div>

            <form class="form-newProject" role="form" method="POST" action="${pageContext.request.contextPath}/newProject">
                <h2 class="form-newProject-heading">New Project</h2>
                <label for="inputProjectName" class="sr-only">Project name</label>
                <input id="inputProjectName" name="inputProjectName" class="form-control" placeholder="Project name" required autofocus>

                <button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Create</button>

                <c:if test="${not empty errorMsg}">
                    <div id="errorBlock" class="alert alert-danger"><strong>${errorMsg}</strong></div>
                </c:if>
            </form>
        </div>
    </body>
</html>
