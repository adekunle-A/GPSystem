package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class DeleteDocAvalability
 */
@WebServlet("/DeleteDocAvailability")
public class DeleteDocAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
    PreparedStatement pst,pst2,pst3,pst4;
    ResultSet rs,rs2,rs3;
    PrintWriter out;
    String start,end;
    String[] slots;

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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String username = request.getParameter("username");
		String getDate  = request.getParameter("getDate");
		System.out.println(getDate);
		//get the  times
		String[] part2 = getDate.split(",");
		//gets date
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String eventdate = part2[0] + part2[1] + " "+ year;
		Date getdate = new Date(eventdate);
		System.out.println("getDate" +getdate);
		//convert the date to string
		String getd = dateFormat.format(getdate);
		String availDate = getd;
		//get the start and end times accordingly
		String[] timesparts = part2[2].split("-");
		//start time
		String startTime = timesparts[0];
		//end time
		String endTime = timesparts[1];
		
		String query =   "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 =  "SELECT DOCTORID FROM DOCTORS WHERE USERS_USERID= ?";
		String query3 = "SELECT *  FROM DOCTORSAVAILABILITY WHERE APPOINTMENTDATE= ? AND STARTTIME= ? AND ENDTIME= ? AND STATUS= ?"
				+ " AND DOCTORS_DOCTORID= ?";
		String query4 = "DELETE FROM DOCTORSAVAILABILITY WHERE ADDAPPOINTMENTID=?";
		System.out.println("availDate " + availDate);
		System.out.println("startTime " +  startTime.trim());
		System.out.println("endTime " + endTime.trim());
	    response.setContentType("text/html");
		 out = response.getWriter();
		  try {
			 //connect to DB 
			  connection = DBConnectionManager.getConnection();
			  pst = connection.prepareStatement(query);
			  pst.setString(1, username);
	          rs = pst.executeQuery();
				 if(rs.next() && !username.equals("")){ 
					 System.out.println("userid" +rs.getString(1));
					  pst2 = connection.prepareStatement(query2);
					  pst2.setString(1, rs.getString(1));
					  rs2 = pst2.executeQuery();
					 
					  if(rs2.next()){
						  System.out.println("docid "+rs2.getString(1));
						  pst3 = connection.prepareStatement(query3); 
						  pst3.setString(1, availDate);
						  pst3.setString(2, startTime.trim());
						  pst3.setString(3, endTime.trim());
						  pst3.setInt(4, 1);
						  pst3.setInt(5, rs2.getInt(1));
						  rs3 = pst3.executeQuery();
						 // System.out.println(rs3.next());
							  if(rs3.next() == true) {
								  System.out.println(rs3.getInt(1));
								  pst4 = connection.prepareStatement(query4);
								  pst4.setInt(1, rs3.getInt(1));
							      //execute the prepared statement for delete
								  pst4.executeUpdate();
								  out.write("Availability Removed Successfully");
							  }//end if
					
					  }else{
						  out.write("Unable to Remove Availability");
					  }	 
				 }else{
					 	  out.write("Unable to Remove Successfully");
			
				 }//end else
		} catch (SQLException e) {
			System.out.println(e);
		}//end catch
	
	}//end doPost
}//end class DeleteDocAvailability
