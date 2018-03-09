<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patient Register</title>
<meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style.css' />
  <script type="text/javascript" src="javascripts/RegistrationScripts.js"></script>
</head>
<!-- Navbar -->
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/">GP Booking System</a>
    </div>
  </div>
</nav>
<body>
<div class="container pcontainer">
    	<div class="row " style="margin-top:60px">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="patientlogin.jsp"id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="patientregister.jsp" class="active" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
							<form id="registerForm" action="RegistrationServlet" method="post" role="form">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" required>
									</div>
									<div class="form-group">
										<input type="text" name="fname" id="fname" tabindex="1" class="form-control" placeholder="Firstname" value="">
									</div>
									<div class="form-group">
										<input type="text" name="lname" id="lname" tabindex="1" class="form-control" placeholder="Lastname" value="" required>
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="" required>
									</div>
									<div class="form-group">
										<input type="text" name="address" id="address" tabindex="3" class="form-control" placeholder="Address" value="" required>
									</div>
									<div class="form-group">
										<input type="text" name="phone" id="phone" tabindex="3" class="form-control" placeholder="Phone Number" value="" required>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
									</div>
									<div class="form-group">
										<input type="password" name="password2" id="password2" tabindex="2" class="form-control" placeholder="Confirm Password" required>
									</div>
									 <div class="form-group">
				                    <label class="gen">Date of Birth: </label>
				                    <input type="date" name="dob" id="dob" tabindex="2" class="form-control" placeholder="date of Birth">
				                  </div>
									  <label class="gen">Gender:</label>
									 <div class="form-group gen"> 
									       <div class="col-sm-3">
									            <label class="radio-inline">
									                 <input name="gender" id="input-gender-male" value="M" type="radio" required />Male
									             </label>
									        </div>
									        <div class="col-sm-3">
									             <label class="radio-inline">
									                  <input name="gender" id="input-gender-female" value="F" type="radio" />Female
									             </label>
									         </div>
									   </div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												  <p class="status text-center"></p>
												<input type="submit" name="register-submit" id="register" tabindex="4" class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>
