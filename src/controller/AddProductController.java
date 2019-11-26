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

public class AddProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    
    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;

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
    private TableView<Part> associatedPartTableView;

    @FXML
    private TableColumn<Part, Integer> aPartIdCol;

    @FXML
    private TableColumn<Part, String> aPartNameCol;

    @FXML
    private TableColumn<Part, Integer> aPartInvCol;

    @FXML
    private TableColumn<Part, Double> aPartPriceCol;
    

    //Adds part to temporary list and displays it in the Associated parts table
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
         
        InventoryList.addTempPart(partsTableView.getSelectionModel().getSelectedItem());

        associatedPartTableView.setItems(InventoryList.getTempList());
        aPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        aPartInvCol.setCellValueFactory(new PropertyValueFactory<>("inv"));
        aPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }

    
    //Adds the product and checks if the input has correct data type then returns to main screen.
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        
        try{
            
            int id = 0;
            String name = nameTxt.getText();
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

                InventoryList.addProduct(new Product(id, name, price, inv, min, max));

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

    
    //Removes the part from the temporary list after confirmation.
    @FXML
    void onActionDeletePart(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the selected part, continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            
            InventoryList.deleteTempPart(associatedPartTableView.getSelectionModel().getSelectedItem());
            
        }

    }

    
    //Returns to Main screen after confirmation.
    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data will not be saved, continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show(); 
        }

    }

    
    //Displays the part that was entered in the text field.
    @FXML
    void onActionSearchProduct(ActionEvent event) {
        
        partsTableView.getSelectionModel().select(InventoryList.lookupPart(searchTxt.getText().toLowerCase()));
        
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
