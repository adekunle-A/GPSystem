package daospkg;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewDoctorDao {
	//Attributes
	static Connection connection;
	static PreparedStatement pst;
	static ResultSet rs;
	static PrintWriter out;
	public static DocInfo GetDoctorInfo(String getDocName) {
	   boolean status = false;
	   DocInfo setdocifo = new DocInfo();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			//query
			System.out.println(getDocName);
			String query =  "SELECT * FROM USERS WHERE USERNAME= ? AND ROLE= ?";
			pst = connection.prepareStatement(query);
			pst.setString(1, getDocName);
			pst.setString(2, "doctor");
			rs = pst.executeQuery();
			 if(rs.next()) {
				setdocifo.setUsername(rs.getString(2));
				setdocifo.setEmail(rs.getString(5) );
				setdocifo.setFname(rs.getString(3));
				setdocifo.setLname(rs.getString(4));
				setdocifo.setAddress(rs.getString(6));
				setdocifo.setDob(rs.getString(9));
				setdocifo.setPhone(rs.getString(11));
				setdocifo.setGender(rs.getString(10));
				status = true;			
				}else {
					setdocifo = null;
				}
			System.out.println(setdocifo.getAddress());
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
		    };
		    try {
		    	if (pst != null) 
		    		pst.close(); 
		    	} catch (Exception e) {
		    		System.out.println("statment closing error: " + e);
		    	};
		    try { 
		    	if (connection != null) 
		    		connection.close(); 
		    	} catch (Exception e) {
		    		System.out.println("connection closing error: " + e);
		    	};
		}//end finally
		return setdocifo;
   }//end  GetDoctorInfo
}//end class ViewDoctorDao
