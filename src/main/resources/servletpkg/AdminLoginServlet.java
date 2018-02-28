package servletpkg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daospkg.AdminLoginDao;

/**
 * Servlet implementation class AdminLoginServlet
 * This class contains the implementation for Administrator login
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    // use to store the messages that will be displayed on the client side
	    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("adminlogin.jsp");
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
			if(AdminLoginDao.AdminLoginValidation(username, password)){
			     HttpSession session=request.getSession();   
	            	 session.setAttribute("username", username);
	            	 response.sendRedirect("adminDashboard.jsp");
			}else {
				 messages.put("error", "Invalid login Entered, Try Again");
          	   request.setAttribute("messages", messages);
                 // Forward request to JSP for display.
                 request.getRequestDispatcher("adminlogin.jsp").forward(request,
                         response);
			}//end else
	}// end void doPost
}//end Class AdminLoginServlet
