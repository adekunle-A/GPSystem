package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class updatePrescriptionStatus
 */
@WebServlet("/updatePrescriptionStatus")
public class updatePrescriptionStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2;
    PrintWriter out;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("bookingid");
		//String doc = request.getParameter("docid");
		String Appdate = request.getParameter("appdate");
		String status = request.getParameter("status");
		System.out.println("PATIENT USERNAME" + pid);
		System.out.println("Appdate" + Appdate);
		System.out.println("Status" + status);
		//Queries
		String query =  "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 =  "SELECT * FROM PRESCRIPTIONS WHERE APPDATE= ? AND PATIENTID= ?";
		String query3 = "UPDATE PRESCRIPTIONS SET STATUS = ? WHERE idprecriptions = ?";
	     // Set response content type
	      response.setContentType("text/html");
	      out = response.getWriter();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, pid);
			rs = pst.executeQuery();
			  if(rs.next() !=true){ 
				  out.write("No prescriptions found");
			  }else {
				    pst2 = connection.prepareStatement(query2);
				    pst2.setString(1,Appdate);
					pst2.setInt(2, rs.getInt(1));
					rs2 = pst2.executeQuery();
				  if (rs2.next()){
					  pst3 = connection.prepareStatement(query3);
					  pst3.setString(1, status);
					  pst3.setInt(2, rs2.getInt(1));
					// execute insert SQL statement
					  pst3 .executeUpdate();
					  out.write("Status updated successfully");
					  
				  }else {
					  out.write("unable to update prescription");
				  }//end else
	            }//end else
		}catch(Exception e) {
			e.getStackTrace();
		}//end catch
	}//end doPost

}//end class
