package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class UpdateDocAvailability
 */
public class UpdateDocAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2,rs3;
    PrintWriter out;
    String start,end;
    String[] slots;
    Map<String, String> messages = new HashMap<String, String>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("UpdateAvailability.jsp");
		//doPost(request, response);
	}//end void doGet
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String username = request.getParameter("username");
		Date getdate = new Date(request.getParameter("startDate"));
		Date getenddate = new Date(request.getParameter("endDate"));
		System.out.println("save getdate" + getdate);
		String getd = dateFormat.format(getdate);
		String getd2 =dateFormat.format(getenddate);
		String[] parts = getd.split(" ");
		String availDate = parts[0];
		String startTime = parts[1];
		String[] parts2 = getd2.split(" ");
		String endTime = parts2[1];
		String query =  "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 = "SELECT * FROM DOCTORS WHERE USERS_USERID= ?";
		String query3 = "INSERT INTO DOCTORSAVAILABILITY"
				+ "(APPOINTMENTDATE, STARTTIME, ENDTIME,STATUS,DOCTORS_DOCTORID) VALUES"
				+ "(?,?,?,?,?)";
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
	        	  if(rs2.next()){
							    try {
								    pst3 = connection.prepareStatement(query3);
									pst3.setString(1, availDate);
					        		  	pst3.setString(2, startTime);
					        		  	pst3.setString(3, endTime);
					        		  	pst3.setInt(4, 1);
				 				 	pst3.setInt(5, rs2.getInt(1));
				 				 	pst3.execute();
				 				 	out.write("Availability updated Successfully");
				 				}catch(Exception e1){
				 					 System.out.println("Query3 Error: " + e1);
				 				}//end catch
					}else{
						out.write("Unable to update Availability");
	        	 }//end
	        	
			 } else {
				  out.write("Unable to update Availability");
 				  request.getRequestDispatcher("updateAvailability.jsp").forward(request,
 	                         response);	 
		     }//end else
		 } catch (SQLException e) {
			  System.out.println("Query1 Error: " + e);
				
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
		    	if (pst != null && pst2 != null && pst3 != null) 
		    		pst.close(); 
		    		pst2.close(); 
		    		pst3.close(); 
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
		
	}//end doPost
}//end UpdateDocAvailability class
