package controllers;

import java.sql.SQLException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Meal;
import models.Restaurant;
import models.ServiceRestaurant;

public class ClientspaceController {

    @FXML
    private VBox restoListVBox;
    @FXML
    private VBox menuListVBox;

    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();

    public void initialize() {
        try {
            List<Restaurant> restaurants = serviceRestaurant.readAll();

            for (Restaurant restaurant : restaurants) {
                Button button = new Button(restaurant.getName());

                button.setOnAction(e -> {
                    this.renderRestaurantMenu(restaurant);
                });

                this.restoListVBox.getChildren().add(button);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void renderRestaurantMenu(Restaurant resto) {
        this.menuListVBox.getChildren().clear();

        // deserialization du menu...
        ObjectMapper mapper = new ObjectMapper();
        try {

            Meal[] meals = mapper.readValue(resto.getMenu(), Meal[].class);


            for (int i = 0; i < meals.length; i++) {
                Meal meal = meals[i];

                Label mealLabel = new Label(meal.getName() + " prix : " + meal.getPrice() + "    ");
                Button addToCartBtn = new Button("ajouter");

                addToCartBtn.setOnAction(e -> {
                    System.out.println("ajouter dans le panier name" + meal.getName() + "unitPrice" + meal.getPrice() );
                });

                FlowPane flowPane = new FlowPane(mealLabel, addToCartBtn);
                
                this.menuListVBox.getChildren().add(flowPane);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
