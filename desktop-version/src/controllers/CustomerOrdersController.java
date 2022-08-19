package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Order;
import models.OrderService;
import models.OrderedMeal;
import models.Restaurant;
import models.ServiceRestaurant;

public class CustomerOrdersController {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Restaurant restaurant = null;
    private int restaurantManagerId = 1; // this value of the logged in restaurant manager will set later from the login view

    
    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    private OrderService orderService = new OrderService();

    @FXML
    private VBox ordersVBox;
    
    
    public void setRestaurantManagerId(int restaurantManagerId) {
        if(restaurantManagerId > 0) this.restaurantManagerId = restaurantManagerId;
    }


    public void initialize() {
        System.out.println("CustomerOrdersController initialize...");

        try {

            this.restaurant = serviceRestaurant.findByManagerId(this.restaurantManagerId);
            ArrayList<Order> orderList =  orderService.getAllOrders(restaurant.getId());

            for (Order order : orderList) {
                double totalOrderPrice = 0;
                
            
                VBox innerVBox = new VBox();

                for (int i = 0; i < order.getMeals().length; i++) {
                    
                    OrderedMeal orderedMeal = order.getMeals()[i];
                    Label mealLabel = new Label("Repas : " + orderedMeal.getTitre() + " Quantity : " + orderedMeal.getQuantity());
                    innerVBox.getChildren().add(mealLabel);
                    totalOrderPrice += order.getMeals()[i].getQuantity() * order.getMeals()[i].getUnitPrice();
                }

                String orderDescribe = "order id -> " + order.getId() + "    client info -> " + order.getClient().getFirstName() + " " 
                + order.getClient().getLastName() + "  " +order.getClient().getPhone() + "  prix-total -> " + totalOrderPrice;
                
                Label orderDescLabel = new Label(orderDescribe); orderDescLabel.setFont(new Font("Arial", 16));
                Separator separator = new Separator();

                this.ordersVBox.getChildren().addAll(orderDescLabel, innerVBox, separator);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    

    public void goToRestaurantSettingsHandler(ActionEvent event) throws IOException {

        // communication entre les controlleur...
        // controlleur CustomerOrdersController.java doit envoyer l'id du manager du resto à RestaurantSettingsController.java
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/restaurantSettings.fxml"));
        root = loader.load();
        RestaurantSettingsController restaurantSettingsController = loader.getController();
        restaurantSettingsController.setRestaurantManagerId(this.restaurantManagerId);
        
        // root = FXMLLoader.load(getClass().getResource("../views/restaurantSettings.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

/*
    FXMLLoader loader = new FXMLLoader(getClass().getResource("./scene02.fxml"));
        root = loader.load();
        Scene02 scene2Controller = loader.getController();
        scene2Controller.displayData(data);
*/