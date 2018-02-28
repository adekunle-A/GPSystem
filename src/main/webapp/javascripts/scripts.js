$(function() {
	/**when calender icon is click **/
 $( ".glyphicon-calendar" ).click(function() {
     $(".timeslot").hide();
     $('.form_datetime').datetimepicker({
   	 format: "DD/MM/YYYY",
   	 daysOfWeekDisabled: [0, 6]});
     $(".timeslot").show();
  });
/**when remove icon is click **/
 $( ".glyphicon-remove" ).click(function() {
     	$(".timeslot").hide();
 		$('#datetimepicker').val("");
 });
	/**when patient click to view profile**/
	$("#gprofile").on('click',function(e){
		alert("data");
	});
});