package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.AddDoctorDao;
import daospkg.DocInfo;

/**
 * Servlet implementation class AddDoctor
 */
@WebServlet("/AddDoctor")
public class AddDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out;
    DocInfo doctor = new DocInfo();
    // use to store the messages that will be displayed on the client side
    Map<String, String> messages = new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("adminDashboard.jsp");
		doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doctor.setUsername(request.getParameter("username"));
		doctor.setFname(request.getParameter("fname"));
		doctor.setLname(request.getParameter("lname"));
		doctor.setEmail(request.getParameter("email"));
		doctor.setAddress(request.getParameter("address"));
		doctor.setPassword(request.getParameter("password"));
		doctor.setConfirmpassword(request.getParameter("confirmpassword"));
		doctor.setDob(request.getParameter("dateofbirth"));
		doctor.setGender(request.getParameter("gender"));
		doctor.setRole(request.getParameter("role"));
		doctor.setPhone(request.getParameter("phone"));
		//doctor.setDocCategory(request.getParameter("docCategory"));
		   System.out.println(doctor.getUsername());
		   // Set response content type
		      response.setContentType("text/html");
		     out = response.getWriter();
				try{
					if(AddDoctorDao.DoctorRegistration(doctor)){
				        out.write("Doctor Added Successfully");
					}else{
						System.out.println("error");
						out.write("Unable to Doctor Added Successfully");
					}//end else
				}catch(Exception e){
					e.printStackTrace();
					//System.out.println(e);
		}//end catch
	}//end doPost
}//end class AddDoctor
