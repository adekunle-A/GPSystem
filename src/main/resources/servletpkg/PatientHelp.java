package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class PatientHelp
 * This is where the contact page for admin is implemented
 */
@WebServlet("/PatientHelp")
public class PatientHelp extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		//attributes
		Connection connection;
	    PreparedStatement pst,pst2;
	    ResultSet rs,rs2;
	    PrintWriter out;
	  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("patienthelp.jsp");
		//doPost(request, response);
	}//end doGet

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
			  if(rs.next()){
				out.write(rs.getString(3) + " " + rs.getString(4) +"/"+ rs.getString(5));
			  }//end if
			 }else{
				 System.out.println("No Username");
			 }//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//end doPost
}//end PatientHelp class
