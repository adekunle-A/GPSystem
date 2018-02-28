package servletpkg;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DocInfo;
import daospkg.RemoveDoctorDao;


/**
 * Servlet implementation class RemoveDoctor
 */
@WebServlet("/removeDoctor")
public class RemoveDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DocInfo setdocinfo = new DocInfo();
    PrintWriter out;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("removeDoctor.jsp");
	}//end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get information from the client side
		String doctorid = request.getParameter("docid");
		setdocinfo.setRole(request.getParameter("role"));
		 System.out.println("servlet" + doctorid);
		 System.out.println("gg" + setdocinfo.getRole());
		//set response contentType	 
		 response.setContentType("text/html");
		 out =   response.getWriter();
		try{
			 //gets the doctor id that matches the above and deletes
			 if(RemoveDoctorDao.RemoveDoctor(doctorid) == true) {
				 out.write("Doctor Removed Successfully");
				 System.out.println("Doctor Removed");
			 }else {
				 out.write("Invalid input entered, Try Again");
			 }//end else
		}catch(Exception e){
			System.out.println(e);
		}//end catch
	}//end doPost
}//end class RemoveDoctor
