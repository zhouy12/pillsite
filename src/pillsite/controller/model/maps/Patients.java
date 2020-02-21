package pillsite.controller.model.maps;

public class Patients {
    private String Fname;
    private String Lname;
    private String id;
 
 
    public Patients(String Fname, String Lname, String id) {
        this.setFname(new String(Fname));
        this.setLname(new String(Lname));
        this.id = new String(id);
       
    }
 
        
    public String getId() {
        return id;
    }
    public void setId(String n) {
        id = n;
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
