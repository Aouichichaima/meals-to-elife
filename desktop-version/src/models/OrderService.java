package models;

import java.sql.*;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

public class OrderService {

    private Connection connection = DataSource.getInstance().getConnection();
    private Statement statement;

    public ArrayList<Order> getAllOrders(int restaurantId) throws Exception {

        ArrayList<Order> returnedOrderList = new ArrayList<>();

        this.statement = this.connection.createStatement();

        String query = "select o.id, o.meals, u.first_name, u.last_name, u.phone, u.email from customer_orders as o inner join users as u on client_id = u.id where o.restaurant_id = " + restaurantId;
        ResultSet resultSet = this.statement.executeQuery(query);

        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            // deserialization of the meals of the order
            ObjectMapper mapper = new ObjectMapper();
            OrderedMeal[] orderedMeals = mapper.readValue(resultSet.getString(2), OrderedMeal[].class);
            User client = new User(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            returnedOrderList.add(new Order(id, client, orderedMeals));
        }

        return returnedOrderList;
    }

    public void moveOrderToArchive(int orderId, boolean isDelivered) throws SQLException {

        // insert into customer_orders_archive select *, <isDeliveredValue> as isDelivered from customer_orders where id = <orderId>

        this.statement = this.connection.createStatement();
        String request01 = "INSERT INTO customer_orders_archive SELECT *, " + isDelivered + " as isDelivered FROM customer_orders WHERE id = " + orderId;
        String request02 = "DELETE FROM customer_orders WHERE id = " + orderId;

        this.statement.executeUpdate(request01);
        this.statement.executeUpdate(request02);
    }
    
}
