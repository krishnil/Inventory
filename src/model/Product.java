package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


//Product class
public class Product {
    
    private int id;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList(); //Holds Associated parts
    private static int idNum;
    
    //Constructor
    public Product(int id, String name, double price, int inv, int min, int max){
        
        this.id = idNum++;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
        
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = idNum++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public void setPrice(int max){
        this.price = max;
    }
    
    public void addAssociatedPart(Part part){    //adds associated parts in the list
        associatedParts.add(part);
    }
    
    public void deleteAssociatedPart(Part part){    //removes associated part
        associatedParts.remove(part);
    }
    
    public void clearAssociatedPart(){       //clears the associated parts list
        associatedParts.clear();
    }
    
    public ObservableList<Part> getAllAssociatedParts(){     //returns the associated parts list
        return associatedParts;
    }
    
}
