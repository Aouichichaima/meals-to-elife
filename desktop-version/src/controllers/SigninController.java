package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DataSource;
import models.ServiceUser;



/**
 *
 * @author Bushan Sirgur
 */
public class SigninController implements Initializable {
    
    @FXML
    private TextField textEmail;
    
    @FXML
    private PasswordField textPassword;
    
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    
    public SigninController() {
        connection = DataSource.getInstance().getConnection();
    }
    
    
    public void loginAction(ActionEvent event){
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();
        String sql = "SELECT * FROM users WHERE email = ? and password = ? ";
        
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

           ServiceUser serviceUser = new ServiceUser();

            if(!(resultSet.next()) || !(serviceUser.isAuthorized(email))){
                infoBox("SVP Entrez des valides informations  ", null, "Echec");
            }else{
                infoBox("Connexion réussie",null,"Success" );
                Parent root = FXMLLoader.load(getClass().getResource("../views/PanelAdmin.fxml"));
                dialogStage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.show();   
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    
    


    public void goToInscription(ActionEvent event) throws IOException {
        
    Stage stage;
     Scene scene;
     Parent root;


        root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}