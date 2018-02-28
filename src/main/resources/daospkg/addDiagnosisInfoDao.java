package daospkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
/**addDiagnosisInfoDao**/
public class addDiagnosisInfoDao {
	static Connection connection = null;
    static PreparedStatement pst,pst2,pst3,pst4,pst5,pst6;
    static ResultSet rs,rs2,rs3,rs4,rs5;
    static boolean status=false;
	public static Boolean AddDignosisInfo(DocInfo getDiagnosisInfo) {	
		  try {
			  Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(getDiagnosisInfo.getAppdate());
			  SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		      String appDate = DATE_FORMAT.format(date1);
			//connect to DB 
			connection = DBConnectionManager.getConnection();
	        //queries
	        String query ="SELECT USERID,LNAME,FNAME FROM USERS WHERE USERNAME=?";
	  		String query2 ="SELECT USERID FROM USERS WHERE USERNAME=?";
	  		String getDocIdFromDocTable ="SELECT DOCTORID FROM DOCTORS WHERE users_userid=?";
	  		String queryApps ="SELECT * FROM APPOINTMENTS WHERE doctors_doctorid=? AND patients_patientid=? AND appointmentdate=?";
	  		String query3 ="INSERT INTO PATIENTHEALTHRECORD (APPOINTMENTDATE, SYMPTOMS, DISEASE, TREATMENT, DOCTORNAME,DOCTORID,PATIENTID)"
	  				+ "VALUES(?,?,?,?,?,?,?)";
	  		String query4 ="INSERT INTO prescriptions (APPDATE, DRUG, STATUS, PATIENTID)"
	  				+ "VALUES(?,?,?,?)";
	  		
	  		pst = connection.prepareStatement(query);
		    pst.setString(1, getDiagnosisInfo.getUsername());
		    rs = pst.executeQuery();
		    if(rs.next()) {
		    		pst2 = connection.prepareStatement(query2);
			    pst2.setString(1, getDiagnosisInfo.getBookingid());
			    rs2 = pst2.executeQuery();
			    //get patient id and insert all the info into the patient health record
			    if(rs2.next()) {
				    	pst5 = connection.prepareStatement(getDocIdFromDocTable);
					pst5.setInt(1, rs.getInt(1));
					rs4 = pst5.executeQuery();
					if(rs4.next()) {
			    		pst6 = connection.prepareStatement(queryApps);
				    pst6.setInt(1, rs4.getInt(1));
				    pst6.setInt(2, rs2.getInt(1));
				    pst6.setString(3, appDate);
				    rs5 = pst6.executeQuery();
				    System.out.println(rs4.getInt(1) +  " " +rs2.getInt(1) +" " +appDate);
				    if(rs5.next()) {
				    		pst3 = connection.prepareStatement(query3);
				    		pst3.setString(1, getDiagnosisInfo.getAppdate());
					    pst3.setString(2, getDiagnosisInfo.getSymptoms());
					    pst3.setString(3, getDiagnosisInfo.getDisease());
				        pst3.setString(4, getDiagnosisInfo.getTreatment());
						pst3.setString(5, rs.getString(2) +" "+ rs.getString(3));
						pst3.setInt(6, rs.getInt(1));
						pst3.setInt(7, rs2.getInt(1));
				        //execute insert SQL statement
				         pst3.executeUpdate();
				         //insert into the prescription table
				        pst4 = connection.prepareStatement(query4);
				        pst4.setString(1, getDiagnosisInfo.getAppdate());
				        pst4.setString(2, getDiagnosisInfo.getTreatment());
					    pst4.setString(3, getDiagnosisInfo.getPrescriptionStatus());
						pst4.setInt(4, rs2.getInt(1));
						 //execute insert SQL statement for prescription
				        pst4.executeUpdate();
				        status =true;
				     }else {
				    	 status =false;
					}//end else
				    }//end if
		    		}//end if
		    }//end if
		  }catch(Exception e) {
			  System.out.println(e);
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
		  return status;
	}//end AddDignosisInfo
}//end class addDiagnosisInfoDao
