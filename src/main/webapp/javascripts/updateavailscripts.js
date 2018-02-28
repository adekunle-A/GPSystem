$(document).ready(function(e) {   
	 var appData = [];
	  /**when patient click  Update Appointment page**/
		var uid = $('#uid').val();
	    //send the doctors information to the server side when the update availability is clicked and get response
	     $.ajax({
	        url:'DisplayDocAvailability',
	        data:{username:uid},
	        type:'GET',
	        success:function(data){
	            // alert(data); 
	            $('.docupdateavailability').html(data);
	             $("#infoTable").find('tr').each(function () {
	                var $tds = $(this).find('td');
	                var availabilityID = $tds.eq(0).text();
	                var availabilityDate = $tds.eq(1).text();
	                var startTime = $tds.eq(2).text();
	                var endTime = $tds.eq(3).text();
	                var docname = $tds.eq(4).text();
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
	              	  content: "Available",
	              	  endDate: new Date(year, month-1, day, endfpart,endsecpart),
	  	   		      startDate: new Date(year, month-1, day, sfpart,ssecpart),
	                }
	                appData.push(jsondata);
	            }); 
	             YUI().use(
	           		  'aui-scheduler',
	           		  function(Y) {
	           		    var events = appData;
	           		    /*This handles events when the calender is clicked */
	           		    var eventRecorder = new Y.SchedulerEventRecorder({duration:30,reminder:true,content: "Available",
	           		     on: {
	           		    	/*Save events*/
	           		    save: function(event) {
	           		    	console.log(this.isNew());
	           		    	$.ajax({
	           	                url:'UpdateDocAvailability',
	           	                data:{status:"Free",username:uid,startDate: eventRecorder.get('startDate'),endDate:eventRecorder.get('endDate')},
	           	                type:'POST',
	           	                success:function(data){
	           	                   alert(data); 
	           	               },
	           	                error:function(){
	           	                  alert('error');
	           	                }
	           	             }
	           	        	);
	           		      },
	           		     /*delete events*/
	           		      delete: function(event) {
	           		     	console.log(">>>" + this.getFormattedDate());
	           		 	console.log(">>>end" + this.get('endDate'));
			           	   	$.ajax({
	           	                url:'DeleteDocAvailability',
	           	                data:{status:"Free",username:uid,startDate: eventRecorder.get('startDate'),getDate: this.getFormattedDate(),endDate:eventRecorder.get('endDate')},
	           	                type:'POST',
	           	                success:function(data){
	           	                	console.log(event);
	           	                   alert(data); 
	           	               },
	           	                error:function(){
	           	                  alert('error');
	           	                }
	           	             }
	           	        	);
	           		      }     
	           		    }
	           		  });
	           		    var weekView = new Y.SchedulerWeekView({days:5, isoTime:true});
	           		    var dayView = new Y.SchedulerDayView({isoTime:true});
	           		    console.log(dayView);
	           		    new Y.Scheduler(
	           		      {  
	           		    	  	activeView: weekView,
	           		        boundingBox: '#updateavailabilities',
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