package controllers;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    @FXML
    private Label inscriptionMessageLabel;
    

    public void signupHandler() {
        String cin = cinTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String typeOfUser = restaurantRadioButton.isSelected() ? restaurantRadioButton.getText() : clientRadioButton.getText();

        // vérification des champ...
        if(cin.length() != 8 || firstName.length() < 3 || lastName.length() < 2 || phone.length() < 8 || email.length() < 12 || password.length() < 5 || typeOfUser.isEmpty()) {
            this.inscriptionMessageLabel.setText("Merci de vérifier vos informations");
            return;
        }

        ServiceUser serviceUser = new ServiceUser();
        
        // vérification de l'éxictance de la cin ou de l'email dans la base de données
        try {
            if(serviceUser.isCinOrEmailExist(cin, email)) {
                this.inscriptionMessageLabel.setText("un compte avec la cin: "+cin+"\nou email: "+email+" existe déja");
                return;
            }
        } catch (SQLException e) {
            this.inscriptionMessageLabel.setText("désole un problème dans le système");
        }



        User user = new User(cin, firstName, lastName, phone, email, password, typeOfUser);
        

        try {
            if(serviceUser.add(user)) {
                this.inscriptionMessageLabel.setText("Votre demande de création d'un compte finit par succés \n en attendant l'acceptation des administrateurs");
            }
        } catch (SQLException e) {
            this.inscriptionMessageLabel.setText("désole un problème dans le système");
            System.out.println(e);
        }

    }





}