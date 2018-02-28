package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daospkg.DocInfo;
import daospkg.addDiagnosisInfoDao;

/**
 * Servlet implementation class addDiagnosisInfo
 */
public class addDiagnosisInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out;
    DocInfo doctor = new DocInfo();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("addDiagnosisInfo.jsp");
		//doPost(request, response);
	}//end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//diagnosis information
		doctor.setBookingid(request.getParameter("bookingid"));
		doctor.setUsername(request.getParameter("docid"));
		doctor.setAppdate(request.getParameter("appdate"));
		doctor.setSymptoms(request.getParameter("symptoms"));
		doctor.setDisease(request.getParameter("disease"));
		doctor.setTreatment(request.getParameter("treatment"));
		doctor.setPrescriptionStatus(request.getParameter("status"));

		System.out.println("bookingid " + doctor.getBookingid());
		System.out.println("docid " + doctor.getUsername());
		System.out.println("appdate " + doctor.getAppdate());
		System.out.println("symptoms " + doctor.getSymptoms());
		System.out.println("disease " + doctor.getDisease());
		System.out.println("treatment " + doctor.getTreatment());
		System.out.println("status " + doctor.getPrescriptionStatus());
		
	     response.setContentType("text/html");
	     out = response.getWriter();
	     try {
		    if(addDiagnosisInfoDao.AddDignosisInfo(doctor)==true) {
					out.write("Diagnosis added successfully");
		    }else {
		    		out.write("Unable to perform Operation");
		    }//end else
	     }catch(Exception e) {
	    		System.out.println(e);
	     }//end catch
	}//end doPost
}//end class addDiagnosisInfo
