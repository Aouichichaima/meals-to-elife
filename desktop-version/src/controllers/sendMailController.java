package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.DataSource;
import models.User;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;



public class sendMailController implements Initializable {

    @FXML
    private Pane paneImp;


    @FXML
    private TableView<User> tabUser;
    @FXML
    private TableColumn<User, String> autoCol;
    @FXML
    private TableColumn<User, String> cinCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> nomCol;
    @FXML
    private TableColumn<User, String> prenomCol;
    @FXML
    private TableColumn<User, String> pwdCol;
    @FXML
    private TableColumn<User, String> roleCol;
    @FXML
    private TableColumn<User, String> telCol;


    @FXML
    private TextField txtEmail;
    @FXML
    private TextArea txtContenu;
    @FXML
    private TextField txtObjet;


    @FXML
    private TextField filterTxt;  

    
    private Parent root1=null;
    private Stage stage=null;
    private Scene scene;
    ActionEvent event;
    Stage dialogStage = new Stage();



    Connection con;
    PreparedStatement pst;
    int myIndex;
    String cinId;


 
    
        //Affichage des utilisateurs dans le tableau    
        public void table()
            {
            Connect();
            ObservableList<User> listUser = FXCollections.observableArrayList();
     
            try
                {
                    pst = con.prepareStatement("select * from users");  
                    ResultSet rs = pst.executeQuery();
                        {
                            while (rs.next())
                                {
                                    User user = new User();
                                    user.setCin(rs.getString("cin"));
                                    user.setCin(rs.getString("cin"));
                                    user.setFirstName(rs.getString("first_name"));
                                    user.setLastName(rs.getString("last_name"));
                                    user.setEmail(rs.getString("email"));
                                    user.setPassword(rs.getString("password"));
                                    user.setPhone(rs.getString("phone"));
                                    user.setTypeOfUser(rs.getString("type_of_user"));
                                    user.setIsAuthorized(rs.getInt("isAuthorized"));

                                    listUser.add(user);
                                }
                        }

                    tabUser.setItems(listUser);
                    cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
                    nomCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    prenomCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
                    pwdCol.setCellValueFactory(new PropertyValueFactory<>("password"));
                    telCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    roleCol.setCellValueFactory(new PropertyValueFactory<>("typeOfUser"));
                    autoCol.setCellValueFactory(new PropertyValueFactory<>("isAuthorized"));
                }
   
            catch (SQLException ex)
                {
                    Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
                }

            tabUser.setRowFactory( tv -> {
                TableRow<User> myRow = new TableRow<>();
                myRow.setOnMouseClicked (event ->
                    {
                        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                            {
                                myIndex =  tabUser.getSelectionModel().getSelectedIndex();
                                txtEmail.setText(tabUser.getItems().get(myIndex).getEmail());                  
                            }
                    });

                return myRow;
            });
        }

        


        //Connexion a la base de données
        public void Connect()
            {
                con = DataSource.getInstance().getConnection();
            }
        
     
    


        //Rechercher un utilisateur par son numéro de CIN
        public void search()
            {
            Connect();
            ObservableList<User> listUser = FXCollections.observableArrayList();
            
            try
                {
                    pst = con.prepareStatement("select * from users where cin ="+filterTxt.getText());  
                    ResultSet rs = pst.executeQuery();
                    {
                        while (rs.next())
                            {
                                User user = new User();
                                user.setCin(rs.getString("cin"));
                                user.setFirstName(rs.getString("first_name"));
                                user.setLastName(rs.getString("last_name"));
                                user.setEmail(rs.getString("email"));
                                user.setPassword(rs.getString("password"));
                                user.setPhone(rs.getString("phone"));
                                user.setTypeOfUser(rs.getString("type_of_user"));
                                user.setIsAuthorized(rs.getInt("isAuthorized"));
                        
                                listUser.add(user);
                            }
                    }

                    tabUser.setItems(listUser);
                    cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
                    nomCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    prenomCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
                    pwdCol.setCellValueFactory(new PropertyValueFactory<>("password"));
                    telCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    roleCol.setCellValueFactory(new PropertyValueFactory<>("typeOfUser"));
                    autoCol.setCellValueFactory(new PropertyValueFactory<>("isAuthorized"));              
                }
     
            catch (SQLException ex)
                {
                    Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
                }
  
            tabUser.setRowFactory( tv -> {
                TableRow<User> myRow = new TableRow<>();
                myRow.setOnMouseClicked (event ->
                    {
                        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                            {
                                myIndex =  tabUser.getSelectionModel().getSelectedIndex();
                                txtEmail.setText(tabUser.getItems().get(myIndex).getEmail());                
                            }
                    });
       
                return myRow;
                  });
        }



        
        //Envoi d'email 
        public void envoyerEmail()
            {

                String to = txtEmail.getText();
                String from = "bensakhriaoumaima23@gmail.com";
                String password = "acsqomvfjacqqugf"; 
    
                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "465");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.socketFactory.port", "465");
                prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    
                Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
    
                try 
                    {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(from));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                        message.setSubject(txtObjet.getText());
                        message.setText(txtContenu.getText());
                       
                        if((txtObjet.getText().isEmpty()) || (txtContenu.getText().isEmpty())|| (txtEmail.getText().isEmpty())){
                            infoBox("SVP Entrez toutes les informations  ", null, "Echec");

                        }
                        else{

                        Transport.send(message);
    
                        System.out.println("Mail Sent...");
                        infoBox("Mail envoyé avec succés !  ", null, "Succés");

                        txtContenu.setText("");
                        txtEmail.setText("");
                        txtObjet.setText("");
                    }
    
                    } 
                catch (MessagingException e) 
                    {
                        e.printStackTrace();
                    }
    
        }
    



    //Impression de tableau qui contient les détails des utilisateurs inscrits
    @FXML        
    void imprimerCliquer(ActionEvent event) 
        {
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (null != printerJob) 
                {
                    boolean proceed = printerJob.showPageSetupDialog(null);
                    if (proceed) 
                        {
                            boolean printed = printerJob.printPage(paneImp);
                            if (printed) 
                                {
                                    printerJob.endJob();
                                }
                            else   
                                {
                                    System.out.println("Printing failed.");
                                }
                        }

                } else 
                    {
                        System.out.println("Could not create a printer job.");
                    }
        }





        @Override
        public void initialize(URL location, ResourceBundle resources) 
            {
                Connect();
                table();            
            }    
        



        //Déconnexion
        public void goToIndex(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
                                
                                
          

        //Accés a la page de statistiques
        public void goToStatistique(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/Statistiqueutilisateur.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }



        
        //Accés a la page d'accueil
        public void goToHome(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/PanelAdmin.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }




        //Accés a la page de gestion des utilisateurs
        public void goToPanel(ActionEvent event) throws IOException 
            {
                root1 = FXMLLoader.load(getClass().getResource("../views/UserGestion.fxml"));
                scene = new Scene(root1);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }



            
        //Infobox
        public static void infoBox(String infoMessage, String headerText, String title)
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(infoMessage);
                alert.setTitle(title);
                alert.setHeaderText(headerText);
                alert.showAndWait();
            }


    }








