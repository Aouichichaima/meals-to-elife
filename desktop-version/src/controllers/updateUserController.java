package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateUserController {

    @FXML
    TextField txtCin;
    @FXML
    TextField txtNom;
    @FXML
    TextField txtPrenom;
    @FXML
    TextField txtMail;
    @FXML
    TextField txtTel;
    @FXML
    TextField txtAuto;

    private String cin=null;
    private String nom=null;
    private String prenom=null;
    private String mail=null;
    private String tel=null;
    private String autorisation=null;

    private String checkQuery=null;
    private String SQLQuery=null;
    private Connection updateConn=null;
    private PreparedStatement updateSt=null;
    private ResultSet updateRs=null;
    private Alert ualert = new Alert(Alert.AlertType.INFORMATION);


    private void getUpdateDetails(){
        cin=txtCin.getText();
        nom=txtNom.getText();
        prenom=txtPrenom.getText();
        mail=txtMail.getText();
        tel=txtTel.getText();
        autorisation=txtAuto.getText();
    }

    private boolean validateUpdateDetails(){
        getUpdateDetails();
        if(cin.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP Entrez le CIN d'utilisateur");
            ualert.showAndWait();
            return false;
        }

        if(nom.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP Entrez le nom d'utilisateur");
            ualert.showAndWait();
            return false;
        }

        if(prenom.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP Entrez le prénom d'utilisateur");
            ualert.showAndWait();
            return false;
        }

        if(mail.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP Entrez l'adresse email d'utilisateur");
            ualert.showAndWait();
            return false;
        }

        if(tel.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP Entrez le numéro de tel d'utilisateur");
            ualert.showAndWait();
            return false;
        }

        if(autorisation.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP confirmez si l'utilisateur est autorisé ou non");
            ualert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }

    @FXML
    private void updateRequiredData() throws SQLException{
        getUpdateDetails();
        checkQuery="SELECT * FROM users WHERE cin='"+cin+"';";
        SQLQuery="UPDATE users SET last_name='"+nom+"', first_name='"+prenom+"' , email='"+mail+"' , phone='"+tel+"', isAuthorized='"+autorisation+"' WHERE cin='"+cin+"';";
        try {
            updateConn = DataSource.getInstance().getConnection();
            updateRs = updateConn.createStatement().executeQuery(checkQuery);
            if(updateRs.next()){
                updateSt = updateConn.prepareStatement(SQLQuery);
                updateSt.executeUpdate();
                ualert.setTitle("Mise à jour avec succés");
                ualert.setHeaderText(null);
                ualert.setContentText("Modification des coordonnées de l'utilisateur avec succés");
                ualert.showAndWait();
            }
            else {
                ualert.setTitle("Echec de mise à jour");
                ualert.setHeaderText(null);
                ualert.setContentText("Impossible de modifier les données");
                ualert.showAndWait();
            }
        }catch (SQLException e){
            e.printStackTrace();}
        /* }finally {
            if(updateSt!=null){
                updateSt.close();
            }
            if (updateRs!=null){
                updateRs.close();
            }
            if (updateConn!=null){
                updateConn.close();
            }
        }*/
    }

    @FXML
    private void updateDetails() throws SQLException{
        boolean checkStatus=validateUpdateDetails();
        if(checkStatus){
            updateRequiredData();
        }
    }
}