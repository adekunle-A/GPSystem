//$(document).ready(function(e) {   
///**when patient click to view profile**/
//	var uid = $('#uid').val();
//    //send the patient information to the server side when the book button is clicked and get response
//     $.ajax({
//        url:'viewMedicalHistory',
//        data:{username:uid},
//        type:'POST',
//        success:function(data){
//        	 $('.getmhistoryrow').html(data);
//        },
//        error:function(){
//          alert('error');
//        }
//     }); 
//});