package controllers ;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.DataSource;
import javafx.scene.Node;


public class StatistiqueUtilisateurController implements Initializable{

    @FXML
    private PieChart chart;

    
    @FXML
    Label txt;
    @FXML
    Label txt1;
    @FXML
    Label txt2;
    @FXML
    Label txt3;
    @FXML
    Label txt4;
    @FXML
    Label txt5;


    private Parent root1=null;
    private Stage stage=null;
    private Scene scene;
    ActionEvent event;
    Stage dialogStage = new Stage();


    Connection con;
    PreparedStatement pst;
    ResultSet rs;


 


    //Connexion a la base de données
    public void Connect()
        {
            con = DataSource.getInstance().getConnection();
        }  



    //Calcul de nombre total des utilisateurs inscrits
    public static int count(String col)
        {
             try
                {
                    PreparedStatement pst=DataSource.getInstance().getConnection().prepareStatement("Select count("+col+") from users");
                    ResultSet rs= pst.executeQuery();

                    if(rs.next())
                        {
                            return Integer.parseInt(rs.getString(1));
                        }
                } 
            catch(Exception e)
                {}
            return 0;
        }



    //Calcul de nombre des utilisateurs autorisés
    public static int countAuto(String col)
        {
            try
                {
                    PreparedStatement pst=DataSource.getInstance().getConnection().prepareStatement("Select count("+col+") from users WHERE `isAuthorized`='1'");
                    ResultSet rs= pst.executeQuery();

                    if(rs.next())
                        {
                            return Integer.parseInt(rs.getString(1));
                        }
                } 
            catch(Exception e)
                {}
            return 0;
        }




    //Calcul de nombre des utilisateurs non autorisés
    public static int countNoAuto(String col)
        {
            try
                {
                    PreparedStatement pst=DataSource.getInstance().getConnection().prepareStatement("Select count("+col+") from users WHERE `isAuthorized`='0'");
                    ResultSet rs= pst.executeQuery();

                    if(rs.next())
                        {
                            return Integer.parseInt(rs.getString(1));
                        }
                } 
            catch(Exception e)
                {}
            return 0;
        }




    //Calcul de nombre des administrateurs
    public static int countAdmin(String col)
        {
            try
                {
                    PreparedStatement pst=DataSource.getInstance().getConnection().prepareStatement("Select count("+col+") from users WHERE `type_of_user`='admin'");
                    ResultSet rs= pst.executeQuery();

                    if(rs.next())
                        {
                            return Integer.parseInt(rs.getString(1));
                        }
                } 
            catch(Exception e)
                {}
            return 0;
        }





    //Calcul de nombre des clients
    public static int countClient(String col)
        {
            try
                {
                    PreparedStatement pst=DataSource.getInstance().getConnection().prepareStatement("Select count("+col+") from users WHERE `type_of_user`='client'");
                    ResultSet rs= pst.executeQuery();

                    if(rs.next())
                        {
                            return Integer.parseInt(rs.getString(1));
                        }
                } 
            catch(Exception e)
                {}
            return 0;
        }




    //Calcul de nombre des gérants des restaurants
    public static int countRestaurant(String col)  
        {
            try
                {
                    PreparedStatement pst=DataSource.getInstance().getConnection().prepareStatement("Select count("+col+") from users WHERE `type_of_user`='restaurant'");
                    ResultSet rs= pst.executeQuery();

                    if(rs.next())
                        {
                            return Integer.parseInt(rs.getString(1));
                        }
                } 
            catch(Exception e)
                {}
            return 0;
        }




    //Affichage des résultas(les nombres)
    @Override
    public void initialize(URL location, ResourceBundle resources) 
        {
            txt.setText("UTILISATEURS : "+count( "id"));
            txt1.setText("AUTORISÉS : " +countAuto( "id")+"");
            txt2.setText("NON AUTORISÉS : "+countNoAuto( "id")+"");
            txt3.setText("ADMINISTRATEURS : "+countAdmin( "id")+"");
            txt4.setText("CLIENTS : "+countClient( "id")+"");
            txt5.setText("RESTAURANTS : "+countRestaurant( "id")+"");



            ObservableList <PieChart.Data> pData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Utilisateurs : "+count( "id"), count("id")),
                    new PieChart.Data("Autorisés : "+countAuto("id") , countAuto("id")),
                    new PieChart.Data("Non autorisés : " +countNoAuto("id"), countNoAuto("id")),
                    new PieChart.Data("Administrateurs : " +countAdmin("id"), countAdmin("id")),
                    new PieChart.Data("Clients : " +countClient("id"), countClient("id")),
                    new PieChart.Data("Restaurants : " +countRestaurant("id"), countRestaurant("id")));

                chart.setData(pData);
        }





    //Root : Déconnexion    
    public void goToIndex(ActionEvent event) throws IOException 
        {
            root1 = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
            scene = new Scene(root1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
                        
                        
                        
    //Root : Rechargement de l'interface de gestion des utilisateurs    
    public void goToPanel(ActionEvent event) throws IOException 
        {
            root1 = FXMLLoader.load(getClass().getResource("../views/UserGestion.fxml"));
            scene = new Scene(root1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }



    //Root : Rechargement de l'interface d'accueil   
    public void goToHome(ActionEvent event) throws IOException 
        {
            root1 = FXMLLoader.load(getClass().getResource("../views/PanelAdmin.fxml"));
            scene = new Scene(root1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }



    //Root: Rechqrgement de l'interface de contact
    public void goToContact(ActionEvent event) throws IOException 
        {
            root1 = FXMLLoader.load(getClass().getResource("../views/sendMail.fxml"));
            scene = new Scene(root1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
                
}