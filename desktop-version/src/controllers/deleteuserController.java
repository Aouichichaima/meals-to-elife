package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.DataSource;

import java.sql.*;

public class deleteuserController {
    private Alert dalert = new Alert(Alert.AlertType.INFORMATION);
    private Connection delConn = null;
    private String SQLQuery = null;
    private PreparedStatement delSt = null;
    private String reqCin = null;
    private String checkQuery=null;
    private ResultSet delResultSet = null;

    @FXML
    TextField txtCin;


    @FXML
    private void DeleteRequiredData() throws SQLException {
        reqCin = txtCin.getText();
        SQLQuery = "DELETE FROM users WHERE cin='" + reqCin + "';";
        checkQuery = "SELECT * FROM users WHERE cin='"+reqCin+"';";
        if (reqCin.isEmpty()) {
            dalert.setTitle("champ vide");
            dalert.setHeaderText(null);
            dalert.setContentText(" Entrer un valide numéro de CIN pour supprimer l'utilisateur ");
            dalert.showAndWait();
        } else {
            try {
                delConn = DataSource.getInstance().getConnection();
                delResultSet = delConn.createStatement().executeQuery(checkQuery);
                if (delResultSet.next()){
                    delSt = delConn.prepareStatement(SQLQuery);
                    delSt.executeUpdate();
                    dalert.setTitle("Suppression de données avec succés");
                    dalert.setHeaderText(null);
                    dalert.setContentText("Utilisateur supprimé avec succés");
                    dalert.showAndWait();
                }
                else {
                    dalert.setTitle("Echec de suppression");
                    dalert.setHeaderText(null);
                    dalert.setContentText("Utilisateur non trouvé");
                    dalert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
        }
    }
