<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Appointment</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Appointment</title>
  <meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
    <script src="http://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="http://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css" rel="stylesheet"></link>
<!-- <script type="text/javascript" src="javascripts/displaydocavailabilitiesScripts.js"></script> -->
</head>
<body>

<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("patientlogin.jsp");
  }
%>
<script>
$(document).ready(function(e) { 
	var appData = [];
    var bookingid = $("#bookid").val();
	$.ajax({
        url:'bookAppointment',
        data:{bookingid:bookingid},
        type:'POST',
        success:function(data){
        	$('.bkapptable').html(data);
            $("#listable").find('tr').each(function () {
            	  		var $tds = $(this).find('td');
	                var availabilityID = $tds.eq(0).text();
	                var availabilityDate = $tds.eq(2).text();
	                var startTime = $tds.eq(3).text();
	                var endTime = $tds.eq(4).text();
	                var docname = $tds.eq(1).text();
	                /*availabilityDate breakdown*/
	                var splitappdate = availabilityDate.split('/');
	                var year = splitappdate[2];
	                var month = splitappdate[1];
	                var day = splitappdate[0]; 
	                /*Start time breakdown*/
	                var splitappstarttime = startTime.split(':');
	                var sfpart = splitappstarttime[0];
	                var ssecpart = splitappstarttime[1]; 
	                /*end time breakdown*/
	                var splitappendtime = endTime.split(':');
	                var endfpart = splitappendtime[0];
	                var endsecpart = splitappendtime[1]; 
	                /*putting the information to getter as a json object*/
	                var jsondata = {
	              	  content: docname,
	              	  endDate: new Date(year, month-1, day, endfpart,endsecpart),
	  	   		      startDate: new Date(year, month-1, day, sfpart,ssecpart),
	                }
	                appData.push(jsondata);
	            }); 
            YUI().use(
   				 'aui-scheduler',
   				  function(Y) {
   					var events = appData;
   				    var eventRecorder = new Y.SchedulerEventRecorder({
   				    	duration:30,reminder:true,content: ""
   				    });
   				    /* var eventRecorder = new Y.SchedulerEventRecorder({duration:30,reminder:true,content: "Available"}); */
   				    var weekView = new Y.SchedulerWeekView({days:5, isoTime:true});
   				    var dayView = new Y.SchedulerDayView({isoTime:true});
   	
   				    new Y.Scheduler(
   				      {
   				    	    activeView: weekView,
   				        boundingBox: '#displaydocavailabilities',
   				        date: new Date(Date.now()),
   				        eventRecorder: eventRecorder, 
   	       		        firstDayOfWeek: 1,
   	       		        items: events,
   	       		        render: true,
   	       		        views: [dayView,weekView]
   				      }
   				    );
   				  }
   			);
            
	     },
	     error:function(){
	          alert('error');
	     }
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
      <a class="navbar-brand" href="patientDashboard">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="patientDashboard.jsp">Profile</a></li>
        <li><a href="bookAppointment.jsp">Book Appointment</a></li>
        <li><a href="ManageAppointments">Manage Appointment</a></li>
        <li><a href="viewPrescription">View Prescriptions</a></li>
        <li><a href="viewMedicalHistory.jsp">viewMedicalHistory</a></li>
        <li ><a href="patienthelp.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-9 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6">
                <a href="bookAppointment" class="active" id="login-form-link">New Appointment Booking</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                  <div class="form-group">
                  <label class="rememberme">Booking ID:</label>
                  <input type="text" name="BookingID" id="bookid" tabindex="1" class="form-control" placeholder="Booking ID" value="<%= session.getAttribute("username") %>" readonly>
                  </div>
             	<div id="displaydocavailabilities"></div>
             	 <div class="hidden">
         			<table class="table gen" id="listable">
                       <tbody class="bkapptable">
                       </tbody>
                       </table>
                  </div>
               </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>