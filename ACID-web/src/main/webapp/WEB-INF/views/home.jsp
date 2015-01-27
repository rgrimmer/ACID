<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/styles/home.css" rel="stylesheet" type="text/css"
              title="Default Style">
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

        <div class="board-title">
            ${projets}
        </div>
        <c:if test="${not empty errorMsg}">
            <div id="errorBlock" class="alert alert-danger"><strong>${errorMsg}</strong></div>
        </c:if>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>