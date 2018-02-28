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

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class MainServlet
 *  This is the implementation that displays patient's details 
 * on the profile page
 */
@WebServlet("/patientDashboard")
public class ProfileInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	    Connection connection;
	    PreparedStatement pst;
	    ResultSet rs;
	    PrintWriter out;
	    // use to store the messages that will be displayed on the client side
	    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect("patientDashboard.jsp");
		 doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String query =  "SELECT * FROM USERS WHERE USERNAME= ? AND ROLE= ?";
		 response.setContentType("text/html");
		 out =   response.getWriter();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, "patient");
			rs = pst.executeQuery();
		  if(!username.equals("")){ 
			  while(rs.next()){
				  //response.setContentType("text/html;charset=UTF-8");
				  out.write("<tr>");
				  out.write("<td>"+rs.getString(2) + "</td>");
				  out.write("<td>"+rs.getString(9) + "</td>");
				  out.write("<td>"+rs.getString(3) + "</td>");
				  out.write("<td>"+rs.getString(4) + "</td>");
				  out.write("<td>"+rs.getString(6) + "</td>");
				  out.write("<td>"+rs.getString(10) + "</td>");
				  out.write("</tr>");
				  System.out.println(">>>> " + rs.getString(2));
			  }//end while
			 }else{
				 System.out.println("No Username");
			 }//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
//		finally {
//		    try { 
//		    	if (rs != null) 
//		    		rs.close(); 
//		    } catch (Exception e) {
//		    	  System.out.println("result closing error: " + e);
//		    };
//		    try {
//		    	if (pst != null) 
//		    		pst.close(); 
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
}//end class patientInformation
