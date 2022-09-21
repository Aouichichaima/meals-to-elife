package models;

import java.sql.*;
import java.util.List;



public class ServiceUser implements IService<User> {

    private Connection connection = DataSource.getInstance().getConnection();
    private Statement statement;

    @Override
    public boolean add(User user) throws SQLException {
        this.statement = connection.createStatement();
        String request01 = "INSERT INTO users VALUES (null, '"+user.getCin()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getPhone()+"', '"+user.getEmail()+"', '"+user.getPassword()+"', '"+user.getTypeOfUser()+"', null, FALSE)";
        int result01 = this.statement.executeUpdate(request01);
        String insertedUserId = findUserIdByCinOrEmail(user.getCin(), user.getEmail());

        if(user.getTypeOfUser() == "restaurant") {
            String request02 = " INSERT INTO restaurants (id_manager) value (" + insertedUserId + ")";
            int result02 = this.statement.executeUpdate(request02);
            return result01 + result02 == 2; 
        }


        return  result01 == 1;
    }

    @Override
    public void update(User user) throws SQLException {
        
        this.statement = connection.createStatement();
        String updateRequest = "UPDATE users SET cin = '" + user.getCin() + "', first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName() 
                                + "', phone = '" + user.getPhone() + "', email = '" + user.getEmail() + "', password = '"+user.getPassword()+"' WHERE id = " + user.getId();
    

        int updateResult = this.statement.executeUpdate(updateRequest);

        if(updateResult == 1) System.out.println("user updated successfully...");
        else throw new SQLException();

    }

    @Override
    public boolean delete(User t) throws SQLException {
        return false;
    }

    @Override
    public User findById(int id) throws SQLException {
        
        this.statement = connection.createStatement();
        String query = "SELECT * FROM users WHERE id = " + id;

        ResultSet result = this.statement.executeQuery(query);
        result.next();

        User user = new User(result.getString(2), result.getString(3), result.getString(4), result.getString(6), result.getString(5));

        System.out.println(user.toString());

        return user;
    }

    @Override
    public List<User> readAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isCinOrEmailExist(String cin, String email) throws SQLException {

        this.statement = connection.createStatement();
        String query = "SELECT count(*) FROM users WHERE cin = '"+cin+"' OR email = '"+email+"'";
        ResultSet result = this.statement.executeQuery(query);
        result.next();
        if(result.getInt(1) != 0) return true;
        return false;

    }

    public String findUserIdByCinOrEmail (String cin, String email) throws SQLException{
        
        this.statement = connection.createStatement();
        String query = "SELECT id FROM users WHERE cin = '"+cin+"' OR email = '"+email+"'";
        ResultSet result = this.statement.executeQuery(query);
        result.next();


        return result.getString(1);
    }


    public boolean isAuthorized (String email) throws SQLException {
        
        this.statement = connection.createStatement();
        String query = "SELECT isAuthorized FROM users WHERE email = '"+email+"'";
        ResultSet result = this.statement.executeQuery(query);
        result.next();
        if(result.getInt(1) != 0) return true;
        return false;
    }

    // pour ajouter une commande 
    public void addOrder(String meals, int clientId, int restaurantId) throws SQLException {
        this.statement = this.connection.createStatement();
        String request = "INSERT INTO customer_orders VALUES(null, CAST('"+meals+"' AS JSON), "+clientId+", "+restaurantId+")";
        this.statement.executeUpdate(request);
    }




    public boolean isEmailExist( String email) throws SQLException {

        this.statement = connection.createStatement();
        String query = "SELECT count(*) FROM users WHERE email = '"+email+"'";
        ResultSet result = this.statement.executeQuery(query);
        result.next();
        if(result.getInt(1) != 0) return true;
        return false;

    }
    
}
