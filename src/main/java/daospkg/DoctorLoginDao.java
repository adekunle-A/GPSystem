package daospkg;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

public class DoctorLoginDao {
	//attributes
	 static Connection connection;
	 static PreparedStatement pst,pst2;
	 static ResultSet rs,rs2;
	 static PrintWriter out;
	/*DoctorLoginValidation method*/
 public static boolean DoctorLoginValidation(String name,String pass){
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
          pst.setString(2, "doctor");
          rs = pst.executeQuery();
          if(!rs.next()) {
      	  	status=false;
          }else {
          //hash the entered password with the salt
          String salt =  rs.getString(1);
          String hashedPassword = BCrypt.hashpw(pass,salt);
          //check if its a valid  info
          pst2 = connection.prepareStatement(verify);
          pst2.setString(1, name);
          pst2.setString(2, hashedPassword);
          pst2.setString(3, "doctor");
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
         }//end if
      }//end finally
	  return status;
      }//end DoctorLoginValidation	
 }//end AdminLoginaDao
