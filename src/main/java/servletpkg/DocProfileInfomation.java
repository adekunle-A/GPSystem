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
import daospkg.ViewDoctorDao;

/**
 * Servlet implementation class DocProfileInfomation
 * This is the implementation that displays doctor's details 
 * on the profile page
 */
public class DocProfileInfomation extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    PrintWriter out;
	    // use to store the messages that will be displayed on the client side
	    Map<String, String> messages = new HashMap<String, String>();
	    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect("doctorDashboard.jsp");
		 doPost(request, response);
	}//end void doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		 DocInfo getdocinfo = new DocInfo();
		 response.setContentType("text/html");
		 out =   response.getWriter();
		try {
			//get doctor info so that it can be displayed for proile
			getdocinfo = ViewDoctorDao.GetDoctorInfo(username);
		   if(getdocinfo != null){ 
				  out.write("<tr>");
				  out.write("<td>"+getdocinfo.getUsername() + "</td>");
				  out.write("<td>"+getdocinfo.getDob() + "</td>");
				  out.write("<td>"+getdocinfo.getEmail() + "</td>");
				  out.write("<td>"+getdocinfo.getFname() + "</td>");
				  out.write("<td>"+getdocinfo.getLname() + "</td>");
				  out.write("<td>"+getdocinfo.getAddress() + "</td>");
				  out.write("<td>"+getdocinfo.getGender());
				  out.write("</tr>");
				  System.out.println(">>>> " +getdocinfo.getUsername());
			 }else{
				 System.out.println("No Username");
			 }//end else
		} catch (Exception e) {
			e.printStackTrace();
		}//end catch
	}//end void doPost
}//end class DocProfileInfomation

