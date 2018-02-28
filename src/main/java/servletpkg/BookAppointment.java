package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DBConnectionManager;

/**
 * Servlet implementation class BookAppointment
 * This class contains the implementation appointment booking for patients
 */
public class BookAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	//attributes
	Connection connection;
    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2,rs3;
    PrintWriter out;
    // use to store the messages that will be displayed on the client side
   // ArrayList<Object> a2 = new ArrayList<>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("bookAppointment.jsp");
		 doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = request.getParameter("bookingid");
		String selectedDate = request.getParameter("datepicker");
		String query = "SELECT * FROM DOCTORSAVAILABILITY WHERE STATUS= ?";
		String query2 = "SELECT * FROM DOCTORS WHERE DOCTORID= ?";
		String query3 = "SELECT * FROM USERS WHERE USERID= ?";
		System.out.println("bookAppoClas " +  bookingid);

		 // Set response content type
	      response.setContentType("text/html");
	     out = response.getWriter();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			pst = connection.prepareStatement(query);
			//pst.setString(1, selectedDate);
			pst.setInt(1, 1);//where doctor is available
			rs = pst.executeQuery();
			System.out.println(selectedDate);
			if (!rs.next()) {
				   out.write("No Appointment Available for date:" + selectedDate);
			}else {
				do{
					pst2 = connection.prepareStatement(query2);
					pst2.setString(1, rs.getString(6));
					rs2 = pst2.executeQuery();
					System.out.println("id" + rs.getString(1));
					System.out.println("date" + rs.getString(2));
					System.out.println("start"+rs.getString(3));
					System.out.println("end"+rs.getString(4));
					System.out.println("status"+rs.getString(5));
					System.out.println("docid"+rs.getString(6));
					if(rs2.next()){
						pst3 = connection.prepareStatement(query3);
						pst3.setString(1, rs2.getString(3));
						rs3 = pst3.executeQuery();
						 System.out.println(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString(2)).before(new Date()));
						  if (!new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString(2)).before(new Date())) {
							while(rs3.next()){
								  out.write("<tr>");
							      out.write("<td class='id'>"+rs.getString(1)+"</td>");
							      out.write("<td class='name'>"+rs3.getString(3) + " " + rs3.getString(4)+"</td>");
							      out.write("<td class='date'>"+rs.getString(2)+"</td>");
							      out.write("<td class='start'>"+rs.getString(3)+"</td>");
							      out.write("<td class='end'>"+ rs.getString(4)+"</td>");
							      out.write("<td class='prebutton'><button style='font-size:15px' class='bookapp'>Book<i class='fa fa-calendar-check-o'></i></button></td>");
							      out.write("</tr>");	
								  System.out.println("AvailableID: " + rs.getString(1) + " APPOINTMENTDATE "+ rs.getString(2) +" starttime: "+
									       rs.getString(3)+" ENDtime: "+ rs.getString(4) +"fName: " + rs3.getString(3) + "lName: " + rs3.getString(4));
							}	
						  }
					}else{
						 out.write("Unable to perform Operation");
					}//end else
				}while(rs.next() && selectedDate !="");
			}//end else
		} catch (Exception e) {
			e.printStackTrace();
		}//end catch
//		finally {
//		    try { 
//		    	if (rs != null && rs2 != null && rs3 != null) 
//		    		rs.close(); 
//		    	    rs2.close();
//		    	    rs3.close(); 
//		    } catch (Exception e) {
//		    	  System.out.println("result closing error: " + e);
//		    };
//		    try {
//		    	if (pst != null && pst2 != null && pst3 != null) 
//		    		pst.close(); 
//		    		pst2.close(); 
//		    		pst3.close(); 
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
}//end Class BookAppointment
