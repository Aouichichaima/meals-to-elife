package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class ServiceRestaurant implements IService<Restaurant> {

    private Connection connection = DataSource.getInstance().getConnection();
    private Statement statement;

    @Override
    public boolean add(Restaurant t) throws SQLException {
        return false;
    }

    @Override
    public void update(Restaurant t) throws SQLException {
    }

    @Override
    public boolean delete(Restaurant t) throws SQLException {
        return false;
    }

    @Override
    public Restaurant findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Restaurant> readAll() throws SQLException {

        this.statement = this.connection.createStatement();
        String query = "SELECT * FROM restaurants";
        
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        ResultSet resultSet = this.statement.executeQuery(query);

        while(resultSet.next()){
            Restaurant restaurant = new Restaurant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
            restaurants.add(restaurant);
        }


        return restaurants;
    }

    public Restaurant findByManagerId(int managerId) throws SQLException {

        this.statement = this.connection.createStatement();
        String query = "SELECT * FROM restaurants WHERE id_manager = " + managerId;
        ResultSet resultSet = this.statement.executeQuery(query);

        Restaurant restaurant = null;
        if (resultSet.next())
            restaurant = new Restaurant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));

        return restaurant;
    }

    public boolean updateNameAddress(int idRestaurant, String name, String address) throws SQLException {

        this.statement = this.connection.createStatement();
        String request = "UPDATE restaurants SET name = \"" + name + "\", address = \"" + address + "\" WHERE id = " + idRestaurant;
        return this.statement.executeUpdate(request) > 0;
    }


    public boolean addMealToMenu(int idRestaurant, Meal meal) throws SQLException {
        this.statement = this.connection.createStatement();
        String request1 = "SET @json_menu = (SELECT menu FROM restaurants WHERE id = "+idRestaurant+")";
        String request2 = "UPDATE restaurants SET menu = (SELECT JSON_ARRAY_APPEND(@json_menu, '$', CAST('{\"name\": \""+meal.getName()+"\", \"price\": "
                            +meal.getPrice()+", \"description\":\""+meal.getDescription()+"\"}' AS JSON))) WHERE id = " + idRestaurant;
        this.statement.executeUpdate(request1);
        return this.statement.executeUpdate(request2) == 1;
    }


    public ArrayList<Meal> deserializeJSONMenu(String JSONMenu) throws Exception{
        ArrayList<Meal> menu = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Meal[] meals = mapper.readValue(JSONMenu, Meal[].class);
        for (int i = 0; i < meals.length; i++) menu.add(meals[i]);
        return menu;
    }


    public boolean updateMeal(int idRestaurant, int mealIndex, String newName, String newDescription, double newPrice) throws SQLException {
        this.statement = this.connection.createStatement();
        String request1 = "SET @json_column_value = ( SELECT menu FROM restaurants WHERE id = "+idRestaurant+")";
        String request2 = "UPDATE restaurants SET menu = (SELECT JSON_SET(@json_column_value, '$["+mealIndex+"]', CAST('{\"name\": \""+newName+"\", \"price\": "
                            +newPrice+", \"description\": \""+newDescription+"\" }' AS JSON))) WHERE id = " + idRestaurant;
        
        this.statement.executeUpdate(request1);
        return this.statement.executeUpdate(request2) == 1;

    }

    public boolean deleteMeal(int idRestaurant, int mealIndex) throws SQLException {
        this.statement = this.connection.createStatement();
        String request1 = "SET @json_column_value = ( SELECT menu FROM restaurants WHERE id = "+idRestaurant+")";
        String request2 = "UPDATE restaurants SET menu = (SELECT JSON_REMOVE(@json_column_value, '$["+mealIndex+"]')) WHERE id = " + idRestaurant;

        this.statement.executeUpdate(request1);
        return this.statement.executeUpdate(request2) == 1;
    }
    
}


/*
* to add item in a json array column in table first fetch the old json state then update
* SET @json_column_value = (SELECT <column_name> FROM <table_name> WHERE <condition>);
* UPDATE <table_name> SET <column_name> = (SELECT JSON_ARRAY_APPEND(@json_column_value, '$',  CAST('<your_value>' AS JSON))) WHERE <condition>;
* 
*/

/*
 *
 * to update item in json array column in table ==>>> 
 * SET @json_column_value = ( SELECT <column_name> FROM <table_name> WHERE <condition> );
 * UPDATE <table_name> SET <column_name> = (SELECT JSON_SET(@json_column_value, '$[index]', CAST('<your_new_value>' AS JSON))) WHERE <condition>;
 * 
 */

/*
 * to delete item in json array column in a table ==>>>
 * SET @json_column_value = ( SELECT <column_name> FROM <table_name> WHERE <condition> );
 * UPDATE <table_name> SET <column_name> = (SELECT JSON_REMOVE(@json_column_value, '$[index]')) WHERE <condition>;
 * 
 */