<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset Password</title>       
<meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
  <script type="text/javascript" src="javascripts/resetPasswordScript.js"></script>
</head>
<body>
<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("adminlogin.jsp");
  }
%>
<!-- <script>
$(document).ready(function() { 
	//when search doctor button is clicked
 $('#resetpassbtn').on( "click", function(e) {
	 e.preventDefault();
     var username = $("#username").val();
     var password = $("#password").val();
     console.log(username);
     var role = $('#role').find(":selected").text();
     $.ajax({
         url:'ResetPassword',
         data:{username:username,role:role,password:password},
         type:'POST',
         success:function(data){ 
        	 alert(data);
         	  //$('.status').html(data);
         },error:function(){
           alert('error');
         }
      });
 });
});
</script> -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="adminDashboard">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li ><a href="adminDashboard.jsp">Add Doctor</a></li>
        <li><a href="removeDoctor.jsp">Remove Doctor</a></li>
        <li><a href="viewDoctor.jsp">View Doctors</a></li>
        <li class="active"><a href="resetPassword.jsp">Reset Password</a></li>
        <li><a href="adminhelp.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6 text-left">
                <a href="viewDoctor" class="active" id="register-form-link">User Details</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
              <form id="register-form" action="ResetPassword" method="post" role="form">
                  <div class="form-group">
                   <label class="rememberme">Username:</label>
                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder=" username" value="" required>
                  </div>
                   <div class="form-group ">
	                  <label class="rememberme">Role:</label>
	                  <select class="form-control" name="role" id="role">
	                        <option value="doctor">Doctor </option>
	                        <option value="patient">patient </option>
	                  </select>
                  </div>
                     <div class="form-group">
                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                          <p class="status text-center"></p>
                        <input type="submit" name="register-submit" id="resetpassbtn" tabindex="4" class="form-control btn btn-register" value="Reset Password">
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