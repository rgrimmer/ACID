<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/home.css" />

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
                if (ev.target.id == "draggableContainer") {
                    ev.target.appendChild(document.getElementById(data));
                } else if (ev.target.id.substring(0, 13) == "draggableItem") {
                    $(event.target).parent().append(document.getElementById(data));
                } else {
                    alert(ev.target.id);
                }

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
                    <div id="draggableContainer" class="panel-body" ondrop="drop(event)"
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
                    <div id="draggableContainer" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)"></div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Done</h3>
                    </div>
                    <div id="draggableContainer" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)"></div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Done Done</h3>
                    </div>
                    <div id="draggableContainer" class="panel-body" ondrop="drop(event)"
                         ondragover="allowDrop(event)"></div>
                </div>
            </div>

        </div>
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
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>