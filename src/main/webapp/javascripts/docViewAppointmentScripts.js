$(document).ready(function(e) {   
	 var appData = [];
	/**when Doctor click to View Appointments**/
 		var uid = $('#uid').val();
	    //send the Doctors information(id) to the server side when the VEIW APPOINTMENT is clicked and get response
	     $.ajax({
	        url:'DisplayDocAppointment',
	        data:{docid:uid},
	        type:'GET',
	        success:function(data){
	            $('.docappointments').html(data);
	            $("#infoTable").find('tr').each(function () {
	                var $tds = $(this).find('td'),
	                appointmentID = $tds.eq(0).text(),
	                appointmentDate = $tds.eq(1).text(),
	                startTime = $tds.eq(2).text();
	                endTime = $tds.eq(3).text();
	                patient = $tds.eq(5).text();
	                /*appointmentDate breakdown*/
	                var splitappdate = appointmentDate.split('/');
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
	                /*putting the information together as a json object*/
	                var jsondata = {
	              	content: patient,
	              	 endDate: new Date(year, month-1, day, endfpart,endsecpart),
	  	   		     startDate: new Date(year, month-1, day, sfpart,ssecpart),
	                }
	                appData.push(jsondata);
	        
	            });
	            YUI().use(
	      	   		  'aui-scheduler',
	      	   		  function(Y) {
	      	   			//events to be added to the calender which is the information gotten from the database 
	      	   		    var events = appData;
	      	   		    //calender views
	      	   		    var weekView = new Y.SchedulerWeekView({days:5, isoTime:true,duration:{value:30}});
	           		    var dayView = new Y.SchedulerDayView({isoTime:true,duration:{value:30}});
	      	   		    var eventRecorder = new Y.SchedulerEventRecorder();
	      	   		   //adding everything to the calender and displaying 
	      	   		    new Y.Scheduler(
	      	   		      {
	      	   		        activeView: weekView,
	      	   		        boundingBox: '#upappointments',
	      	   		        date: new Date(Date.now()),
	      	   		        //eventRecorder: eventRecorder, 
	      	   		        firstDayOfWeek: 1,
	      	   		        items: events,
	      	   		        render: true,
	      	   		        views: [dayView, weekView]
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