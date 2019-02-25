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
<link rel="stylesheet"
	href="http://oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css">
<script
	src="http://oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
<style type="text/css">
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
</style>
</head>
<body class="bg">
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	 
	<!--  <h3 style="color: white;">Enter your details</h3>  -->
	 
	<!-- <div class="col-lg-12">
		      
		<form:form method="POST" id="registration" action="userSignUp.html"
			modelAttribute="user">
           
		<div class="form-group">
				<form:input type="text" path="firstName" name="firstName"
					class="form-control" placeholder="First Name" value=""
					required="required" />
			</div>
			<div class="form-group">
				<form:input type="text" path="lastName" name="lastName"
					class="form-control" placeholder="Last Name" value=""
					required="required" />
			</div>
			<div class="form-group">
				<form:input type="password" path="password" name="password"
					class="form-control" placeholder="Password" required="required" />
			</div>
			<div class="form-group">
				<input type="password" name="confirmPassword" class="form-control"
					placeholder="Confirm Password" required="required" />
			</div>
			<div class="form-group">
				<form:input type="email" path="email" class="form-control"
					name="email" placeholder="Email ID" required="required" />
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3">
						<input type="submit" name="register-submit" id="register-submit"
							tabindex="4" class="form-control btn btn-success"
							value="Register Now" />
					</div>
				</div>
			</div>
        </form:form>
	</div> -->
	<div class="container">
		<div class="row centered-form">
			<div
				class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							Signup for Recipe Generator. <small>It's free!</small>
						</h3>
					</div>
					<div class="panel-body">
						<form:form method="POST" id="registration"
							action="userSignUp.html" modelAttribute="user">
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<form:input type="text" path="firstName" name="firstName"
											class="form-control" placeholder="First Name" value=""
											required="required" />
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<form:input type="text" path="lastName" name="lastName"
											class="form-control" placeholder="Last Name" value=""
											required="required" />
									</div>
								</div>
							</div>

							<div class="form-group">
								<form:input type="email" path="email" class="form-control"
									name="email" placeholder="Email ID" required="required" />
							</div>

							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<form:input type="password" path="password" name="password"
											class="form-control" placeholder="Password"
											required="required" />
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<input type="password" name="confirmPassword"
											class="form-control" placeholder="Confirm Password"
											required="required" />
									</div>
								</div>
							</div>

							<input type="submit" value="Register"
								class="btn btn-info btn-block">
							<input type="button" value="Back" onclick="javascript:location.href='login.html';"
								class="btn btn-info btn-block">
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(document)
			.ready(
					function() {
						$('#registration')
								.bootstrapValidator(
										{
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												password : {
													validators : {
														identical : {
															field : 'confirmPassword',
															message : 'The password and its confirm are not the same'
														}
													}
												},
												confirmPassword : {
													validators : {
														identical : {
															field : 'password',
															message : 'The password and its confirm are not the same'
														}
													}
												}
											}
										});
					});
</script>
</html>