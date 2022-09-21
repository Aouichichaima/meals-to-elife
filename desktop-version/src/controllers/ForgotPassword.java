package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DataSource;
import models.ServiceUser;

public class ForgotPassword implements Initializable{

    @FXML
    private Button recuperer;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label errLab;

        
    Stage dialogStage = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;



        
        @FXML
        public void recupererPwd(ActionEvent event){
            String email = txtEmail.getText();

        String sql = "SELECT * FROM users WHERE email =? ";
        
        try{
            con = DataSource.getInstance().getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();      


            ServiceUser serviceUser = new ServiceUser();
            
            if(email.isEmpty()){
                errLab.setText("Entrez vos coordonnées  ");
            }
             else if

             (!(serviceUser.isEmailExist(email))) {
                errLab.setText("Utilisateur inconnu  ");
            }
            else {
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
                {
                    try 
                        {

                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(from));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                            message.setSubject("Récupération de mot de passe");
                            while (resultSet.next())
                            {
                                String pwd= resultSet.getString("password");
                            
                            message.setText("Votre MOT DE PASSE est : "+pwd+" ET VOTRE ADRESSE EMAIL : " +txtEmail.getText());
                            Transport.send(message);}
                            System.out.println("Mail Sent...");
                            
                        txtEmail.setText("");
                        }

                    catch (MessagingException e) 
                        {
                            e.printStackTrace();
                        }
                    }
                    errLab.setText("Email envoyé avec succès  ");
                    }
                }
              
                catch (SQLException e) 
                        {
                            e.printStackTrace();
                        }
            }



    



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }


    

}