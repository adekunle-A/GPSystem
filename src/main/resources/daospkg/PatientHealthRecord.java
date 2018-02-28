package daospkg;

public class PatientHealthRecord {
    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int patienthealthrecordid;
    private String appointmentdate;
    private String symptoms;
    private String disease;
    private String treatment;
    private String doctorname;
    private String doctorid;
    private String patientid;
    private String patientname;

    public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	/** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public PatientHealthRecord () {

    }

    public PatientHealthRecord (int patienthealthrecordidIn) {

          this.patienthealthrecordid = patienthealthrecordidIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getPatienthealthrecordid() {
          return this.patienthealthrecordid;
    }
    public void setPatienthealthrecordid(int patienthealthrecordidIn) {
          this.patienthealthrecordid = patienthealthrecordidIn;
    }

    public String getAppointmentdate() {
          return this.appointmentdate;
    }
    public void setAppointmentdate(String appointmentdateIn) {
          this.appointmentdate = appointmentdateIn;
    }

    public String getSymptoms() {
          return this.symptoms;
    }
    public void setSymptoms(String symptomsIn) {
          this.symptoms = symptomsIn;
    }

    public String getDisease() {
          return this.disease;
    }
    public void setDisease(String diseaseIn) {
          this.disease = diseaseIn;
    }

    public String getTreatment() {
          return this.treatment;
    }
    public void setTreatment(String treatmentIn) {
          this.treatment = treatmentIn;
    }

    public String getDoctorname() {
          return this.doctorname;
    }
    public void setDoctorname(String doctornameIn) {
          this.doctorname = doctornameIn;
    }

    public String getDoctorid() {
          return this.doctorid;
    }
    public void setDoctorid(String doctoridIn) {
          this.doctorid = doctoridIn;
    }

    public String getPatientid() {
          return this.patientid;
    }
    public void setPatientid(String patientidIn) {
          this.patientid = patientidIn;
    }
}