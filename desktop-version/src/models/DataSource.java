package models;

import java.sql.*;

public class DataSource {
    
    private static DataSource data;
    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/elife";
    private String login = "root";
    private String pwd = "toor";

    private DataSource() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.login, this.pwd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
        if(data == null) data = new DataSource();
        return data;
    }
}
