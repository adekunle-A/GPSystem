package daospkg;
//This is a getter and setter class for getting doctor information
public class DocInfo {
	//attributes
	 private String username,fname,lname,email,address,password,confirmpassword,dob,gender,role,start,end,phone,docCategory,appdate,disease,treatment,bookingid,symptoms,availdatepicker,prescriptionStatus;
	
	 public String getPrescriptionStatus() {
		return prescriptionStatus;
	}

	public void setPrescriptionStatus(String prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getAvaildatepicker() {
		return availdatepicker;
	}//end getAvaldatepicker

	public void setAvaildatepicker(String avaldatepicker) {
		this.availdatepicker = avaldatepicker;
	}//enc setAvaildatepicker
	String[] timeslot;
	
	 public String[] getTimeslot() {
		return timeslot;
	}//end getTimeslot

	public void setTimeslot(String[] timeslot2) {
		this.timeslot = timeslot2;
	}//end setTimeslot

	public DocInfo () {

	 }//end DocInfo constructor
	 
	 public DocInfo (String username) {

         this.username = username;
	 }//end DocInfo
	 public String getAppdate() {
		return appdate;
	}//end getAppdate
	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}//end setAppdate
	public String getDisease() {
		return disease;
	}//end getDisease
	public void setDisease(String disease) {
		this.disease = disease;
	}//end setDisease
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getDocCategory() {
		return docCategory;
	}
	public void setDocCategory(String docCategory) {
		this.docCategory = docCategory;
	}
	//getters and setters
	public String getUsername() {
		return username;
	}//end getUsername
	public void setUsername(String username) {
		this.username = username;
	}//end setUsername
	public String getFname() {
		return fname;
	}//end getFname
	public void setFname(String fname) {
		this.fname = fname;
	}//end setFname
	public String getLname() {
		return lname;
	}//end getLname
	public void setLname(String lname) {
		this.lname = lname;
	}//end setLname
	public String getEmail() {
		return email;
	}//end getEmail
	public void setEmail(String email) {
		this.email = email;
	}//end setEmail
	public String getAddress() {
		return address;
	}//end getAddress
	public void setAddress(String address) {
		this.address = address;
	}//end setAddress
	public String getPassword() {
		return password;
	}//end getPassword
	public void setPassword(String password) {
		this.password = password;
	}//end setPassword
	public String getConfirmpassword() {
		return confirmpassword;
	}//end getConfirmpassword
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}//end setConfirmpassword
	public String getDob() {
		return dob;
	}//end getDob
	public void setDob(String dob) {
		this.dob = dob;
	}//end setDob
	public String getGender() {
		return gender;
	}//end getGender
	public void setGender(String gender) {
		this.gender = gender;
	}//end setGender
	public String getRole() {
		return role;
	}//end getRole
	public void setRole(String role) {
		this.role = role;
	}//end setRole
	public String getPhone() {
		return phone;
	}//end get getPhone
	public void setPhone(String phone) {
		this.phone = phone;
	}//end setPhone
}//end Docinfo class
