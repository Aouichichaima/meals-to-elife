package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    

    public void signupHandler() {
        System.out.println("signing up...");
        User user = new User(cinTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText(), emailTextField.getText(), passwordTextField.getText());
        
        System.out.println(user);

    }


}
