<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/board.css" />

        <script>
            function allowDrop(ev) {
                ev.preventDefault();
            }

            function drag(ev) {
                ev.dataTransfer.setData("text", ev.target.id);
            }

            function drop(ev) {
                ev.preventDefault();

                var data = ev.dataTransfer.getData("text");
                if (ev.target.id.substring(0, 18) === "draggableContainer") {
                    ev.target.appendChild(document.getElementById(data));
                    addToDb(document.getElementById(data).id.substring(13), ev.target.id.substring(18));
                } else if (ev.target.id.substring(0, 13) === "draggableItem") {
                    $(event.target).parent().append(document.getElementById(data));
                    addToDb(document.getElementById(data).id.substring(13), $(event.target).parent().attr('id').substring(18));
                }
            }

            function addToDb(idTask, idList) {
                post("${pageContext.request.contextPath}/board?idBoard=${idBoard}", {idBoard:${idBoard}, idT: idTask, idL: idList});
                    }

                    function post(path, params, method) {
                        method = method || "post"; // Set method to post by default if not specified.

                        // The rest of this code assumes you are not using a library.
                        // It can be made less wordy if you use one.
                        var form = document.createElement("form");
                        form.setAttribute("method", method);
                        form.setAttribute("action", path);

                        for (var key in params) {
                            if (params.hasOwnProperty(key)) {
                                var hiddenField = document.createElement("input");
                                hiddenField.setAttribute("type", "hidden");
                                hiddenField.setAttribute("name", key);
                                hiddenField.setAttribute("value", params[key]);

                                form.appendChild(hiddenField);
                            }
                        }

                        document.body.appendChild(form);
                        form.submit();
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
            <strong><span id="boardName">${boardName}</span></strong>
        </div>

        <div class="row">
            ${lists}
        </div>

        <!--
        <c:if test="${not empty infoMsg}">
            <div class="alert alert-danger"><strong>${infoMsg}</strong></div>
        </c:if>
        -->
    </body>
</html>