<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>	Diagonise Patient</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
         <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
           <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tokenfield/0.12.0/bootstrap-tokenfield.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tokenfield/0.12.0/bootstrap-tokenfield.min.js"></script>
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tokenfield/0.12.0/css/bootstrap-tokenfield.css" />
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tokenfield/0.12.0/css/bootstrap-tokenfield.min.css" />
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tokenfield/0.12.0/css/tokenfield-typeahead.css" />
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tokenfield/0.12.0/css/tokenfield-typeahead.min.css" />
       <link rel='stylesheet' href='stylesheets/style2.css' />
       <script type="text/javascript" src="javascripts/scripts.js"></script>
        <script type="text/javascript" src="javascripts/diagnosePatientScript.js"></script>

</head>
<body>
<%

  if(session.getAttribute("username") == null){
	  response.sendRedirect("doctorlogin.jsp");
  }
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="addDocAvailability.jsp">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
      <li><a href="doctorsDashboard.jsp">Profile</a></li>
        <li><a href="addDocAvailability.jsp">Add Availability</a></li>
        <li><a href="ViewAppointment.jsp">View Appointments</a></li>
        <li><a href="checkPatientHistory.jsp">checkPatientHistory</a></li>
        <li><a href="UpdateAvailability.jsp">Update Availability</a></li>
        <li><a href="addDiagnosisInfo.jsp">Add DiagnosisInfo</a></li>
        <li class="active"><a href="diagnosePatient.jsp">Diagnose Patient</a></li>
        <li><a href="updatePrescription.jsp">update Prescription</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="addDocAvailability.jsp">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="doctorsDashboard.jsp">Profile</a></li>
        <li><a href="addDocAvailability.jsp">Add Availability</a></li>
        <li><a href="ViewAppointment.jsp">View Appointments</a></li>
        <li><a href="checkPatientHistory.jsp">checkPatientHistory</a></li>
        <li  class="active"><a href="UpdateAvailability.jsp">Update Availability</a></li>
        <li><a href="addDiagnosisInfo.jsp">Add DiagnosisInfo</a></li>
        <li><a href="diagnosePatient.jsp">Diagnose Patient</a></li>
         <li><a href="updatePrescription.jsp">update Prescription</a></li>
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
                <a href="diagnosePatient.jsp" class="active" id="register-form-link">Diagnose Patient<a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body gen">
            <div class="row">
             <input type="hidden" name="docid " id="docid" tabindex="1" class="form-control"  value="<%= session.getAttribute("username") %>"required>
              <div class="col-lg-12">
              <form id="register-form" action="diagnosePatient" method="post" role="form">
             <div class="input-group">
               <div class="input-group-btn search-panel"> 
		            <select class="form-control" id="searchby">
		                <option value="symptoms">Symptoms</option>
		                <option value="diseases">Diseases</option>
		            </select>
		              <div  class="btn btn-default dropdown-toggle">
                    	<span id="concept">Filter by</span> <span class="caret"></span>
                    </div>
                 </div>     
                 <div class="form-group"> 
                	<input type="text" class="form-control" id="getinfo" name="search" placeholder="Search" required>
                </div>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id="searchbtn"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            	   </div>
                </form>
                <p class="msg"></p>
                   <table class="table gen" id="listable">
                      <thead class="thead-inverse">
                        <tr>
                          <th>Disease</th>
                          <th>Symptoms</th>
                          <th>Location</th>
                          <th>Description</th>
                        </tr>
                      </thead>
                       <tbody class="displa">
                       </tbody>
                  </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div>
</body>
</html>
