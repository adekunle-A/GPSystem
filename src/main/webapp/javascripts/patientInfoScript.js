$(document).ready(function(e) {   
/**when patient click to view profile**/
	//e.preventDefault(); 
	var uid = $('#uid').val();
    //send the patient information to the server side when the book button is clicked and get response
     $.ajax({
        url:'patientDashboard',
        data:{username:uid},
        type:'POST',
        success:function(data){
        	 $('.getprofilerow').html(data);
        },
        error:function(){
          alert('error');
        }
     }); 
   //}); 
});