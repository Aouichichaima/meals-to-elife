package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;



public class PanelAdminController 
    {
    
        private Parent root1=null;
        private Stage stage=null;
        private Scene scene;
        ActionEvent event;
        Stage dialogStage = new Stage();

        @FXML 
        private Label text;


        //Affichage de nom d'administrateur connecté
        public void displayName(String prenom) throws IOException
            {
                text.setText("BIENVENUE "+prenom);    
            }

     


        //Deconnexion
        public void goToIndex(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }




        //Accés a la page de gestion des utilisateurs inscrits
        public void goToPanel(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/UserGestion.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }




        //Accés a la page de statistiques des utilisateurs de l'application
        public void goToStatistique(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/StatistiqueUtilisateur.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }




        //Accés a la page de contact
        public void goToContact(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/sendMail.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }

    
    }
    