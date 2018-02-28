package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DocInfo;
import daospkg.DoctorsAvailabilityDao;

/**
 * Servlet implementation class DoctorsAvailability
 * This is the implementation where doctors can add availability
 */
public class DoctorsAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    PrintWriter out;
	    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("addDocAvailability.jsp");
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DocInfo info = new DocInfo();
		String username = request.getParameter("username");
		info.setUsername(username);
		String date = request.getParameter("datepicker");
		info.setAvaildatepicker(date);
		String [] timeslot = request.getParameterValues("selectedslot");
		info.setTimeslot(timeslot);
		try {
	        	    // when the result set is finished then print success message
	        		if(DoctorsAvailabilityDao.AddDoctorsAvailability(info) == true){
	        		  messages.put("success", "Availability Added Successfully");
	              	  request.setAttribute("messages", messages);
	  				  request.getRequestDispatcher("addDocAvailability.jsp").forward(request,
	  	                         response);	 
			 } else {
				  messages.put("error", "Unable to Add Availability");
             	  request.setAttribute("messages", messages);
 				  request.getRequestDispatcher("addDocAvailability.jsp").forward(request,
 	                         response);	 
		     }//end else
		 } catch (Exception e) {
			  System.out.println("Query1 Error: " + e);
	   }//end catch
	}//end end doPost
}//end DoctorsAvailability
