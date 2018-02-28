<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Dashboard</title>
  <meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
</head>
<body>
<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("adminlogin.jsp");
  }
%>
<script>
$(document).ready(function() {   
	$('#addDoctorForm').on('submit', function(e) {
		e.preventDefault();
    var username = $("#username").val();
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var email = $("#email").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    var password = $("#password").val();
    var confirmpassword = $("#password2").val();
    alert(confirmpassword);
    var role =  $("#role option:selected").val()
    var dateofbirth = $("#dateofbirth").val();
    var gender = $('input[name=gender]:checked').val(); 	
    $.ajax({
        url:'AddDoctor',
        data:{username:username,fname:fname,lname:lname,email:email,address:address,phone:phone,password:password,confirmpassword:confirmpassword,dateofbirth:dateofbirth,role:role,gender:gender},
        type:'POST',
        success:function(data){ 
        	alert(data);
        		$(".status").html(data);
        },error:function(){
          alert('error');
        }
     });
    });
}); 
</script>
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
        <li class="active"><a href="adminDashboard.jsp">Add Doctor</a></li>
        <li><a href="removeDoctor.jsp">Remove Doctor</a></li>
        <li><a href="viewDoctor.jsp">View Doctors</a></li>
        <li><a href="resetPassword.jsp">Reset Password</a></li>
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
      <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6 text-left">
                <a href="adminDashboard" class="active" id="register-form-link">Add New Doctor</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
              <form action="AddDoctor" method="post" role="form"  id="addDoctorForm">
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
                        <input type="date" name="dob" id="dateofbirth" tabindex="2" class="form-control" placeholder="date of Birth">
                      </div>
                  <div class="form-group ">
                    <label class="rememberme">Medic Role:</label>
                    <select class="form-control" name="role" id="role">
                        <option value="doctor"> Doctor </option>
                    </select>
                  </div>
<!--                 <div class="form-group "> -->
<!--                   <label class="rememberme">Select Category:</label> -->
<!--                   <select class="form-control" name="docCategory"> -->
 <!--                      <option value="Allegist"> Allegist</option>
                      <option value="Audiologist"> Audiologist</option>
                      <option value="Dentist"> Dentist</option>
                      <option value="Dermatologist"> Dermatologist</option>
                      <option value="Epidmiologist"> Epidmiologist</option>
                      <option value="Cardiologist"> Cardiologist</option>
                      <option value="Infectectious Disease"> Infectectious Disease</option>
                      <option value="Microbiologist"> Microbiologist</option>
                      <option value="Othopedics"> Othopedics</option>
                      <option value="ENT Specialist">ENT Specialist</option>
                  </select> 
                </div> -->
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
                        <input type="submit" name="register-submit" id="registersubmit" tabindex="4" class="form-control btn btn-register" value="Add Doctor">
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