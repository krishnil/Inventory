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

public class MainScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    
    @FXML
    private TextField searchProductTxt;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField searchPartTxt;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    
    //Switches to add part screen.
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    
    //Switches to add product screen.
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
        InventoryList.clearTempList();

    }

    
    //Deletes the selected part after confirmation.
    @FXML
    void onActionDeletePart(ActionEvent event) {
        

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the selected part, continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            
           InventoryList.deletePart(partTableView.getSelectionModel().getSelectedItem()); 
        
        }
           
    }

    
    //Deletes the selected product after confirmation.
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the selected product, continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            
           InventoryList.deleteProduct(productTableView.getSelectionModel().getSelectedItem()); 
           
        }
        
    }

    
    //Exits the program.
    @FXML
    void onActionExit(ActionEvent event) {
        
        System.exit(0);

    }

    
    //Switches to Modify Part screen with information from the selected part.
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        
        try{
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
            loader.load();

            ModifyPartController MPController = loader.getController();
            MPController.setPart(partTableView.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        
        }
        
        catch(NullPointerException e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a Part.");
            alert.showAndWait();
            
        }

    }

    
    //Switches to Modify Product screen with information from the selected product.
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        
        try{
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            loader.load();

            ModifyProductController MPController = loader.getController();
            MPController.setProduct(productTableView.getSelectionModel().getSelectedItem());


            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        
        }
        
        catch(NullPointerException e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a Product.");
            alert.showAndWait();
            
        }

    }

    
    //Highlights the searched part.
    @FXML
    void onActionSearchPart(ActionEvent event) {
        
        partTableView.getSelectionModel().select(InventoryList.lookupPart(searchPartTxt.getText().toLowerCase()));
        
    }

    
    //Highlights the searched product.
    @FXML
    void onActionSearchProduct(ActionEvent event) {
        
        productTableView.getSelectionModel().select(InventoryList.lookupProduct(searchProductTxt.getText().toLowerCase()));

    }
   
    
    //Initializes the controller class.
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       //populates the Products Tableview. 
       productTableView.setItems(InventoryList.getAllProducts());
        
       productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       productInvCol.setCellValueFactory(new PropertyValueFactory<>("inv"));
       productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       
       
       //Populates the Parts Tableview.
       partTableView.setItems(InventoryList.getAllParts());
       
       partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       partInvCol.setCellValueFactory(new PropertyValueFactory<>("inv"));
       partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
 
    }    
    
}
