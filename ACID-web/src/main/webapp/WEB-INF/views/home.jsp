<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/styles/home.css" rel="stylesheet" type="text/css"
	title="Default Style">
<script src="jquery-2.1.3.min.js"></script>
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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">ACIID</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<div class="navbar-form navbar-right">
                                        <p class="navbar-brand">${sessionScope.user.name}</p>
					<a id="logoutBtn" class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="board-title">
		TRUC <span class="glyphicon glyphicon-folder-open"></span>
	</div>

	<div class="row">
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">TRUC : Common</h3>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">TRUC : Sprint 1</h3>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">TRUC : Sprint 2</h3>
				</div>
			</div>
		</div>
	</div>

	<hr>

	<div class="board-title">
		ACID <span class="glyphicon glyphicon-folder-open"></span>
	</div>

	<div class="row">
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">ACID : Common</h3>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">ACID : Sprint 1</h3>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">ACID : Sprint 2</h3>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="panel board">
				<div class="panel-heading">
					<h3 class="panel-title">ACID : Sprint 3</h3>
				</div>
			</div>
		</div>

	</div>

	<hr>

	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>