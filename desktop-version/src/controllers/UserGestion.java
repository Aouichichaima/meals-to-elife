package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.DataSource;
import models.ServiceUser;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;


public class UserGestion implements Initializable {

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
    private Button deleteBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button modBtn;


    @FXML
    private TextField txtAuto;
    @FXML
    private TextField txtCin;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtPwd;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtTel;  
    @FXML
    private TextField filterTxt;  
    

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Parent root1=null;
    private Stage stage=null;
    private Scene scene;
    ActionEvent event;
    Stage dialogStage = new Stage();


    Connection con;
    PreparedStatement pst;
    int myIndex;
    String cinId;
        
        

 

    //Ajout d'un nouveau utilisateur
    @FXML
    void Add(ActionEvent event) throws SQLException 
        {
           String cin = txtCin.getText();
           String nom = txtNom.getText();
           String prenom = txtPrenom.getText();
           String email = txtEmail.getText();
           String tel = txtTel.getText();
           String pwd = txtPwd.getText();
           String role = txtRole.getText();
           String auto = txtAuto.getText();
           ServiceUser serviceUser = new ServiceUser();

           if(serviceUser.isCinOrEmailExist(cin, email)) 
                {
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Cet Utilisateur déja disponible");
                    alert.setContentText("Verifiez l'adresse Email et le N° Cin de cet utilisateur");
                    alert.showAndWait();
                    return;
                }


           if(cin.isEmpty()|| nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || pwd.isEmpty() || tel.isEmpty() || auto.isEmpty() || role.isEmpty())
                {
                    System.out.println("\n Erreur");
                    alert.setTitle("ERREUR");
                    alert.setHeaderText("Informations incomplètes");
                    alert.setContentText("Veuillez remplir tous les champs ! ");
                    alert.showAndWait();
                }

            else
                {
                    try
                        {

                            pst = con.prepareStatement("insert into users( `cin`, `first_name`, `last_name`,`email`, `password`,`phone`,`type_of_user`,`isAuthorized`) VALUES (?,?,?,?,?,?,?,?)");
                            pst.setString(1, cin);
                            pst.setString(2, nom);
                            pst.setString(3, prenom);
                            pst.setString(4, email);
                            pst.setString(5, pwd);
                            pst.setString(6, tel);
                            pst.setString(7, role);
                            pst.setString(8, auto);
                            pst.executeUpdate();
        
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("SUCCES");
                            alert.setHeaderText("Ajout d'utilisateur avec succés !");
                            alert.showAndWait();
                            table();


                    String to = txtEmail.getText();
                    String from = "bensakhriaoumaima23@gmail.com";
                    String password = "acsqomvfjacqqugf"; 
        
                    Properties prop = new Properties();
                    prop.put("mail.smtp.host", "smtp.gmail.com");
                    prop.put("mail.smtp.port", "465");
                    prop.put("mail.smtp.auth", "true");
                    prop.put("mail.smtp.socketFactory.port", "465");
                    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
                    Session session = Session.getInstance(prop, new javax.mail.Authenticator() 
                        {
                            protected PasswordAuthentication getPasswordAuthentication() 
                                {
                                    return new PasswordAuthentication(from, password);
                                }
                        });
                            //Autorisation
                    if(0!=Integer.parseInt(auto))
                    {
                        try 
                            {
                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(from));
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                                message.setSubject("CREATION et ACTIVATION de compte Meals to elife");
                                message.setText("Création de votre compte MEALS TO ELIFE par l'administration avec succés, ADRESSE EMAIL  =  " +txtEmail.getText() +"  MOT DE PASSE =   "+txtPwd.getText());
                                Transport.send(message);
                                System.out.println("Mail Sent...");
                                
                            txtCin.setText("");
                            txtNom.setText("");
                            txtPrenom.setText("");
                            txtEmail.setText("");
                            txtPwd.setText("");
                            txtTel.setText("");
                            txtRole.setText("");
                            txtAuto.setText("");
                            }

                        catch (MessagingException e) 
                            {
                                e.printStackTrace();
                            }
                    }


                //Refus
                else
                    {

                            txtCin.setText("");
                            txtNom.setText("");
                            txtPrenom.setText("");
                            txtEmail.setText("");
                            txtPwd.setText("");
                            txtTel.setText("");
                            txtRole.setText("");
                            txtAuto.setText("");
                            

                    } 

                        }
                    catch (SQLException ex)
                        {
                            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        }
 
    


    //Affichage des utilisateurs inscrits avec leurs détails
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
                                //id = Integer.parseInt(String.valueOf(tabUser.getItems().get(myIndex).getIsAuthorized()));

                                txtCin.setText(tabUser.getItems().get(myIndex).getCin());
                                txtNom.setText(tabUser.getItems().get(myIndex).getLastName());
                                txtPrenom.setText(tabUser.getItems().get(myIndex).getFirstName());
                                txtEmail.setText(tabUser.getItems().get(myIndex).getEmail());
                                txtPwd.setText(tabUser.getItems().get(myIndex).getPassword());
                                txtTel.setText(tabUser.getItems().get(myIndex).getPhone());
                                txtRole.setText(tabUser.getItems().get(myIndex).getTypeOfUser());
                                txtAuto.setText(String.valueOf(tabUser.getItems().get(myIndex).getIsAuthorized()));                
                            }
                    });
                return myRow;
            });
        }




    //Suppression d'un utilisateur
    @FXML
    void Delete(ActionEvent event) 
        {
            myIndex = tabUser.getSelectionModel().getSelectedIndex();
            cinId =tabUser.getItems().get(myIndex).getCin();   

            try
                {
                    pst = con.prepareStatement("delete from users where cin = ? ");
                    pst.setString(1, cinId);
                    pst.executeUpdate();
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCES ");
                    alert.setHeaderText("Suppression d'utilisateur avec succés !");
                    alert.showAndWait();
                    table();


                    String to = txtEmail.getText();
                    String from = "bensakhriaoumaima23@gmail.com";
                    String password = "acsqomvfjacqqugf"; 
        
                    Properties prop = new Properties();
                    prop.put("mail.smtp.host", "smtp.gmail.com");
                    prop.put("mail.smtp.port", "465");
                    prop.put("mail.smtp.auth", "true");
                    prop.put("mail.smtp.socketFactory.port", "465");
                    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
                    Session session = Session.getInstance(prop, new javax.mail.Authenticator() 
                        {
                            protected PasswordAuthentication getPasswordAuthentication() 
                                {
                                    return new PasswordAuthentication(from, password);
                                }
                        });
                     
                        try{
                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(from));
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                                message.setSubject("SUPPRESSION de compte");
                                message.setText("Votre compte MEALS TO ELIFE est supprimé !");
                                Transport.send(message);
                                System.out.println("Mail envoyé...");
                            
                }
                catch (MessagingException e) 
                {
                    e.printStackTrace();
                }}
            catch (SQLException ex)

                {
                    Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    


       
    //Modification d'un utilisateur (Avec autorisation ou refus)
    @FXML
    void Update(ActionEvent event) 
        {
            String  cin ,nom, prenom, email, pwd, tel, auto, role  ;
    
            myIndex = tabUser.getSelectionModel().getSelectedIndex();
            cinId =tabUser.getItems().get(myIndex).getCin();
            cin=txtCin.getText();
            nom = txtNom.getText();
            prenom = txtPrenom.getText();
            email = txtEmail.getText();
            pwd = txtPwd.getText();
            tel = txtTel.getText();
            role = txtRole.getText();
            auto = txtAuto.getText();

            try
                {
                    pst = con.prepareStatement("update users set last_name = ?,first_name = ? ,email = ? ,password = ? ,phone = ? ,type_of_user = ? ,isAuthorized = ? where cin = "+cin);
                    pst.setString(1, nom);
                    pst.setString(2, prenom);
                    pst.setString(3, email);
                    pst.setString(4, pwd);
                    pst.setString(5, tel);
                    pst.setString(6, role);
                    pst.setString(7, auto);
                    pst.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCES");
                    alert.setHeaderText("Modification d'utilisateur avec succés !");
                    alert.showAndWait();
                    table();



                    String to = txtEmail.getText();
                    String from = "bensakhriaoumaima23@gmail.com";
                    String password = "acsqomvfjacqqugf"; 
        
                    Properties prop = new Properties();
                    prop.put("mail.smtp.host", "smtp.gmail.com");
                    prop.put("mail.smtp.port", "465");
                    prop.put("mail.smtp.auth", "true");
                    prop.put("mail.smtp.socketFactory.port", "465");
                    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
                    Session session = Session.getInstance(prop, new javax.mail.Authenticator() 
                        {
                            protected PasswordAuthentication getPasswordAuthentication() 
                                {
                                    return new PasswordAuthentication(from, password);
                                }
                        });
    


                    //Autorisation
                    if(0!=Integer.parseInt(auto))
                        {
                            try 
                                {
                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress(from));
                                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                                    message.setSubject("ACTIVATION de compte Meals to elife");
                                    message.setText("Votre compte meals to elife est activé, votre adresse Email  =  " +txtEmail.getText() +"  Et votre mot de passe =   "+txtPwd.getText());
                                    Transport.send(message);
                                    System.out.println("Mail Sent...");
                                }
    
                            catch (MessagingException e) 
                                {
                                    e.printStackTrace();
                                }
                        }


                    //Refus
                    else
                        {
                            try 
                                {
                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress(from));
                                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                                    message.setSubject("DESACTIVATION de compte Meals to elife");
                                    message.setText("Votre compte meals to elife est désactivé ");
                                    Transport.send(message);
                                    System.out.println("Mail Sent...");
                                }

                            catch (MessagingException e) 
                                {
                                    e.printStackTrace();
                                }

                        }
                    }
                catch (SQLException ex)
                    {
                        Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        
        


        
        
    //Connexion a la base de données    
    public void Connect()
        {
            con = DataSource.getInstance().getConnection();

        }
        
     
     

    //Rechercher un utilisteur par son numéro de CIN
    public void search()
        {
            Connect();
            ObservableList<User> listUser = FXCollections.observableArrayList();
            
            try
                {
                    pst = con.prepareStatement("select * from users where cin LIKE '%"+filterTxt.getText()+"%'");  
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
                                //id = Integer.parseInt(String.valueOf(tabUser.getItems().get(myIndex).getIsAuthorized()));
                        
                                txtCin.setText(tabUser.getItems().get(myIndex).getCin());
                                txtNom.setText(tabUser.getItems().get(myIndex).getLastName());
                                txtPrenom.setText(tabUser.getItems().get(myIndex).getFirstName());
                                txtEmail.setText(tabUser.getItems().get(myIndex).getEmail());
                                txtPwd.setText(tabUser.getItems().get(myIndex).getPassword());
                                txtTel.setText(tabUser.getItems().get(myIndex).getPhone());
                                txtRole.setText(tabUser.getItems().get(myIndex).getTypeOfUser());
                                txtAuto.setText(String.valueOf(tabUser.getItems().get(myIndex).getIsAuthorized()));                                       
                            }
                    });
                return myRow;
            });
         }







    @Override
    public void initialize(URL location, ResourceBundle resources) 
        {
            Connect();
            table();            
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
                                
         
        
                                
    //Root : Rechargement de l'interface de statistiques    
    public void goToStatistique(ActionEvent event) throws IOException 
        {
            root1 = FXMLLoader.load(getClass().getResource("../views/Statistiqueutilisateur.fxml"));
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




    //Root : Rechargement de l'interface de contact    
    public void goToContact(ActionEvent event) throws IOException 
        {
            root1 = FXMLLoader.load(getClass().getResource("../views/sendMail.fxml"));
            scene = new Scene(root1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


                                    
    }




