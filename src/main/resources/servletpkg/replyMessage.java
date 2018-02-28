package servletpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sendgrid.*;


import daospkg.DBConnectionManager;

/**
 * Servlet implementation class replyMessage
 */
@WebServlet("/replyMessage")
public class replyMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
		//attributes
		Connection connection;
	    PreparedStatement pst,pst2;
	    ResultSet rs,rs2;
	    PrintWriter out;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("adminhelp.jsp");
		//§	§doPost(request, response);
	}//end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String recipient = req.getParameter("email");
		String emailContent = req.getParameter("message");
		String emailSubject = req.getParameter("subject");
		String name = req.getParameter("name");
		//String recipient = "mscproject001@gmail.com";
	
		System.out.println(recipient);
		System.out.println(emailContent);
		System.out.println(emailSubject);
		System.out.println(name);
	    
	    // Set response content type
		res.setContentType("text/html");
		 out =   res.getWriter();
			//connect to DB 
			connection = DBConnectionManager.getConnection();
		try {
		  if(!recipient.equals("")){ 
			  	 Email from = new Email("mscproject001@gmail.com");
		         Email to = new Email(recipient);
		         Content content = new Content("text/plain", emailContent);
		         Mail mail = new Mail(from, emailSubject, to, content);
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
		             out.write("sent successfully");
				  }else {
					  out.write("Unable to send Message"); 
				  }
		    }catch (IOException ex) {
	        	   //System.out.println(ex);
	           ex.printStackTrace();
	         }
	}//end doPost
	//send email to user
}

