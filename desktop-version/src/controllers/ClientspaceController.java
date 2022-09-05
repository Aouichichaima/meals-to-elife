package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Meal;
import models.OrderedMeal;
import models.Restaurant;
import models.ServiceRestaurant;
import models.ServiceUser;

public class ClientspaceController {

    @FXML
    private VBox restoListVBox;
    @FXML
    private VBox menuListVBox;
    @FXML
    private VBox cartVBox;

    private int clientId;
    private int restaurantId;
    private ArrayList<OrderedMeal> orderedMeals = new ArrayList<>();

    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    private ServiceUser serviceUser  = new ServiceUser();

    

    public void setClientId(int clientId) {
        this.clientId = clientId;
        this.render();
    }


    public void render() {
        try {
            List<Restaurant> restaurants = serviceRestaurant.readAll();

            for (Restaurant restaurant : restaurants) {
                Button button = new Button(restaurant.getName());

                button.setOnAction(e -> { this.renderRestaurantMenu(restaurant); });

                this.restoListVBox.getChildren().add(button);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void renderRestaurantMenu(Restaurant resto) {
        this.restaurantId = resto.getId();
        this.menuListVBox.getChildren().clear();
        this.orderedMeals.clear();
        this.renderCart();

        // deserialization du menu...
        ObjectMapper mapper = new ObjectMapper();
        try {

            Meal[] meals = mapper.readValue(resto.getMenu(), Meal[].class);


            for (int i = 0; i < meals.length; i++) {
                Meal meal = meals[i];

                Label mealLabel = new Label(meal.getName() + " prix : " + meal.getPrice() + "    ");
                Button addToCartBtn = new Button("ajouter");

                addToCartBtn.setOnAction(e -> { this.addToOrderedMeals(meal.getName(), meal.getPrice(), 1); });

                FlowPane flowPane = new FlowPane(mealLabel, addToCartBtn);
                
                this.menuListVBox.getChildren().add(flowPane);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToOrderedMeals(String name, double unitPrice, int quantity) {
        this.orderedMeals.add(new OrderedMeal(name, unitPrice, quantity));
        this.renderCart();
    }

    public void removeFromOrderdMeals(int index) {
        this.orderedMeals.remove(index);
        System.out.println("remove meal of index : " + index);
        this.renderCart();
    }

    public void renderCart() {
        this.cartVBox.getChildren().clear();

        for (int i = 0; i < this.orderedMeals.size(); i++) {
            OrderedMeal orderedMeal = this.orderedMeals.get(i);
            Label mealLabel = new Label(orderedMeal.getName() + "  prix  " + orderedMeal.getUnitPrice());
            Button removeMealBtn = new Button("supprimer");
            int index = i;
            removeMealBtn.setOnAction(e -> {
                this.removeFromOrderdMeals(index);
            });
            FlowPane flowPane = new FlowPane(mealLabel, removeMealBtn);
            this.cartVBox.getChildren().add(flowPane);
        }
    }

    public void sendOrder(Event event) {
        try {
            serviceUser.addOrder(this.orderedMeals.toString(), this.clientId, this.restaurantId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void parametreClient (ActionEvent event) {
     

        System.out.println("ParametreClient");



    }
    public void logout(ActionEvent event){

System.out.println("Deconnexion de la page ");

    }

}
