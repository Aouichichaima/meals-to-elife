package controllers;




import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class addUserController {


    @FXML
    private TextField txtCin;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtPassword;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private String cin, nom,prenom,adresse,tel,type,password;
    private Connection InsConn=null;
    private Statement InsSt=null;
    private String SQLQuery=null;
    private Alert ialert = new Alert(Alert.AlertType.INFORMATION);


    private void getUserData(){
        cin = txtCin.getText();
        nom = txtName.getText();
        prenom = txtPrenom.getText();
        adresse = txtMail.getText();
        tel = txtTel.getText();
        type = txtType.getText();
        password = txtPassword.getText();

    }
    private boolean ValidateUserData(){
        getUserData();
        System.out.println("LOG :: Entrer dans l'étape de validation");
        
        if(cin.isEmpty()){
            System.out.println("\n Champs de NUMERO DE CIN est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }

        if(nom.isEmpty()){
            System.out.println("\n Champs de NOM est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }

        if(prenom.isEmpty()){
            System.out.println("\n Champs de PRENOM est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }


        if(adresse.isEmpty()){
            System.out.println("\n Champs d' ADRESSE EMAIL est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }


        if(tel.isEmpty()){
            System.out.println("\n Champs de NUMERO DE TEL est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }


        if(type.isEmpty()){
            System.out.println("\n Champs de TYPE est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }
        if(password.isEmpty()){
            System.out.println("\n Champs de MOT DE PASSE est vide");
            alert.setTitle("Informations incomplètes");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Ajouter les informations manquantes");
            alert.showAndWait();
            return false;
        }


        else{
            return true;
        }
    }

    private void InsertDataIntoDB() throws SQLException{

        try {
            InsConn = DataSource.getInstance().getConnection();
            InsSt=InsConn.createStatement();

            SQLQuery = "INSERT INTO users (cin, first_name, last_name, phone, email, type_of_user, password, isAuthorized) values('"+cin+"','"+prenom+"','"+nom+"','"+tel+"','"+adresse+"','"+type+"','"+password+"','"+1+"');";
            InsSt.executeUpdate(SQLQuery);
            System.out.println("\n LOG :: Nouveau utilisateur est ajouté avec succés");
        }catch (SQLException e){
            System.err.print(e);}}
      /*  }finally {
            if(InsSt!=null){
                InsSt.close();
            }
            if(InsConn!=null){
                InsConn.close();
            }
        } */

    
    public void Register() throws SQLException{
        boolean valResult = ValidateUserData();
        if(valResult){
            InsertDataIntoDB();
            ialert.setTitle("Données ajoutées avec succés");
            ialert.setHeaderText(null);
            ialert.setContentText("informations insérées");
            ialert.showAndWait();
        }
        else{
            ialert.setTitle("Echec d'insertion de données");
            ialert.setHeaderText(null);
            ialert.setContentText("Verifiez les champs");
            ialert.showAndWait();
        }
    }

}