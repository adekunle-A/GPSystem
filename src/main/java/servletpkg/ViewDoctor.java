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
 * Servlet implementation class ViewDoctor
 * This is the class that implements what allows admin to view doctor information
 */
public class ViewDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//attributes
    PrintWriter out;
    // use to store the messages that will be displayed on the client side
    Map<String, String> messages = new HashMap<String, String>();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("viewDoctor.jsp");
		//doPost(request, response);
	}//end void doGet
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DocInfo getdocinfo = new DocInfo();	
		//gets the doctor name form the client side
		String getDocName =  request.getParameter("docid");
		//String role =  request.getParameter("role");
		//getdocinfo.setRole(role);
		System.out.println(getDocName);
		 response.setContentType("text/html");
		 out =   response.getWriter();
		try {
			getdocinfo = ViewDoctorDao.GetDoctorInfo(getDocName);
		   if(getdocinfo != null){ 
					  out.write("<tr>");
					  out.write("<td>"+ getdocinfo.getUsername() + "</td>");	
					  out.write("<td>"+ getdocinfo.getEmail() + "</td>");
					  out.write("<td>"+getdocinfo.getFname() + "</td>");
					  out.write("<td>"+getdocinfo.getLname() + "</td>");
					  out.write("<td>"+getdocinfo.getAddress() + "</td>");
					  out.write("<td>"+getdocinfo.getDob() + "</td>");
					  out.write("<td>"+getdocinfo.getPhone()+ "</td>");
					  out.write("<td>"+getdocinfo.getGender() + "</td>");
					  out.write("</tr>");
					  System.out.println(">>>> " +getdocinfo.getUsername());
			 }else{
				 out.write("No doctor Record found");
				 System.out.println("No doctor Record found");
			 }//end else
		} catch (Exception e) {
			e.printStackTrace();
		}//end catch
	}//end doPost
}//end class ViewDoctor
