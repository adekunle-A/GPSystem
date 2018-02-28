 /**when check check availability and book app button is clicked**/
$(function() {
	$('#listable').hide();
	$("#checkavail").on('click',function(e){
		e.preventDefault();
	    var bookingid = $("#bookid").val();
	    var datepicker = $("#datetimepicker").val();
    $.ajax({
        url:'bookAppointment',
        data:{bookingid:bookingid,datepicker:datepicker},
        type:'POST',
        success:function(data){  
       		//alert(data);
           if (!$.trim(data)){ 
	    	   alert("No Available Appointment for: " +datepicker );
	       }else{
	    	   $('#listable').show();
           	 $('.bkapptable').html(data);
           	 $(".bkapptable").find(".bookapp").on('click',function(e){
        	    e.preventDefault();
        	    var id =  $(this).parents('tr').find('.id').text();
        	    var name =  $(this).parents('tr').find('.name').text();
        	    var date =  $(this).parents('tr').find('.date').text();
        	    var start =  $(this).parents('tr').find('.start').text();
        	    var end =  $(this).parents('tr').find('.end').text();
        	    var bookingid = $("#bookid").val();
        	    $(this).html('Booked <i class="fa fa-calendar-check-o"></i>');
        	    console.log("gggh");
        	    //send the patient information to the server side when the book button is clicked and get response
        	    $.ajax({
                    url:'getSelectedAppointment',
                    data:{bookingid:bookingid,appid:id,name:name,date:date,start:start,end:end},
                    type:'POST',
                    success:function(data){ 
                    	if(!data){
                    		$('.bookapp').attr("disabled", true);
                    	}else{
                    		$('.bookapp').html('Book <i class="fa fa-calendar-check-o"></i>');
                    		alert(data);
                    	}  
                    },
                    error:function(){
                    	 $('.bookapp').html('Book <i class="fa fa-calendar-check-o"></i>');
                      alert('error');
                    }
                 });
        	    });
	       }
        },
        error:function(){
          alert('error');
        }
     });
	});
	
});