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
        <div class="container">

            <div class="row text-center">
                <a href="${pageContext.request.contextPath}">
                    <img src="${pageContext.request.contextPath}/resources/imgs/logo.png" class="img-rounded">
                </a>
            </div>

                <form class="form-newProject" role="form" method="POST" action="${pageContext.request.contextPath}/newProject">
                <h2 class="form-newProject-heading">New Project</h2>
                <label for="inputProjectName" class="sr-only">Project name</label>
                <input id="inputProjectName" name="inputProjectName" type="projectname" class="form-control" placeholder="Project name" required autofocus>
                
                <button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
                
                <c:if test="${not empty errorMsg}">
                    <div id="errorBlock" class="alert alert-danger"><strong>${errorMsg}</strong></div>
                </c:if>
            </form>
        </div>
    </body>
</html>
