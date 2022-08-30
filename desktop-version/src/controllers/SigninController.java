package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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



public class SigninController {
    
    @FXML
    private TextField textEmail;
    
    @FXML
    private PasswordField textPassword;
    
    Stage dialogStage = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    
    public SigninController() {
        connection = DataSource.getInstance().getConnection();
    }
    
    
    public void loginAction(ActionEvent event) {
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
            }
            else {

                int id = resultSet.getInt(1);
                String typeOfUser = resultSet.getString(8);
                infoBox("Connexion réussie",null,"Success" );

                switch(typeOfUser) {
                    case "admin" :
                        loader = new FXMLLoader(getClass().getResource("../views/PanelAdmin.fxml"));
                        root = loader.load();
                    break;
                    case "restaurant" :
                        loader = new FXMLLoader(getClass().getResource("../views/customerOrdersList.fxml"));
                        root = loader.load();
                        CustomerOrdersController ordersController = loader.getController();
                        ordersController.setRestaurantManagerId(id);

                    break;
                    case "client" :
                        loader = new FXMLLoader(getClass().getResource("../views/Clientspace.fxml"));
                        root = loader.load();
                    break;
                }


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
    
}