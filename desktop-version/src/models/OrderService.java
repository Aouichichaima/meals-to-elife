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
/*
+----+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+------------+-----------+----------+------------------------+
| id | meals                                                                                                                                                                 | first_name | last_name | phone    | email                  |
+----+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+------------+-----------+----------+------------------------+
|  1 | [{"titre": "repas01", "quantity": 2, "unitPrice": 1.5}]                                                                                                               | firas      | djebby    | 58744349 | djebby.firas@gmail.com |
|  2 | [{"titre": "repas01", "quantity": 2, "unitPrice": 1.5}, {"titre": "repas09", "quantity": 4, "unitPrice": 9.5}]                                                        | firas      | djebby    | 58744349 | djebby.firas@gmail.com |
|  3 | [{"titre": "repas01", "quantity": 2, "unitPrice": 1.5}, {"titre": "repas09", "quantity": 4, "unitPrice": 9.5}, {"titre": "repas05", "quantity": 5, "unitPrice": 5.5}] | firas      | djebby    | 58744349 | djebby.firas@gmail.com |
+----+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+------------+-----------+----------+------------------------+ 

*/

        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            // deserialization of the meals of the order
            ObjectMapper mapper = new ObjectMapper();
            OrderedMeal[] orderedMeals = mapper.readValue(resultSet.getString(2), OrderedMeal[].class);

            // for (int i = 0; i < orderedMeals.length; i++) System.out.println(orderedMeals[i]);
            // System.out.println("-------------------------------------------------------------");
            
            User client = new User(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            // System.out.println(client);

            returnedOrderList.add(new Order(id, client, orderedMeals));
            

            // System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }

        return returnedOrderList;
    }
    
}
