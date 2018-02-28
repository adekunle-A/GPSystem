$(document).ready(function(e) {   
	/**when patient click to Manage Appointment**/
	/* $("#mgapp").on('click',function(e){
		e.preventDefault(); */
		var uid = $('#uid').val();
	    //send the patient information to the server side when the Manage App is clicked and get response
	     $.ajax({
	        url:'ManageAppointments',
	        data:{username:uid},
	        type:'GET',
	        success:function(data){
	            /* alert(data); */
	            $('.getapp').html(data);
	        },
	        error:function(){
	          alert('error');
	        }
	     }); 
 /*   }); */
});