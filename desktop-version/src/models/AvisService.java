package models;

import java.sql.*;


public class AvisService {
    private Connection connection = DataSource.getInstance().getConnection();
    private Statement statement;


    public void createAvis(int idClient, int idRestaurant, int rating, String notice) throws SQLException {
        this.statement = this.connection.createStatement();
        String request = "INSERT INTO avis VALUE (null, "+rating+", '"+notice+"', "+idClient+", "+idRestaurant+")";
        this.statement.executeUpdate(request);
    }

    
}
