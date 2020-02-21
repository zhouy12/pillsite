package pillsite.controller.model.maps;

public class YourPills {
    private String name;
    private String AL;
    private String dosage;
    private String TPD;
    private String TTT;
    private String TLT;
 
    public YourPills(String name, String AL, String dosage, String TPD, String TTT, String TLT) {
        this.name = new String(name);
        this.AL = new String(AL);
        this.setDosage(new String(dosage));
        this.setTPD(new String(TPD));
        this.setTTT(new String(TTT));
        this.setTLT(new String(TLT));
    }
 
    public String getName() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }
        
    public String getAL() {
        return AL;
    }
    public void setAL(String n) {
    	AL = n;
    }

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getTPD() {
		return TPD;
	}

	public void setTPD(String tPD) {
		TPD = tPD;
	}

	public String getTTT() {
		return TTT;
	}

	public void setTTT(String tTT) {
		TTT = tTT;
	}

	public String getTLT() {
		return TLT;
	}

	public void setTLT(String tLT) {
		TLT = tLT;
	}
    
        
}