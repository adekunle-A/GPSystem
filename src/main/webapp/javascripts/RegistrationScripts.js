 $(document).ready(function() { 
	$('#registerForm').on('submit', function(e) {
    	e.preventDefault();
    var username = $("#username").val();
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var email = $("#email").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    var password = $("#password").val();
    var confirmpassword = $("#password2").val();
    var dob = $("#dob").val();
    var gender = $('input[name=gender]:checked').val();
    $.ajax({
        url:'RegistrationServlet',
        data:{username:username,fname:fname,lname:lname,email:email,address:address,phone:phone,password:password,confirmpassword:confirmpassword,dob:dob,gender:gender},
        type:'POST',
        success:function(data){ 
        		$(".status").html(data);
        },error:function(){
          alert('error');
        }
     });
    });
}); 