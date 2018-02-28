package servletpkg;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class Cancel Patients Appointments
 * This is the class implementation  that enables patient to cancel appointments 
 */
public class CancelAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Twillo Account Sid and Token 
	public static final String ACCOUNT_SID = "AC0a88ef937d3a0f60ce6ef2f815442d7e";
    public static final String AUTH_TOKEN = "1191a945ab34038eb385121f46e40207";
    public static final String sendernumber = "441422400778";
	Connection connection;
    PreparedStatement pst,pst2,pst3,pst4;
    ResultSet rs,rs2,rs3;
    PrintWriter out;

    // use to store the messages that will be displayed on the client side
    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("ManageAppointment.jsp");
		doPost(request, response);
	}//end doGet
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request the user id and the appointment id;
		String userid = request.getParameter("username");
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String query =   "SELECT USERID,PHONE,FNAME FROM USERS WHERE USERNAME= ?";
		String query2 = "SELECT APPOINTMENTID,APPS_APPID FROM APPOINTMENTS WHERE APPOINTMENTDATE= ? AND STARTTIME= ? AND ENDTIME= ? "
						+ "AND DOCTORNAME= ? AND PATIENTS_PATIENTID= ?";
		String query3 = "DELETE FROM APPOINTMENTS WHERE APPOINTMENTID= ?";
		String query4 = "UPDATE DOCTORSAVAILABILITY SET STATUS = ? WHERE ADDAPPOINTMENTID = ?";
		 response.setContentType("text/html");
	     out = response.getWriter();
	     System.out.println(date);
	     try{
	    		//connect to DB 
			 connection = DBConnectionManager.getConnection();
	    	 	 pst = connection.prepareStatement(query);
			 pst.setString(1, userid);
	         rs = pst.executeQuery();
			 if(rs.next() && !userid.equals("")){
				 pst2 = connection.prepareStatement(query2);
				 pst2.setString(1, date);
				 pst2.setString(2, start);
				 pst2.setString(3, end);
				 pst2.setString(4, name);
				 pst2.setInt(5, rs.getInt(1));
				 rs2 = pst2.executeQuery();
				 //gets the appointmentid and id appointment id to update and delete the row
				 if(rs2.next()){
					 System.out.println("Appointmentid" + rs2.getInt(1));
					 System.out.println("AddavailbilityID" + rs2.getString(2) );
					 //deletes the row with the matching id
					 pst3 = connection.prepareStatement(query3);
					 pst3.setInt(1, rs2.getInt(1));
					 pst3.executeUpdate();
					 /*This update the slot availability status in the doctors availability table*/
					 pst4 = connection.prepareStatement(query4);
					 pst4.setInt(1, 1);
					 pst4.setInt(2, rs2.getInt(2));
					 pst4.executeUpdate();
					 pst4.executeUpdate();
					 out.write("Appointment Cancelled Successful");
					 cancelappointmentconfirmation(rs.getString(2), " Dear " +rs.getString(3) + " your appointment "
					 		+ "with Dr. " + name + " on " + date + " at " + start + " has been cancelled successfully.");
				 }
			}else{
				out.write("Unable to perform Operation");
			}
	     }catch(SQLException e){
	    	e.printStackTrace();
	     }
	}//end  void doPost
	//sends cancellation notification 
     public void cancelappointmentconfirmation(String pnumber, String msg) {
			   //initialises twilo using the account id and auth token
			   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			    Message message = Message.creator(new PhoneNumber(pnumber),
			        new PhoneNumber(sendernumber), 
			        msg).create();
			    System.out.println(message.getSid());
   }
}//end class CancelAvailability
