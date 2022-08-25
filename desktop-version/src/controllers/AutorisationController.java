package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorisationController {

    @FXML
    TextField txtCin;
 
    @FXML
    TextField txtAuto;

    private String cin=null;
    private String autorisation=null;

    private String checkQuery=null;
    private String SQLQuery=null;
    private Connection updateConn=null;
    private PreparedStatement updateSt=null;
    private ResultSet updateRs=null;
    private Alert ualert = new Alert(Alert.AlertType.INFORMATION);


    private void getUpdateEtat(){
        cin=txtCin.getText();
        autorisation=txtAuto.getText();
    }

    private boolean validateUpdateEtat(){
        getUpdateEtat();
        if(cin.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP Entrez le CIN d'utilisateur");
            ualert.showAndWait();
            return false;
        }
        if(autorisation.isEmpty()){
            ualert.setTitle("Données incomplétes");
            ualert.setHeaderText(null);
            ualert.setContentText("SVP complétez les donnérs manquantes");
            ualert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }

    @FXML
    private void updateRequiredData() throws SQLException{
        getUpdateEtat();
        checkQuery="SELECT * FROM users WHERE cin='"+cin+"';";
        SQLQuery="UPDATE users SET isAuthorized='"+autorisation+"' WHERE cin='"+cin+"';";
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
    private void updateEtat() throws SQLException{
        boolean checkStatus=validateUpdateEtat();
        if(checkStatus){
            updateRequiredData();
        }
    }
}