package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class InventoryList {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();         //contains all parts
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();   //contains all products
    private static ObservableList<Part> tempList = FXCollections.observableArrayList();         //temporary list to hold associated parts
    private static ObservableList<Part> searchList = FXCollections.observableArrayList();       //temporary list to hold search results
    
    
    public static void addPart(Part newPart){           //adds a part
        
        allParts.add(newPart);
        
    }
    
    public static void addProduct(Product newProduct){          //adds a product
        
        allProducts.add(newProduct);
        
    }
    
    public static ObservableList<Part> getAllParts() {           //returns the part list
        
        return allParts;
        
    }
    
    public static ObservableList<Product> getAllProducts() {     //returns the product list
            
        return allProducts;
        
    }
    
    public static Part lookupPart(int partId){                   //looks for parts using int
        for(Part part : InventoryList.getAllParts()){
            if(part.getId() == partId)
                return part;        
        }
        return null;
    }
    
    public static Product lookupProduct(int productId){             //looks for products using int
        for(Product product : InventoryList.getAllProducts()){
            if(product.getId() == productId)
                return product;
        }
        return null;
    }
    
    public static Part lookupPart(String partName){                 //looks for parts using name
        for(Part part : InventoryList.getAllParts()){
            if(part.getName().toLowerCase().equals(partName))
                return part;
        }
        return null;
    }
    
    public static Product lookupProduct(String productName){            //looks for products using name
        for(Product product : InventoryList.getAllProducts()){
            if(product.getName().toLowerCase().equals(productName))
                return product;
        }
        return null;
    }
    
    public static void updatePart(int index, Part selectedPart){         //updates the parts
        
        int list = -1;
        
        for(Part part : InventoryList.getAllParts()){
            
            list++;
            
            if(part.getId() == index){
                InventoryList.getAllParts().set(list, selectedPart);
            }
        }
        
    }
    
    public static void updateProduct(int index, Product selectedProduct){       //updates the product
        
        int list = -1;
        
        for(Product product : InventoryList.getAllProducts()){
            
            list++;
            
            if(product.getId() == index){
                InventoryList.getAllProducts().set(list, selectedProduct);
            }
        }
        
    }
    
    public static void deletePart(Part selectedPart){           //removes the part from the list
        
        for(Part part : InventoryList.getAllParts()){
            
            if (part == selectedPart){
                allParts.remove(part);
                break;
            }
            
        }
        
    }
    
    public static void deleteProduct(Product selectedProduct){          //removes the product from the list
        
        for(Product product : InventoryList.getAllProducts()){
            
            if(product == selectedProduct){
                allProducts.remove(product);
                break;
            }
        }
    }
    
    public static ObservableList<Part> getTempList() {             //returns the temporary list
        
        return tempList;
        
    }
     
    public static void addTempPart(Part newPart){               //adds parts to the temporary list
        
        tempList.add(newPart);
        
    }
    
    public static void deleteTempPart(Part part){          //removes parts from the temporary list
        
        tempList.remove(part);
        
    }
    
    public static void clearTempList(){               //clears the temporary list
        
        tempList.clear();
        
    }
    
    public static ObservableList<Part> getSearchList() {            //returns the search list
        
        return searchList;
        
    }
     
    public static void addSearchPart(Part newPart){         //adds parts to the search list
        
        searchList.add(newPart);
        
    }
    
    public static void clearSearchList(){           //clears the search list
        searchList.clear();
    }
    
    
    
}
