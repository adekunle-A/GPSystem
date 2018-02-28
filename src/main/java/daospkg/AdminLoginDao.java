package daospkg;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**This is Admin Login Dao, this has the implementation that allow admin to login**/
public class AdminLoginDao {
	//attributes
	 static Connection connection;
	 static PreparedStatement pst;
	 static ResultSet rs;
	 static PrintWriter out;
/*AdminLoginValidation method*/
 public static boolean AdminLoginValidation(String name,String pass){
	boolean status=false;
	  try {
		  //connect to DB 
		  connection = DBConnectionManager.getConnection();
          //query
          pst = connection.prepareStatement("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME= ? AND PASSWORD= ? AND ROLE= ?");
          pst.setString(1, name);
          pst.setString(2, pass);
          pst.setString(3, "admin");
          rs = pst.executeQuery();
          status = rs.next();
      } catch (Exception e) {
          if (connection == null) {
              System.out.println("Connection not successful.");
          }//end if
          e.printStackTrace();
      }//end catch up
	   //some exception handling
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
      }	
 }//end AdminLoginaDao
