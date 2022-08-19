package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Order {
    
    private int id;
    private User client;
    private OrderedMeal[] meals;
  
    public Order(int id, User client, OrderedMeal[] meals) {
        this.id = id;
        this.client = client;
        this.meals = meals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public OrderedMeal[] getMeals() {
        return meals;
    }

    public void setMeals(OrderedMeal[] meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Order [client=" + client + ", id=" + id + ", meals=" + Arrays.toString(meals) + "]";
    }

    

}
