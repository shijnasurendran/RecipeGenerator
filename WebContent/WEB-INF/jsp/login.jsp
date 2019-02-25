<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
body, html {
	height: 100%;
	margin: 0;
}

.bg {
	/* The image used */
	background-image:
		url("http://evgeni.mk/wp-content/uploads/2015/07/awesome-fruit-and-vegetable-backgrounds.jpg");
	/* Full height */
	height: 100%;
	/* Center and scale the image nicely */
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}

.panel-default {
	opacity: 0.9;
	margin-top: 30px;
}

.form-group.last {
	margin-bottom: 0px;
}
</style>
<body class="bg">
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-lock"></span> Login <small
							style='color: red;'>     <%
							if (request.getAttribute("msg") != null) {
						%> ${msg } <%
 	}
 %>
						</small>
					</div>
					<div class="panel-body">
						<form:form method="POST" action="userLogin.html"
							modelAttribute="user" class="form-signin">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-3 control-label">
									Username</label>
								<div class="col-sm-9">
									<form:input path="lastName" type="text" class="form-control"
										name="Username" placeholder="Username" required="required"
										autofocus="" />
								</div>
							</div>
							<br />
							<br />
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-3 control-label">
									Password</label>
								<div class="col-sm-9">
									<form:input path="password" type="password"
										class="form-control" name="Password" placeholder="Password"
										required="required" />
								</div>
							</div>
							<br />
							<br />
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										Sign in</button>
									<button type="reset" class="btn btn-default btn-sm">
										Reset</button>
								</div>
							</div>
						</form:form>
					</div>
					<div class="panel-footer">
						Not Registred? <a href="registration.html">Register here</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>