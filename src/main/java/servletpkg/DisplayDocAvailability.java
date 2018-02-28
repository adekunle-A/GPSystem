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
import daospkg.DocInfo;

/**
 * Servlet implementation class DisplayDocAvailability
 */
public class DisplayDocAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2,rs3;
    PrintWriter out;

    Map<String, String> messages = new HashMap<String, String>();
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("UpdateAvailability.jsp");
		doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DocInfo info = new DocInfo();
	    String docid = request.getParameter("username"); 
		info.setUsername(docid);
	    String query = "SELECT USERID,FNAME, LNAME FROM USERS WHERE USERNAME= ?";
	    String query2 = "SELECT DOCTORID FROM DOCTORS WHERE USERS_USERID= ?";
	    String query3 = "SELECT ADDAPPOINTMENTID, APPOINTMENTDATE,STARTTIME,ENDTIME FROM DOCTORSAVAILABILITY WHERE DOCTORS_DOCTORID= ?";
	    response.setContentType("text/html");
		 out =   response.getWriter();
		 System.out.println("dispolayAvailability " + info.getUsername());
			try {
				//connect to DB 
				connection = DBConnectionManager.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, docid);
				rs = pst.executeQuery();
				if(rs.next()){
					pst2 = connection.prepareStatement(query2);
					pst2.setInt(1, rs.getInt(1));
					rs2 = pst2.executeQuery();
					System.out.println(rs.getInt(1));
					if(rs2.next()){
						pst3 = connection.prepareStatement(query3);
						pst3.setInt(1, rs2.getInt(1));
						rs3 = pst3.executeQuery();
						while(rs3.next()){
							  out.write("<tr>");
						      out.write("<td>"+rs3.getString(1)+"</td>");
						      out.write("<td>"+rs3.getString(2)+"</td>");
						      out.write("<td>"+rs3.getString(3)+"</td>");
						      out.write("<td>"+rs3.getString(4)+"</td>");
						      out.write("<td>"+rs.getString(2) + " " + rs.getString(3)+"</td>");
						      out.write("<td class='cancelavailbutton'><button style='font-size:15px'>Cancel</button></td>");
						      out.write("</tr>");
						}//end while
					}//end if
				}else{
					System.out.println("No userId Found");
				}//end else
			} catch (SQLException e) {
				e.printStackTrace();
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
//			    try { 
//			    	if (connection != null) 
//			    		connection.close(); 
//			    	} catch (Exception e) {
//			    		System.out.println("connection closing error: " + e);
//			    	};
			}//end finally
	}//end void doPost
}//end  class UpdateAvailability