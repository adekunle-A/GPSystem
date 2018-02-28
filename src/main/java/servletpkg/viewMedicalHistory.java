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
 * Servlet implementation class viewMedicalHistory
 */
@WebServlet("/viewMedicalHistory")
public class viewMedicalHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
    PreparedStatement pst,pst2,pst3,pst4;
    ResultSet rs,rs2,rs3;
    PrintWriter out;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("viewMedicalHistory.jsp");
		doPost(request, response);
	}//end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String query =  "SELECT * FROM USERS WHERE USERNAME= ?";
		String query2 =  "SELECT * FROM PATIENTHEALTHRECORD WHERE PATIENTID= ?";
		 response.setContentType("text/html");
		 out =   response.getWriter();
		 System.out.println(username);
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
		  if(rs.next()){ 
			  	System.out.println(rs.getString(1));
				pst2 = connection.prepareStatement(query2);
				pst2.setString(1, rs.getString(1));
				rs2 = pst2.executeQuery();
				  while(rs2.next()){
					  out.write("<tr>");
					  out.write("<td>"+rs2.getString(1) + "</td>");
					  out.write("<td>"+rs2.getString(2) + "</td>");
					  out.write("<td>"+rs2.getString(3) + "</td>");
					  out.write("<td>"+rs2.getString(4) + "</td>");
					  out.write("<td>"+rs2.getString(5) + "</td>");
					  out.write("<td>"+rs2.getString(6)+ "</td>");
					  out.write("</tr>");
					  System.out.println(">>>> " + rs2.getString(6));
				  }//end while
			 }else{
				 out.write("No medical records found");
				 System.out.println("No medical records found");
			 }//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}

}
