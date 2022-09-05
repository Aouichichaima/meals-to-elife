package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
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
    private int restaurantManagerId;

    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    private OrderService orderService = new OrderService();

    @FXML
    private VBox ordersVBox;

    // c'est pour l'assignement de l'id du l'utilisateur à partir d'un autre controlleur
    public void setRestaurantManagerId(int restaurantManagerId) {
        if (restaurantManagerId > 0)
            this.restaurantManagerId = restaurantManagerId;
        this.render();
    }

    public void initialize() {
    }

    // c'est pour l'actualisation du l'interface aprés tout operation 'CRUD'
    public void render() {
        this.ordersVBox.getChildren().clear();
        try {
            this.restaurant = serviceRestaurant.findByManagerId(this.restaurantManagerId);
            ArrayList<Order> orderList = orderService.getAllOrders(restaurant.getId());

            for (Order order : orderList) {
                double totalOrderPrice = 0;

                VBox innerVBox = new VBox();
                for (int i = 0; i < order.getMeals().length; i++) {

                    OrderedMeal orderedMeal = order.getMeals()[i];
                    String mealLabelText = "    Repas : " + orderedMeal.getName() + " | Quantity : "
                            + orderedMeal.getQuantity() + " | Prix :  " + orderedMeal.getUnitPrice() + " * "
                            + orderedMeal.getQuantity() + " ............ "
                            + orderedMeal.getUnitPrice() * orderedMeal.getQuantity();
                    Label mealLabel = new Label(mealLabelText);
                    mealLabel.setFont(new Font("Arial", 14));
                    innerVBox.getChildren().add(mealLabel);
                    totalOrderPrice += order.getMeals()[i].getQuantity() * order.getMeals()[i].getUnitPrice();
                }

                String orderDescribe = "Commande " + order.getId() + "  Prix Total " + totalOrderPrice + "\nClient "
                        + order.getClient().getFirstName()
                        + " " + order.getClient().getLastName() + "  " + order.getClient().getPhone() + " "
                        + order.getClient().getEmail();

                Label orderDescLabel = new Label(orderDescribe);
                orderDescLabel.setFont(new Font("Arial", 16));

                Button deliveredOrderButton = new Button("Commande livré");
                deliveredOrderButton.setOnAction(event -> {
                    this.archiveOrder(order.getId(), true);
                });

                Button canceledOrderButton = new Button("Annuler la Commande");
                canceledOrderButton.setOnAction(event -> {
                    this.archiveOrder(order.getId(), false);
                });

                FlowPane buttonsFlowPane = new FlowPane(deliveredOrderButton, canceledOrderButton);
                buttonsFlowPane.setHgap(10);
                buttonsFlowPane.setPadding(new Insets(10, 10, 10, 390));

                Separator separator = new Separator();

                this.ordersVBox.getChildren().addAll(orderDescLabel, innerVBox, buttonsFlowPane, separator);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // method à executer lorsque on clique sur le bouton "Modifier les cordonnée de resto"
    public void goToRestaurantSettingsHandler(ActionEvent event) throws IOException {

        // communication entre les controlleur...
        // controlleur CustomerOrdersController.java doit envoyer l'id du manager du
        // resto à RestaurantSettingsController.java
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/restaurantSettings.fxml"));
        root = loader.load();
        RestaurantSettingsController restaurantSettingsController = loader.getController();
        restaurantSettingsController.setRestaurantManagerId(this.restaurantManagerId);

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // method à executer lorsque on clique sur le bouton "Modifier les cordonnée du chef"
    public void goToChefSettingsInterfaceHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/updateUserData.fxml"));
        root = loader.load();
        updateUserDataController updateUser = loader.getController();
        updateUser.setUserId(this.restaurantManagerId);

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


    // method à executer lorsque on clique sur le bouton "Commande livré" ou "Annuler la Commande"
    public void archiveOrder(int id, boolean isDelivered) {
        try {
            orderService.moveOrderToArchive(id, isDelivered);
            this.render();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // méthode de déconnexion
    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}