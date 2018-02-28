package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class DisplayDocAppointment
 * This displays class contains the implementation that displays the appointment
 *  that a doctor have got and patient information
 */
public class DisplayDocAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2,rs3;
    PrintWriter out;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}// end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String docid = request.getParameter("docid");
		System.out.println(docid);
		String query =   "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 =  "SELECT DOCTORID FROM DOCTORS WHERE USERS_USERID= ?";
		String query3 =  "SELECT APPOINTMENTID,APPOINTMENTDATE,STARTTIME,ENDTIME,DOCTORNAME,PATIENTNAME FROM APPOINTMENTS WHERE DOCTORS_DOCTORID= ?";
		  //Set response content type
	      response.setContentType("text/html");
	      out = response.getWriter();
			try {
				//connect to DB 
				connection = DBConnectionManager.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, docid);
				rs = pst.executeQuery();
				if(rs.next()){
					System.out.println(rs.getInt(1));
					pst2 = connection.prepareStatement(query2);
					pst2.setInt(1, rs.getInt(1));
					rs2 = pst2.executeQuery();
					if(rs2.next()){
						pst3 = connection.prepareStatement(query3);
						pst3.setInt(1, rs2.getInt(1));
						rs3 = pst3.executeQuery();
							while(rs3.next()){ 
								  out.write("<tr>");
							      out.write("<td>"+rs3.getInt(1)+"</td>");
							      out.write("<td>"+rs3.getString(2)+"</td>");
							      out.write("<td>"+rs3.getString(3)+"</td>");
							      out.write("<td>"+rs3.getString(4)+"</td>");
							      out.write("<td>"+ rs3.getString(5)+"</td>");
							      out.write("<td>"+ rs3.getString(6)+"</td>");
							      out.write("</tr>");  
							}//end while
					}else{
						System.out.println("No DoctoID found");
					}//end else
				
				}else{
					System.out.println("No UserID found");
				}//end else
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
	}//end void doPost
}//end class DisplayDocAppointment
