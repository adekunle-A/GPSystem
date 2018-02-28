package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

/**
 * Servlet implementation class PatientSendMessage
 */
public class PatientSendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out;

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.sendRedirect("patienthelp.jsp");
	//doPost(request, response);
}//end doGet

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String username = req.getParameter("username");
	String email = req.getParameter("email");
	String msg = req.getParameter("message");
	String subject = req.getParameter("subject");
	String name = req.getParameter("name");
	System.out.println(username);
	System.out.println(email);
	System.out.println(msg);
	System.out.println(subject);
	System.out.println(name);
    
    // Set response content type
	res.setContentType("text/html");
	 out =   res.getWriter();
	try {
	  if(!username.equals("")){ 
		   	Email from = new Email(email);
	         Email to = new Email("mscproject001@gmail.com");
	         Content content = new Content("text/plain", msg);
	         Mail mail = new Mail(from, subject, to, content);
	         SendGrid sg = new SendGrid("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");
	         	Request request = new Request();
	        	     request.setMethod(Method.POST);
	             request.setEndpoint("mail/send");
	             request.setBody(mail.build());
	             Response response = sg.api(request);
	             response.setBody("Email sent successfully");
	             System.out.println(response.getStatusCode());
	             System.out.println(response.getBody());
	             System.out.println(response.getHeaders());
	             out.write("Message sent Successfully");
	  }else {
		  out.write("Unable to send Message"); 
	  }//end else
	} catch (Exception e) {
		System.out.println(e);
	}//end catch
}//end doPost
}
