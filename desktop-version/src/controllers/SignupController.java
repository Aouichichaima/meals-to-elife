package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.ServiceUser;
import models.User;

public class SignupController {

    @FXML
    private TextField cinTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private RadioButton clientRadioButton, restaurantRadioButton;



    
    // la méthode responsable a l'inscription du l'utilisateur
    public void signupHandler() {
        String cin = cinTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String typeOfUser = restaurantRadioButton.isSelected() ? "restaurant" : "client";

        // vérification des champ... (controle saisie)
        if(cin.length() != 8 || firstName.length() < 3 || lastName.length() < 2 || phone.length() < 8 || email.length() < 12 || password.length() < 5 || typeOfUser.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("merci de vérifier vos coordonnées");
            alert.show();
            return;
        }

        ServiceUser serviceUser = new ServiceUser();
        
        // vérification de l'éxictance de la cin ou de l'email dans la base de données
        Alert alert = new Alert(AlertType.WARNING);
        try {
            if(serviceUser.isCinOrEmailExist(cin, email)) {
                alert.setContentText("un compte avec la cin: "+cin+"\nou email: "+email+" existe déja");
                alert.show();
                return;
            }
        } catch (SQLException e) {
            alert.setContentText("désole un problème dans le système");
            alert.show();
        }



        User user = new User(cin, firstName, lastName, phone, email, password, typeOfUser);
        

        try {
            alert = new Alert(AlertType.CONFIRMATION);
            if(serviceUser.add(user)) {
                alert.setContentText("Votre demande de création d'un compte finit par succés \n en attendant l'acceptation des administrateurs");
                alert.show();
                this.cinTextField.setText("");
                this.firstNameTextField.setText("");
                this.lastNameTextField.setText("");
                this.phoneTextField.setText("");
                this.emailTextField.setText("");
                this.passwordTextField.setText("");
            }
        } catch (SQLException e) {
            alert.setContentText("désole un problème dans le système");
            alert.show();
        }

    }

    // changement vers l'interface de connexion
    public void goToLoginInterface(Event event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("../views/signin.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }





}