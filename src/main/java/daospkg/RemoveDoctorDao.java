package daospkg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RemoveDoctorDao {
		//Attributes
		static Connection connection;
		static PreparedStatement pst,pst2;
		static ResultSet rs;
		
	public static boolean RemoveDoctor(String doctorUsername){
		   boolean status = false;
		   try {
				//connect to DB 
				connection = DBConnectionManager.getConnection();
				//query for the database
				String query = "SELECT USERID FROM USERS WHERE USERNAME= ? AND ROLE= ?";
				String query2 = "DELETE FROM USERS WHERE USERID=?";
				//execute query
				 pst = connection.prepareStatement(query);
				 pst.setString(1, doctorUsername);
				 pst.setString(2, "doctor");
				 rs = pst.executeQuery();
				//gets the doctor id that matches the above and deletes
				 if(rs.next()) {
					 System.out.println(">>>>>>"+status);
					 pst2 = connection.prepareStatement(query2);
					 System.out.println("userid "+rs.getInt(1));
					 pst2.setInt(1, rs.getInt(1));
					//execute the prepared statement
					 pst2.executeUpdate();
					 status =true;
				 }
				 System.out.println(status);
			}catch(Exception ex) {
				System.out.println(ex);
			}//end catch
			finally {
		    try { 
		    	if (rs != null) 
		    		rs.close(); 
		    } catch (Exception e) {
		    	  System.out.println("result closing error: " + e);
		    }
		    try {
		    	if (pst != null) 
		    		pst.close(); 
		    	} catch (Exception e) {
		    		System.out.println("statment closing error: " + e);
		    	}
		    try { 
		    	if (connection != null) 
		    		connection.close(); 
		    	} catch (Exception e) {
		    		System.out.println("connection closing error: " + e);
		    	}
		}//end finally
			return status;
	   }//end  GetDoctorInfo
}//end class RemoveDoctorDao
