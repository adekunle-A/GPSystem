package daospkg;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheckPatientHistoryDao {
	//Attributes
	static Connection connection;
	static PreparedStatement pst,pst2;
	static ResultSet rs,rs2;
	static PrintWriter out;
	public ArrayList<PatientHealthRecord>  PatientMedicalRecord(String patientusername) {
	   ArrayList<PatientHealthRecord> searchResults = new ArrayList<PatientHealthRecord>();
	   PatientHealthRecord record = new PatientHealthRecord();
		try {
			//connect to DB 
			connection = DBConnectionManager.getConnection();
			//query
			String query =  "SELECT * FROM USERS WHERE USERNAME= ?";
			String query2 =  "SELECT * FROM PATIENTHEALTHRECORD WHERE PATIENTID= ?";
			pst = connection.prepareStatement(query);
			pst.setString(1, patientusername);
			rs = pst.executeQuery();
			  if(rs.next()){ 
				  	System.out.println(rs.getString(1));
					pst2 = connection.prepareStatement(query2);
					pst2.setString(1, rs.getString(1));
					rs2 = pst2.executeQuery();
					  while(rs2.next()){
						  record.setPatienthealthrecordid(rs2.getInt(1));
						  record.setAppointmentdate(rs2.getString(2));
						  record.setSymptoms(rs2.getString(3));
						  record.setDisease(rs2.getString(4));
						  record.setTreatment(rs2.getString(5));
						  record.setDoctorname(rs2.getString(6));
						  record.setPatientname(rs.getString(3)+ " " + rs.getString(4));
						  searchResults.add(record);
					  }//end while
			 }else{
				 searchResults= null;
				 System.out.println("No medical records found");
		 }//end else
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
		return searchResults;
	}
}//end class CheckPatientHistoryDao
