<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Availability</title>
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
    <script src="http://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="http://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css" rel="stylesheet"></link>
<script type="text/javascript" src="javascripts/updateavailscripts.js"></script>
</head>
<body>
<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("doctorlogin.jsp");
  }
%>

<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-5">
                <a href="UpdateAvailability.jsp" class="active" id="login-form-link">Update Availability</a>
              </div>
            </div>
            <hr>
            <input type="hidden" name="userid " id="uid" tabindex="1" class="form-control" value="<%= session.getAttribute("username") %>" readonly>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
              <div id="updateavailabilities"></div>
              <div class="hidden">
         			<table class="table gen" id="infoTable">
                       <tbody class="docupdateavailability">
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
</body>
</html>
