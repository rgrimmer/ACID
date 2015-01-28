<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/styles/card.css" rel="stylesheet" type="text/css"
              title="Default Style">
        <link href="${pageContext.request.contextPath}/resources/styles/sweet-alert.css" rel="stylesheet" type="text/css"
              title="Default Style">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/sweet-alert.min.js"></script>
        <script>
            function showConfirmationBox(ev) {
                swal({
                    title: "Are you sure ?",
                    text: "You will not be able to recover this task!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes",
                    cancelButtonText: "No",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        swal("Deleted!", "Your task has been deleted.", "success");
                    } else {
                        swal("Cancelled", "Operation canceled.", "error");
                    }
                });
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed"
                            data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                            aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">ACID</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <div class="navbar-form navbar-right">
                        <a id="logoutBtn" class="btn btn-danger" href="logout.html">Logout</a>
                    </div>
                </div>
            </div>
        </nav>

        <div class="col-sm-3">
            ${task}
        </div>

        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>