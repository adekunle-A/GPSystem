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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class getSelectedAppointment
 * This contains the implementation that gets the appointment 
 * that a patient booked and update the database accordingly
 */
@WebServlet("/getSelectedAppointment")
public class getSelectedAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int MYSQL_DUPLICATE_PK = 1062;
	// Twillo Account Sid and Token 
	public static final String ACCOUNT_SID = "AC0a88ef937d3a0f60ce6ef2f815442d7e";
    public static final String AUTH_TOKEN = "1191a945ab34038eb385121f46e40207";
    public static final String sendernumber = "441422400778";
    
	    Connection connection;
	    PreparedStatement pst,pst2,pst3,pst4,pst5;
	    ResultSet rs,rs2,rs3,rs4;
	    PrintWriter out;
	    
	    // use to store the messages that will be displayed on the client side
	    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = request.getParameter("bookingid");
		String docname = request.getParameter("name");
		String appid = request.getParameter("appid");
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String query =  "SELECT USERID,FNAME,LNAME,PHONE FROM USERS WHERE USERNAME= ?";
		String query2 = "SELECT DOCTORS_DOCTORID FROM DOCTORSAVAILABILITY WHERE ADDAPPOINTMENTID= ?";
		//String query3 = "SELECT appointmentid FROM APPOINTMENTS WHERE appointmentdate= ? AND  starttime=? AND doctors_doctorid=?";
		String query3 = "INSERT INTO APPOINTMENTS"
				+ "(APPOINTMENTDATE,STARTTIME,ENDTIME,DOCTORNAME,PATIENTNAME,DOCTORS_DOCTORID,PATIENTS_PATIENTID,APPS_APPID) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		String query4 = "UPDATE DOCTORSAVAILABILITY SET STATUS = ? WHERE ADDAPPOINTMENTID = ?";
		 // Set response content type
	      response.setContentType("text/html");
	     out = response.getWriter();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, bookingid);
			rs = pst.executeQuery();
			System.out.println(appid);
			if (!rs.next()) {
				 messages.put("error", "No Booked Appointment Available");
				 request.setAttribute("messages", messages);
			}else{
				do{
					pst2 = connection.prepareStatement(query2);
					pst2.setString(1,appid);
					rs2 = pst2.executeQuery();
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getString(4));
					
					if(rs2.next()){
						System.out.println(rs2.getString(1));
						pst3 = connection.prepareStatement(query3);
						pst3.setString(1,date);
						pst3.setString(2,start);
						pst3.setString(3,end);
						pst3.setString(4,docname);
						pst3.setString(5,rs.getString(2) + " "+ rs.getString(3));
						pst3.setInt(6,Integer.parseInt(rs2.getString(1)));
						pst3.setInt(7,Integer.parseInt(rs.getString(1)));
						pst3.setInt(8,Integer.parseInt(appid));
						pst3.execute();
							System.out.println("Appointnment added successfully");
							pst4 = connection.prepareStatement(query4);
							pst4.setInt(1, 0);
							pst4.setInt(2,Integer.parseInt(appid));
							// execute update SQL statement
							pst4.executeUpdate();
							System.out.println("doctors availability updated successfull");
							bookappointmentconfirmation(rs.getString(4), "Dear " + rs.getString(2) + ", "
									+ "Your Appointment with Dr. " + docname +  " on " + date + " at "+ start
									+ " is booked." );
					}//end if
				}while(rs.next() && !bookingid.equals(""));
			}//end else
		} 
		catch (SQLException e) {
			if(e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				out.write("Unable to make booking, Try Again");
			}else {
			  System.out.println(e);
			}
		}//end catch
		finally {
		    try { 
		    	if (rs != null && rs2 != null && rs3 != null) 
		    		rs.close(); 
		    	    rs2.close();
		    	    rs3.close(); 
		    } catch (Exception e) {
		    	  System.out.println("result closing error: " + e);
		    };
		    try {
		    	if (pst != null && pst2 != null && pst3 != null && pst4 != null) 
		    		pst.close(); 
		    		pst2.close(); 
		    		pst3.close(); 
		    		pst4.close(); 
		    	} catch (Exception e) {
		    		System.out.println("statment closing error: " + e);
		    	};
		    try { 
		    	if (connection != null) 
		    		connection.close(); 
		    	} catch (Exception e) {
		    		System.out.println("connection closing error: " + e);
		    	};
		}//end finally
	}//end void doPost
	//sends booking notification
	 public void bookappointmentconfirmation(String pnumber, String msg) {
		   //initialises twilo using the account id and auth token
		   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    Message message = Message.creator(new PhoneNumber(pnumber),
		        new PhoneNumber(sendernumber), 
		        msg).create();
		    System.out.println(message.getSid());
	 }
}//end class getSelectedAppointment
