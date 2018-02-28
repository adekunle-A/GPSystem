 package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.PatientRegistrationDao;
import daospkg.UserInfo;

/**
 * Servlet implementation class RegistrationServlet
 * This contains the implementation for Registration for patients
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
    UserInfo user = new UserInfo();
    // use to store the messages that will be displayed on the client side
    Map<String, String> messages = new HashMap<String, String>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("patientregister.jsp");;
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	   //get the info from the user
	   user.setUsername(request.getParameter("username"));
	   user.setFname(request.getParameter("fname"));
	   user.setLname(request.getParameter("lname"));
	   user.setEmail(request.getParameter("email"));
	   user.setAddress(request.getParameter("address"));
	   user.setPassword(request.getParameter("password"));
	   user.setConfirmpassword(request.getParameter("confirmpassword"));
	   user.setDob(request.getParameter("dob"));
	   user.setGender(request.getParameter("gender"));
	   user.setRole("patient");
	   user.setPhone(request.getParameter("phone"));
	   // Set response content type
	      response.setContentType("text/html");
	     out = response.getWriter();
		try{	
			if(PatientRegistrationDao.Registration(user)) {
				out.write("You have successfully Registered");
		        //response.sendRedirect("patientlogin.jsp");;
			}else {
				out.write("Issue occurred during Registration, Try Again");

			}
		}catch(Exception e){
			System.out.println(e);
		}//end catch
		//doGet(request, response);
	}//end void doPost
}//end class RegistrationServlet
