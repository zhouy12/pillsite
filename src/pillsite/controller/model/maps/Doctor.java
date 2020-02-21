package pillsite.controller.model.maps;

public class Doctor {
    private String Fname;
    private String Lname;
    private String id;
    private String hospital;
    private String department;
    private String phoneNumber;
 
    public Doctor(String Fname, String Lname, String id, String hospital, String department, String phoneNumber) {
        this.setFname(new String(Fname));
        this.setLname(new String(Lname));
        this.id = new String(id);
        this.setHospital(new String(department));
        this.setDepartment(new String(department));
        this.setPhoneNumber(new String(phoneNumber));
    }
 
        
    public String getId() {
        return id;
    }
    public void setId(String n) {
        id = n;
    }

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getFname() {
		return Fname;
	}


	public void setFname(String fname) {
		Fname = fname;
	}


	public String getLname() {
		return Lname;
	}


	public void setLname(String lname) {
		Lname = lname;
	}
        
}