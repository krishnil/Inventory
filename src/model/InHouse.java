package model;


//Inhouse Class
public class InHouse extends Part{
    
    private int machineId;
    
    //Constructor
    public InHouse(int id, String name, double price, int inv, int min, int max, int machineId){
        
        super(id, name, price, inv, min, max);
        this.machineId = machineId;
        
    }
    
    //Getter
    public int getMachineId() {
        return machineId;
    }
    
    //Setter
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
    
}
