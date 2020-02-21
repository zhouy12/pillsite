package pillsite.controller.model.maps;

public class Prescriptions {
    private String Pill;
    private String Dosage;
    private String TPD;
    private String ID;
 
 
    public Prescriptions(String Pill, String Dosage, String TPD, String Id) {
        this.setPill(new String(Pill));
        this.setDosage(new String(Dosage));
        this.TPD = new String(TPD);
        this.setId(new String(Id));
       
    }
 
        
    public String getTPD() {
        return TPD;
    }
    public void setId(String n) {
        ID = n;
    }

	public String getPill() {
		return Pill;
	}


	public void setPill(String pill) {
		Pill = pill;
	}


	public String getDosage() {
		return Dosage;
	}


	public void setDosage(String dosage) {
		Dosage = dosage;
	}
        
}

