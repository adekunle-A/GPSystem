package servletpkg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daospkg.DoctorLoginDao;

/**
 * Servlet implementation class DoctorLoginServlet
 * This is the implementation for Doctors login
 */
public class DoctorLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // use to store the messages that will be displayed on the client side
	 Map<String, String> messages = new HashMap<String, String>();
	    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("doctorlogin.jsp");
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		try {
		  if(DoctorLoginDao.DoctorLoginValidation(username, password)) {
            	 HttpSession session = request.getSession();
            	 session.setAttribute("username", username);
            	 response.sendRedirect("doctorsDashboard.jsp");
            
			} else {
            	   messages.put("error", "Invalid login Entered, Try Again");
            	   request.setAttribute("messages", messages);
                   // Forward request to JSP for display.
                   request.getRequestDispatcher("doctorlogin.jsp").forward(request,
                           response);
             }//end else
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch
	}//end void doPost
}//end class DoctorLoginServlet
