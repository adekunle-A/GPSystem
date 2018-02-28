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
 * Servlet implementation class viewPrescription
 */
@WebServlet("/viewPrescription")
public class viewPrescription extends HttpServlet {
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
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("prescripusername" + username);
		String query =  "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 =  "SELECT * FROM PRESCRIPTIONS WHERE PATIENTID= ?";
	     // Set response content type
	      response.setContentType("text/html");
	      out = response.getWriter();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			  if(rs.next() !=true){ 
				  response.sendRedirect("viewPrescription.jsp");
				  //out.write("No prescriptions found");
			  }else {
				    pst2 = connection.prepareStatement(query2);
					pst2.setString(1, rs.getString(1));
					rs2 = pst2.executeQuery();
				  while(rs2.next()){
					  out.write("<tr>");
				      out.write("<td class='id'>"+rs2.getString(1)+"</td>");
				      out.write("<td class='date'>"+rs2.getString(2)+"</td>");
				      out.write("<td class='drug'>"+rs2.getString(3)+"</td>");
				      out.write("<td class='stat'>"+rs2.getString(4)+"</td>");
				      //out.write("<td class='prescriptionButton'><button style='font-size:15px' class='prescripApp'>Request</button></td>");
				      out.write("</tr>");
				      // System.out.println(rs2.getString(2) + "   " + rs2.getString(3) +"  "+ rs2.getString(4) + "   " + rs2.getString(5)+  "<br>");
				      //out.println("AppointmentDate" + "   " + "StartTime" + "   " + "DoctorName" + "   " + "PatientName" + "   "  + "<br>");
				     // out.println(rs2.getString(2) + "   " + rs2.getString(3) +"  "+ rs2.getString(4) + "   " + rs2.getString(5)+  "<br>");
				  }
	            }//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}

}
