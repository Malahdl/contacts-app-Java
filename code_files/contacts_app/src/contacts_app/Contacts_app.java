/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts_app;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author malah
 */
public class Contacts_app extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml"));
        Scene scene = new Scene(loader.load());
        
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
