package daospkg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;
/*DAO to add doctor to the system*/
public class AddDoctorDao {
	//attributes
		 static Connection connection;
		 static PreparedStatement pst,pst2,pst3;
		 static ResultSet rs;
		 static boolean status=false;
		 private static String salt = BCrypt.gensalt(8);
		 public static Boolean DoctorRegistration(DocInfo getDoctorInfo) {
			System.out.println(getDoctorInfo);
			System.out.println(">>" + getDoctorInfo.getFname());
			System.out.println(">>"+getDoctorInfo.getAddress());
			  try {
				  //connect to DB 
				  connection = DBConnectionManager.getConnection();
		          //query
					String insertUSERS = "INSERT INTO USERS"
							+ "(USERNAME, FNAME, LNAME, EMAIL, ADDRESS, PASSWORD, ROLE, DOB, GENDER,PHONE,SALT) VALUES"
							+ "(?,?,?,?,?,?,?,?,?,?,?)";
					String query = "SELECT USERID FROM USERS WHERE USERNAME=?";
					String query2 = "INSERT INTO DOCTORS (USERS_USERID) VALUES (?)";
					
		          System.out.println(getDoctorInfo.getPassword());
		          System.out.println(getDoctorInfo.getConfirmpassword());
		          String hashedPassword = BCrypt.hashpw(getDoctorInfo.getPassword(),salt);
		          System.out.println("Add doctor salt >> "+ salt);
		          System.out.println("Add doctor hashedpassword "+ hashedPassword);
		          System.out.println(getDoctorInfo.getConfirmpassword());
		          if(getDoctorInfo.getPassword().equals(getDoctorInfo.getConfirmpassword())){
						pst = connection.prepareStatement(insertUSERS);
						pst.setString(1, getDoctorInfo.getUsername());
				        pst.setString(2, getDoctorInfo.getFname());
				        pst.setString(3, getDoctorInfo.getLname());
				        pst.setString(4, getDoctorInfo.getEmail());
				        pst.setString(5, getDoctorInfo.getAddress());
				        pst.setString(6, getDoctorInfo.getPassword());
				        pst.setString(7, getDoctorInfo.getRole());
				        pst.setString(8, getDoctorInfo.getDob());
				        pst.setString(9, getDoctorInfo.getGender());
				        pst.setString(10, getDoctorInfo.getPhone());
				        pst.setString(11,salt);
				        //execute insert SQL statement
				        pst.executeUpdate();
				        System.out.println("Insert made successfully");
				      //gets the user id so that it can be insert in the doctors table as a foreign key
				         pst2 = connection.prepareStatement(query);
						 pst2.setString(1, getDoctorInfo.getUsername());
						 System.out.println(">>>>io" + getDoctorInfo.getUsername());
				         rs = pst2.executeQuery(); 
					      if(rs.next()) {
					         	//insert the information into the doctors table
						         pst3 = connection.prepareStatement(query2);
								 pst3.setInt(1, rs.getInt(1));
						         pst3.executeUpdate();
					        }
					        status = true;
					        System.out.println("DoctorRegistrationDAO" + status);
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
	}//end class AddDoctor Dao
