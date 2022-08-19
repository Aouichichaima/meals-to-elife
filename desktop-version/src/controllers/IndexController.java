package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IndexController {
    @FXML
    private Label label01;

    private Stage stage;
    private Scene scene;
    private Parent root;


    
    public void goToInscription(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
