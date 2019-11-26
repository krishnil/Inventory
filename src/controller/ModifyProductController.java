package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InventoryList;
import model.Part;
import model.Product;

//FXML Controller class

public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Part> aPartsTableView;

    @FXML
    private TableColumn<Part, Integer> aPartIdCol;

    @FXML
    private TableColumn<Part, String> aPartNameCol;

    @FXML
    private TableColumn<Part, Integer> aPartInvCol;

    @FXML
    private TableColumn<Part, Double> aPartPriceCol;

    
    //Adds the selected part to the temporary list.
    @FXML
    void onActionAddPart(ActionEvent event) {
       
        InventoryList.addTempPart(partsTableView.getSelectionModel().getSelectedItem());
        
    }

    
    //Adds the product and checks for incorrect data types.
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        
        try{
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText().toLowerCase();
            int inv = Integer.parseInt(invTxt.getText());
            double price = Double.parseDouble(priceTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            
            if(min > max){      //Checks if minimum value is less than maximum value.
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Minimum value cannot exceed Maximum value.");
                alert.showAndWait();
            
            }
            
            else{

                InventoryList.updateProduct(id, new Product(id, name, price, inv, min, max));

                InventoryList.lookupProduct(name.toLowerCase()).clearAssociatedPart();

                for(Part part : InventoryList.getTempList()){

                    (InventoryList.lookupProduct(name.toLowerCase())).addAssociatedPart(part);

                }

                InventoryList.clearTempList();

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            
            }
        
        }
        
        catch(NumberFormatException e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
            
        }

    }

    
    //Deletes the selected part after confirmation.
    @FXML
    void onActionDeletePart(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the selected part, continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            
        InventoryList.deleteTempPart(aPartsTableView.getSelectionModel().getSelectedItem());
        
        }

    }

    
    //Returns to main screen after confirmation.
    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes will not be saved, continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show(); 
        }

    }

    
    //Displays the search results.
    @FXML
    void onActionSearch(ActionEvent event) {
        
        partsTableView.getSelectionModel().select(InventoryList.lookupPart(searchTxt.getText().toLowerCase()));
        
        
    }
    
    
    //Displays the selected products information in the text fields.
    public void setProduct(Product product){
        
        idTxt.setText(String.valueOf(product.getId()));
        nameTxt.setText(product.getName());
        invTxt.setText(String.valueOf(product.getInv()));
        priceTxt.setText(String.valueOf(product.getPrice()));
        maxTxt.setText(String.valueOf(product.getMax()));
        minTxt.setText(String.valueOf(product.getMin()));
        
        InventoryList.clearTempList();
        
        for(Part part : product.getAllAssociatedParts()){
            
            InventoryList.addTempPart(part);
                
        }
        
        //Displays the associated parts in the Tableview.
        aPartsTableView.setItems(InventoryList.getTempList());
        aPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        aPartInvCol.setCellValueFactory(new PropertyValueFactory<>("inv"));
        aPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
     
        
    } 
    
 
    //Initializes the controller class.

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
       partsTableView.setItems(InventoryList.getAllParts());
       
       partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       partInvCol.setCellValueFactory(new PropertyValueFactory<>("inv"));
       partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
   
}
