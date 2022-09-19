package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Meal;
import models.OrderedMeal;
import models.Restaurant;
import models.ServiceRestaurant;
import models.ServiceUser;
import models.User;

public class ClientspaceController {

    @FXML
    private VBox restoListVBox;
    @FXML
    private VBox menuListVBox;
    @FXML
    private VBox cartVBox;
    @FXML
    private Label userNameLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int clientId;
    private User user;
    private int restaurantId;
    private ArrayList<OrderedMeal> orderedMeals = new ArrayList<>();

    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    private ServiceUser serviceUser  = new ServiceUser();

    

    public void setClientId(int clientId) {
        this.clientId = clientId;
        this.render();
    }


    public void render() {
        ServiceUser serviceUser = new ServiceUser();
        
        try {
            List<Restaurant> restaurants = serviceRestaurant.readAll();
            this.user = serviceUser.findById(clientId);
            this.userNameLabel.setText(user.getFirstName() + " " + user.getLastName());

            for (Restaurant restaurant : restaurants) {
                Button button = new Button(restaurant.getName());
                button.setFont(new Font("Arial", 16));
                button.setStyle("-fx-color: #0d6efd");

                button.setOnAction(e -> { this.renderRestaurantMenu(restaurant); });

                this.restoListVBox.getChildren().add(button);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.restoListVBox.setSpacing(10);
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
                int index = i;
                Label mealLabel = new Label(meal.getName() + " prix : " + meal.getPrice() + "    ");
                mealLabel.setFont(new Font("Arial", 16));
                Button addToCartBtn = new Button("ajouter");
                addToCartBtn.setStyle("-fx-color:  #198754");
                addToCartBtn.setFont(new Font("Arial", 14));

                addToCartBtn.setOnAction(e -> { this.addToOrderedMeals(index, meal.getName(), meal.getPrice(), 1); });

                FlowPane flowPane = new FlowPane(mealLabel, addToCartBtn);
                
                this.menuListVBox.getChildren().add(flowPane);
            }

            this.menuListVBox.setSpacing(8);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToOrderedMeals(int index, String name, double unitPrice, int quantity) {
        
        boolean isOrderedMealsExist = false;
        int orderedMealId = 0;

        for(int i = 0; i<this.orderedMeals.size(); i++) {
            if(this.orderedMeals.get(i).getId() == index) {
                isOrderedMealsExist = true;
                orderedMealId = i;
            };
        }

        if(!isOrderedMealsExist) {
            this.orderedMeals.add(new OrderedMeal(index, name, unitPrice, quantity));
        } else {
            int quantitySelectedMeal = this.orderedMeals.get(orderedMealId).getQuantity();
            
            this.orderedMeals.get(orderedMealId).setQuantity(quantitySelectedMeal+1);
        }


        this.renderCart();
    }

    public void removeFromOrderdMeals(int index) {
        if(this.orderedMeals.get(index).getQuantity() == 1)
            this.orderedMeals.remove(index);
        else {
            int quantitySelectedMeal = this.orderedMeals.get(index).getQuantity();
            this.orderedMeals.get(index).setQuantity(quantitySelectedMeal-1);
        }
  
        this.renderCart();
    }

    public void renderCart() {
        this.cartVBox.getChildren().clear();

        for (int i = 0; i < this.orderedMeals.size(); i++) {
            OrderedMeal orderedMeal = this.orderedMeals.get(i);
            Label mealLabel = new Label(orderedMeal.getName() + " Prix :" + orderedMeal.getUnitPrice() * orderedMeal.getQuantity());
            mealLabel.setFont(new Font("Arial", 16));
            
            Label productQuantity = new Label(" Quantity : " + orderedMeal.getQuantity());
            productQuantity.setFont(new Font("Arial", 16));

            Button removeMealBtn = new Button("supprimer");
            removeMealBtn.setFont(new Font("Arial", 14));
            removeMealBtn.setStyle("-fx-color: #dc3545");
            int index = i;
            removeMealBtn.setOnAction(e -> {
                this.removeFromOrderdMeals(index);
            });
            FlowPane flowPane = new FlowPane(mealLabel, productQuantity, removeMealBtn);
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

    public void parametreClient (ActionEvent event) throws IOException{
     

        System.out.println("ParametreClient");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/updateUserData.fxml"));
        root = loader.load();
        updateUserDataController updateUser = loader.getController();
        updateUser.setUserId(this.clientId);

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();



    }

    public void logout(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
