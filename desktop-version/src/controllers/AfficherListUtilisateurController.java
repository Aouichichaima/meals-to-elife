package controllers;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.DataSource;
import models.User;

public class AfficherListUtilisateurController {

    @FXML
    TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> columNCin;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnPrenom;
    @FXML
    private TableColumn<User, String> columnEmail;
    @FXML
    private TableColumn<User, String> columnTel;
    @FXML
    private TableColumn<User, String> columnType;
    @FXML
    private TableColumn<User, String> columnAuto;
    private ObservableList<User> data;
    private Connection ReadConn=null;
    User user = null;

    public void loadDataFromDatabase() throws SQLException {
        try {
            ReadConn = DataSource.getInstance().getConnection();
            data = FXCollections.observableArrayList();
            ResultSet rs = ReadConn.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                data.add(new User(rs.getString(2), rs.getString(4), rs.getString(3),rs.getString(6),rs.getString(5),rs.getString(8),rs.getInt(10)));
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);}
       /* }finally {
            if(ReadConn!=null){
                ReadConn.close();
            }
        }*/
        columNCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("typeOfUser"));
        columnAuto.setCellValueFactory(new PropertyValueFactory<>("isAuthorized"));

        tableUser.setItems(null);
        tableUser.setItems(data);
    }


    @FXML
    private void auto() throws IOException{
         FXMLLoader fxmlLoader = null;
         Parent root = null;
         Stage stage = null;
    
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("../views/AutorisationView.fxml"));
            root = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Valider l'etat d'un utilisateur ");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.err.print(e);
        }
    }

}

    

