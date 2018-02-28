$(document).ready(function(e) {   
/**when patient click to view profile**/
/*  $("#getprofile").on('click',function(e){ 
	e.preventDefault(); */
	var uid = $('#uid').val();
    //send the patient information to the server side when the book button is clicked and get response
     $.ajax({
        url:'DocProfileInfomation',
        data:{username:uid},
        type:'POST',
        success:function(data){
        	 $('.getdocprofilerow').html(data);
        },
        error:function(){
          alert('error');
        }
     }); 
   //}); 
});