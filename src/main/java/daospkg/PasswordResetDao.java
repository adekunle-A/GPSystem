package daospkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class PasswordResetDao {
	// Twillo Account Sid and Token 
		public static final String ACCOUNT_SID = "AC0a88ef937d3a0f60ce6ef2f815442d7e";
	    public static final String AUTH_TOKEN = "1191a945ab34038eb385121f46e40207";
	    public static final String sendernumber = "441422400778";
	//attributes
	 static Connection connection;
	 static PreparedStatement pst,pst2,pst3;
	 static ResultSet rs;
	 static boolean status=false;
	 private static String salt = BCrypt.gensalt(8);
	 public static Boolean resetPassword(String uname, String role, String pass) {
	 try {
		  //connect to DB 
		connection = DBConnectionManager.getConnection();
		//queries
		String query =  "SELECT * FROM USERS WHERE USERNAME= ? AND ROLE= ?";
		String query2 = "UPDATE USERS SET PASSWORD = ?, SALT = ? WHERE USERID = ?";
		System.out.println(pass + salt);
		String hashedPassword = BCrypt.hashpw(pass,salt);
		System.out.println(hashedPassword);
		pst = connection.prepareStatement(query);
		pst.setString(1, uname);
		pst.setString(2, role);
		rs = pst.executeQuery();
		if(!rs.next()) {
			status =false;
		}else {
			//System.out.println(rs.getInt(1));
			pst2 = connection.prepareStatement(query2);
			pst2.setString(1, hashedPassword);
			pst2.setString(2, salt);
			pst2.setInt(3, rs.getInt(1));
			//update SQL statement
			pst2 .executeUpdate();
			status =true;
			sendpasswordresetConfirmation(rs.getString(11), "Dear " + uname + " your password has been successfully Reset, Contact the Clinic for your New Password");
	   }//end else
     } catch (Exception e) {
         if (connection == null) {
             System.out.println("Connection not successful.");
         }//end if
         e.printStackTrace();
     }//end catch up
     finally 
     {
   	  	if (rs != null)	{
   	    try {
               rs.close();
            } catch (Exception e) {}
               rs = null;
            }//end if
        if (pst != null) {
           try {
           	pst.close();
           } catch (Exception e) {}
           pst = null;
           }//end if
        if (connection != null) {
           try {
           	connection.close();
           } catch (Exception e) {
           }

           connection = null;
        }//end if
     }//end finally
	  return status;
 }//end DoctorRegistration method
	 public static void sendpasswordresetConfirmation(String pnumber, String msg) {
		   //initialises twilo using the account id and auth token
		   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    Message message = Message.creator(new PhoneNumber(pnumber),
		        new PhoneNumber(sendernumber), 
		        msg).create();
		    System.out.println(message.getSid());
	 }
}//end PasswordResetDao
