$(document).ready(function(e) {   
	/**when patient click to Manage Appointment**/
		//e.preventDefault(); 
		var username = $('#username').val();
		console.log(username);
	    //send the patient information to the server side when the Manage App is clicked and get response
	     $.ajax({
	        url:'viewPrescription',
	        data:{username:username},
	        type:'POST',
	        success:function(data){
	            $('.prescriptiontable').html(data);
	        },
	        error:function(){
	          alert('error');
	        }
	     }); 
});