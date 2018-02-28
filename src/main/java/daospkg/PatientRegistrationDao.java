package daospkg;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;
/*DAO that handle patient registrations*/
public class PatientRegistrationDao {
	//attributes
	 static Connection connection;
	 static PreparedStatement pst;
	 private static String salt = BCrypt.gensalt(8);
	 public static Boolean Registration(UserInfo getUserInfo) {
		 boolean status=false;
		  try {
			//connect to DB 
				connection = DBConnectionManager.getConnection();
	          //query
	          String insertUSERS = "INSERT INTO USERS"
						+ "(USERNAME, FNAME, LNAME, EMAIL, ADDRESS, PASSWORD, ROLE, DOB, GENDER,PHONE,SALT) VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?)";
	          System.out.println(getUserInfo.getPassword());
	          String hashedPassword = BCrypt.hashpw(getUserInfo.getPassword(),salt);
	          System.out.println("salt >> "+ salt);
	          System.out.println("hashedpassword "+ hashedPassword);
	          System.out.println(getUserInfo.getConfirmpassword());
	          /****/
	          if(getUserInfo.getPassword().equals(getUserInfo.getConfirmpassword())){
					pst = connection.prepareStatement(insertUSERS);
					pst.setString(1, getUserInfo.getUsername());
			        pst.setString(2, getUserInfo.getFname());
			        pst.setString(3, getUserInfo.getLname());
			        pst.setString(4, getUserInfo.getEmail());
			        pst.setString(5, getUserInfo.getAddress());
			        pst.setString(6, hashedPassword);
			        pst.setString(7, getUserInfo.getRole());
			        pst.setString(8, getUserInfo.getDob());
			        pst.setString(9, getUserInfo.getGender());
			        pst.setString(10, getUserInfo.getPhone());
			        pst.setString(11,salt);
			        //execute insert SQL statement
			        pst.executeUpdate();
			        status = true;
			        System.out.println(status);
			        System.out.println("Insert made successfully");
	          }
	        
	      } catch (Exception e) {
	          if (connection == null) {
	              System.out.println("Connection not successful.");
	          }//end if
	          e.printStackTrace();
	      }//end catch up
	      finally 
	      {
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
	      }
		  return status;
	  }//end Registration
}//end class PatientRegistrationDao
