/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimaxapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dell
 */
public class Wimaxapp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane mainPane;
        mainPane = (Pane) FXMLLoader.load(Wimaxapp.class.getResource("wimax.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Application.launch(args);
    }
    
}
