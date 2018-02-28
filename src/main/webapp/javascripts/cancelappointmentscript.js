$(document).ready(function(e) { 
	$('.getapp').on('click', '.appcancelbutton', function(e) {
		var uid = $('#uid').val();
   	    var name =  $(this).parents('tr').find('.name').text();
   	    var date =  $(this).parents('tr').find('.date').text();
   	    var start =  $(this).parents('tr').find('.start').text();
   	    var end =  $(this).parents('tr').find('.end').text(); 
   	  console.log(uid);
   	   $(this).html('Cancelled <i class="fa fa-calendar-check-o"></i>');
	   $.ajax({
	        url:'CancelAppointment',
	        data:{username:uid,name:name,date:date,start:start,end:end},
	        type:'POST',
	        success:function(data){
	        	 $('.cancelapp').attr("disabled", true);
	        	 location.reload(true);
	        	// window.location.href= "/ManageAppointments"; 
	        },
	        error:function(){
	          $('.cancelapp').html('Cancel <i class="fa fa-calendar-check-o"></i>');
	          alert('error');
	        }
	     }); 
	});
});