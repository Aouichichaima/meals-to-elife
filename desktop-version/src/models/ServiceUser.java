package models;

import java.sql.*;
import java.util.List;



public class ServiceUser implements IService<User> {

    private Connection connection = DataSource.getInstance().getConnection();
    private Statement statement;

    @Override
    public boolean add(User user) throws SQLException {
        this.statement = connection.createStatement();
        String request = "INSERT INTO users VALUES (null, '"+user.getCin()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getPhone()+"', '"+user.getEmail()+"', '"+user.getPassword()+"', '"+user.getTypeOfUser()+"', null, 0)";
        return this.statement.executeUpdate(request) != 0;
    }

    @Override
    public void update(User t) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean delete(User t) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User findById(int id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> readAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isCinOrEmailExist(String cin, String email) throws SQLException {

        this.statement = connection.createStatement();
        String query = "SELECT count(*) from users where cin = '"+cin+"' or email = '"+email+"'";
        ResultSet result = this.statement.executeQuery(query);
        result.next();
        if(result.getInt(1) != 0) return true;
        return false;

    }
    
}
