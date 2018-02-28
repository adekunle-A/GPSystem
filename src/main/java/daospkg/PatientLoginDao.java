package daospkg;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;
/**This is PATIENT Login Dao, this has the implementation that allow PATIENT to login**/
public class PatientLoginDao {
	//attributes
	 static Connection connection = null;
	 static PreparedStatement pst,pst2;
	 static ResultSet rs,rs2;
	 static PrintWriter out;
	/*PatientLoginValidation method*/
 public static boolean PatientLoginValidation(String name,String pass){
	boolean status=false;
	  try {
		//connect to DB 
		connection = DBConnectionManager.getConnection();
          //query
		  String getsalt = "SELECT SALT FROM USERS WHERE USERNAME= ? AND ROLE= ?";
		  String verify = "SELECT * FROM USERS WHERE USERNAME= ? AND PASSWORD= ? AND ROLE= ?";
          
		  //gets the salt
		  pst = connection.prepareStatement(getsalt);
          pst.setString(1, name);
          pst.setString(2, "patient");
          rs = pst.executeQuery();
        
          if(!rs.next()) {
        	  	status=false;
          }else {
        	  System.out.println(rs.getString(1));
          //hash the entered password with the salt
          String salt =  rs.getString(1);
          String hashedPassword = BCrypt.hashpw(pass,salt);
          //check if its a valid  info
          pst2 = connection.prepareStatement(verify);
          pst2.setString(1, name);
          pst2.setString(2, hashedPassword);
          pst2.setString(3, "patient");
          rs2 = pst2.executeQuery();
          status = rs2.next(); 
          }
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
            }
         if (pst != null) {
            try {
            	pst.close();
            } catch (Exception e) {}
            pst = null;
            }
	
         if (connection != null) {
            try {
            	connection.close();
            } catch (Exception e) {
            }

            connection = null;
         }
      }//end finally
	  return status;
      }//end PatientLoginValidation	
 }//end AdminLoginaDao
