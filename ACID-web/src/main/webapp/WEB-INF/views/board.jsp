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
                    addToDb(ev.target.id, data);
                } else if (ev.target.id.substring(0, 13) === "draggableItem") {
                    $(event.target).parent().append(document.getElementById(data));
                    addToDb($(event.target).parent().attr('id'), data);
                }
            }

            function addToDb(idDiv, dataDiv) {
                post("${pageContext.request.contextPath}/board", {id:idDiv, data:document.getElementById(dataDiv).textContent});
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
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">ACID</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <div class="navbar-form navbar-right">
                        <a id="logoutBtn" class="btn btn-danger" href="logout.html">Logout</a>
                    </div>
                </div>
            </div>
        </nav>

        <div class="row">
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">To do</h3>
                    </div>
                    <div id="draggableContainerTodo" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)">
                        <div id="draggableItem1" class="panel-body draggable"
                             draggable="true" ondragstart="drag(event)">
                            Create jsp page. <span class="glyphicon glyphicon-pencil"></span>
                        </div>
                        <div id="draggableItem2" class="panel-body draggable"
                             draggable="true" ondragstart="drag(event)">
                            Create base classes (Owner Developper, Developper, Card, Boards,
                            projects) <span class="glyphicon glyphicon-pencil"></span>
                        </div>
                        <div id="draggableItem3" class="panel-body draggable"
                             draggable="true" ondragstart="drag(event)">
                            Create Database (diagram is already draw) <span
                                class="glyphicon glyphicon-pencil"></span>
                        </div>
                        <div id="draggableItem4" class="panel-body draggable"
                             draggable="true" ondragstart="drag(event)">
                            Test4 <span class="glyphicon glyphicon-pencil"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Doing</h3>
                    </div>
                    <div id="draggableContainerDoing" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)"></div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Done</h3>
                    </div>
                    <div id="draggableContainerDone" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)"></div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Done Done</h3>
                    </div>
                    <div id="draggableContainerDoneDone" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)"></div>
                </div>
            </div>

        </div>

    <c:if test="${not empty infoMsg}">
        <div class="alert alert-danger"><strong>${infoMsg}</strong></div>
    </c:if>
    
    <!--
    <div class="row">
            <div class="col-sm-3">
                    <form>
                            <span class="placeholder"> Add a list...</span> <input type="text"
                                    class="list-name-input" name="name" placeholder="Add a list..."
                                    autocomplete="off">
                            <div class="test">
                                    <input type="submit" class="btn btn-primary" value="Save">
                            </div>
                    </form>
            </div>
    </div>
    -->
    <script
    src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>