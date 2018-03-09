<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Help</title>
<meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
  <script type="text/javascript" src="javascripts/adminHelpScript.js"></script>
</head>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="adminDashboard.jsp">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="adminDashboard.jsp">Add Doctor</a></li>
        <li><a href="removeDoctor.jsp">Remove Doctor</a></li>
        <li><a href="viewDoctor.jsp">View Doctors</a></li>
        <li><a href="resetPassword.jsp">Reset Password</a></li>
        <li class="active"><a href="adminhelp.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<body>
<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("adminlogin.jsp");
  }
%>
<!-- <script>
$(document).ready(function() {   
       	 //when send message is clicked
       	 $("#btnReplyUs").on('click',function(e){ 
       		e.preventDefault();
       	    //send the patient information to the server side when the book button is clicked and get response
         	var message = $('#message').val();
         	var subject = $('#subject').find(":selected").val();
         	var name =$('#name').val();
         	var email =$('#email').val();
       		 $.ajax({
       		        url:'replyMessage',
       		        data:{email:email,name:name,message:message,subject:subject},
       		        type:'POST',
       		        success:function(data){
       		        	 alert(data);
       		        	location.reload(true);
       		        },
       		        error:function(){
       		          alert('error');
       		        }
       		   }); 
       	 }); 
});
</script> -->
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3">
                <a href="patienthelp.jsp" class="active" id="login-form-link">Send Email </a>
              </div>
            </div>
            <hr>
          </div>
          <input type="hidden" name="userid " id="uid" tabindex="1" class="form-control" value="" required>
          <div class="panel-body">
              <div class="col-lg-15 gen">
                <form id="patienthelp-form" action="replyMessage" method="post" role="form">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="name">
                                Name</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter name" required="required" value="" required/>
                        </div>
                        <div class="form-group">
                            <label for="email">
                                Email Address</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                <input type="email" class="form-control" id="email" placeholder="Enter email" required="required" value="" required/>
                             </div>
                        </div>
                        <div class="form-group">
                            <label for="subject">
                                Subject</label>
                            <select id="subject" name="subject" class="form-control" required="required">
                                <option value="na" selected="">Choose One:</option>
                                <option value="service">General Customer Enquiry</option>
                                <option value="suggestions">Suggestions</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="name">
                                Message</label>
                            <textarea name="message" id="message" class="form-control" rows="9" cols="25" required="required"
                                placeholder="Message"></textarea>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary pull-right" id="btnReplyUs">
                            Send Message</button>
                    </div>
                </div>
                </form>
               </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>
