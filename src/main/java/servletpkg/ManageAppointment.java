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

import daospkg.DBConnectionManager;


/**
 * Servlet implementation class ViewAppointment
 * This class contains the implementation that  display patients upcoming
 *  appointments and  enable them to cancel if they want
 */
public class ManageAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection;
    PreparedStatement pst,pst2;
    ResultSet rs,rs2;
    PrintWriter out;

    // use to store the messages that will be displayed on the client side
    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //response.sendRedirect("ManageAppointment.jsp");
		 doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("hhh" + username);
		String query =  "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 =  "SELECT * FROM APPOINTMENTS WHERE PATIENTS_PATIENTID= ?";
	     // Set response content type
	      response.setContentType("text/html");
	      out = response.getWriter();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			 
			  if(rs.next() && !username.equals("")){ 	   
				    pst2 = connection.prepareStatement(query2);
					pst2.setString(1, rs.getString(1));
					rs2 = pst2.executeQuery();
				  while(rs2.next()){
					  out.write("<tr>");
				      out.write("<td class='date'>"+rs2.getString(2)+"</td>");
				      out.write("<td class='start'>"+rs2.getString(3)+"</td>");
				      out.write("<td class='end'>"+rs2.getString(4)+"</td>");
				      out.write("<td class='name'>"+rs2.getString(5)+"</td>");
				      out.write("<td class='id'>"+ username+"</td>");
				      out.write("<td class='appcancelbutton'><button style='font-size:15px' class='cancelapp'>Cancel <i class='fa fa-calendar-check-o'></i></button></td>");
				      out.write("</tr>");
				      // System.out.println(rs2.getString(2) + "   " + rs2.getString(3) +"  "+ rs2.getString(4) + "   " + rs2.getString(5)+  "<br>");
				      //out.println("AppointmentDate" + "   " + "StartTime" + "   " + "DoctorName" + "   " + "PatientName" + "   "  + "<br>");
				     // out.println(rs2.getString(2) + "   " + rs2.getString(3) +"  "+ rs2.getString(4) + "   " + rs2.getString(5)+  "<br>");
				  }
	            }else {
	            	   messages.clear();
	            	   messages.put("error", "No Upcoming Appointments");
	            	   request.setAttribute("messages", messages);
	                   // Forward request to JSP for display.
	                   request.getRequestDispatcher("ManageAppointment.jsp").forward(request,
	                           response);
	           }//edn else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
//		finally {
//		    try { 
//		    	if (rs != null && rs2 != null) 
//		    		rs.close(); 
//		    	    rs2.close();
//		    } catch (Exception e) {
//		    	  System.out.println("result closing error: " + e);
//		    };
//		    try {
//		    	if (pst != null && pst2 != null) 
//		    		pst.close(); 
//		    		pst2.close(); 
//		    	} catch (Exception e) {
//		    		System.out.println("statment closing error: " + e);
//		    	};
//		    try { 
//		    	if (connection != null) 
//		    		connection.close(); 
//		    	} catch (Exception e) {
//		    		System.out.println("connection closing error: " + e);
//		    	};
//		}//end finally
	}//end void doPost
}//end class ManageAppointment
