package inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.InventoryList;
import model.Outsourced;
import model.Product;

/**
 *
 * @author krish
 */

public class Inventory extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        //Creates temporary parts on startup
        Outsourced part1 = new Outsourced(5, "Engine", 2500.00, 8, 0, 10, "BMW");
        InHouse part2 = new InHouse(3, "Frame", 1000.00, 7, 0, 10, 1);

        
        //Creates temporary products on startup
        Product product1 = new Product(3, "Corolla", 15000.00, 5, 0, 10);
        Product product2 = new Product(8, "RAV 4", 20000.00, 3, 0, 10);

        
        //adds parts in the Parts list
        InventoryList.addPart(part1);
        InventoryList.addPart(part2);

        
        //adds products in the Products list
        InventoryList.addProduct(product1);
        InventoryList.addProduct(product2);

        //Associates parts to the Products
        product1.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);

        
        launch(args);
    }
    
}
