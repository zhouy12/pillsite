package pillsite.controller.model.maps;

public class Pills {
    private String name;
    private String id;
    private String type;
 
    public Pills(String name, String id, String type) {
        this.name = new String(name);
        this.id = new String(id);
        this.type = new String(type);
    }
 
    public String getName() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }
        
    public String getId() {
        return id;
    }
    public void setId(String n) {
        id = n;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String n) {
        type = n;
    }
        
}