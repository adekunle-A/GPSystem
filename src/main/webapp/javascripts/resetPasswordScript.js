$(document).ready(function() { 
	//when search doctor button is clicked
 $('#resetpassbtn').on( "click", function(e) {
	 e.preventDefault();
     var username = $("#username").val();
     var password = $("#password").val();
     console.log(username);
     var role = $('#role').find(":selected").text();
     $.ajax({
         url:'ResetPassword',
         data:{username:username,role:role,password:password},
         type:'POST',
         success:function(data){ 
        	// alert(data);
         	  $('.status').html(data);
         },error:function(){
           alert('error');
         }
      });
 });
});