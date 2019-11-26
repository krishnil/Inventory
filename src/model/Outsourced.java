package model;


//Outsourced Class
public class Outsourced extends Part{
    
    private String companyName;
    
    //Constructor
    public Outsourced(int id, String name, double price, int inv, int min, int max, String companyName){
        
        super(id, name, price, inv, min, max);
        this.companyName = companyName;
        
    }
    
    //Getter
    public String getCompanyName() {
        return companyName;
    }
    
    //Setter
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
    
}
