package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.PasswordResetDao;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//attributes
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
		String username = request.getParameter("username");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(role);
		 // Set response content type
	      response.setContentType("text/html");
	     out = response.getWriter();
		try {
			if(!PasswordResetDao.resetPassword(username, role, password) == true) {
				out.write("Invalid information enterd. Try again");
			}else {
				out.write("Password reset successfully, Confirmation sent");
;
		   }
		}catch(Exception e) {
			//e.getStackTrace();
			System.out.println(e);
		}
	}
}//end doPost
