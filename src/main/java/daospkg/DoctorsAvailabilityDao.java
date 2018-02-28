package daospkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorsAvailabilityDao {
	static Connection connection = null;
    static PreparedStatement pst,pst2,pst3,pst4;
    static ResultSet rs,rs2,rs3;
    static boolean status=false;
	 public static Boolean AddDoctorsAvailability (DocInfo getInfo) {
	    String start,end;
		String[] slots;
		//connect to DB 
		connection = DBConnectionManager.getConnection();
		//queries
	    String query = "SELECT USERID FROM USERS WHERE USERNAME= ?";
		String query2 = "SELECT * FROM DOCTORS WHERE USERS_USERID= ?";
		String query3 = "INSERT INTO DOCTORSAVAILABILITY(APPOINTMENTDATE, "
				+ "STARTTIME, ENDTIME,STATUS,DOCTORS_DOCTORID) "
				+ "VALUES(?,?,?,?,?)";
		try {
			System.out.println(getInfo.getUsername());
			pst = connection.prepareStatement(query);
			pst.setString(1, getInfo.getUsername());
	        rs = pst.executeQuery();
	        if(rs.next()) {
	        		pst2 = connection.prepareStatement(query2);
				pst2.setString(1, rs.getString(1));
		        rs2 = pst2.executeQuery();
		        if(rs2.next()){
		        	//loop through the time slot array
					for(int i=0; i < getInfo.getTimeslot().length; i++){
						slots = getInfo.getTimeslot()[i].split(";");
						start = slots[0]; //startTime
					    end = slots[1]; //endTime
					    System.out.println("start" + start);
					    System.out.println("end" + end);
					    try {
							pst3 = connection.prepareStatement(query3);
							pst3.setString(1, getInfo.getAvaildatepicker());
			        		  	pst3.setString(2, start);
			        		  	pst3.setString(3, end);
			        		  	pst3.setInt(4, 1);
			 				pst3.setInt(5, rs2.getInt(1));
			 				pst3.execute();
			 				 	System.out.println("availability added successfully");
			 				 	status=true;	
						    }catch(Exception e1){
				 					 System.out.println("Query3 Error: " + e1);
				 		 }//end catch
						}//end for
		        }//end if
	        }//end if
	        else{
       		 System.out.println("Error2");
       	 }//end
		}catch(Exception e) {
			e.printStackTrace();
		}//end catch
		return status;
	 }
 
}//end class DoctorsAvailabilityDao
