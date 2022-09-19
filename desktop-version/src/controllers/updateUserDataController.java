package controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.ServiceUser;
import models.User;

public class updateUserDataController {


    @FXML
    private TextField NomTextField;
    @FXML
    private TextField PrenomTextField;
    @FXML
    private TextField CinTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField newPasswordTextFiled;
    @FXML
    private TextField phoneTextFiled;

    private User user;
    private User updatedUser;
    private int userId;

    private ServiceUser serviceUser = new ServiceUser();
    
    
    public void setUserId(int userId) {
        this.userId = userId;
        this.render();
    }

    public void initialize() {
        // this.setUserId(4); just pour tester l'interface sans connexion...
    }


    public void updateUserDataHandler() {
        System.out.println("update user data...");

        this.updatedUser = new User(this.CinTextField.getText(), this.userId, this.NomTextField.getText(), this.PrenomTextField.getText(), this.phoneTextFiled.getText(), this.EmailTextField.getText(), this.newPasswordTextFiled.getText());

        try {
            serviceUser.update(updatedUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void render() {
        try {
            
            user = serviceUser.findById(this.userId);
            this.NomTextField.setText(user.getFirstName());
            this.PrenomTextField.setText(user.getLastName());
            this.CinTextField.setText(user.getCin());
            this.EmailTextField.setText(user.getEmail());
            this.phoneTextFiled.setText(user.getPhone());


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void retour(ActionEvent event) {

        System.out.println("retour à la page");
        

    }

    public void returnInfo(int userId, String interfaceName) {
        
    }
    @FXML
   private void updateUserDataHandler(ActionEvent event){
    if (NomTextField.getText().isEmpty() ||NomTextField.getText().isEmpty() ||PrenomTextField.getText().isEmpty()||CinTextField.getText().isEmpty()||EmailTextField.getText().isEmpty()||newPasswordTextFiled.getText().isEmpty()||phoneTextFiled.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "vous devez remplir tous les  champs !");
    }
   
        return;
    }

   }



