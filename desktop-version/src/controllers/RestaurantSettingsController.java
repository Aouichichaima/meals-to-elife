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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Meal;
import models.Restaurant;
import models.ServiceRestaurant;

public class RestaurantSettingsController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Restaurant restaurant = null;
    public int restaurantManagerId;
    private int mealIdToBeUpdated;

    


    public int getRestaurantManagerId() {
        return restaurantManagerId;
    }

    public void setRestaurantManagerId(int restaurantManagerId) {
        if(restaurantManagerId > 0) this.restaurantManagerId = restaurantManagerId;
        
        // on doit rafraîchir notre interface avec la nouvelle valeur du restaurantManagerId
        this.renderRestaurantMenu(); 
    }


    @FXML
    private TextField restaurantNameTextField;
    @FXML
    private TextField restaurantAddressTextField;
    @FXML
    private Label saveMessageLabel;
    @FXML
    private TextField mealsNameTextField;
    @FXML
    private TextField mealsDescripTextField;
    @FXML
    private Spinner<Double> priceSpinner;
    @FXML
    private Button addMealButton;
    @FXML
    private Button modifyMealButton;
    @FXML
    private Label addMealMessageLabel;
    @FXML
    private VBox menuVBox;

    

    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    private SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999.9);


    public void initialize() {
        
        try {
            this.valueFactory.setValue(0.5);
            this.priceSpinner.setValueFactory(valueFactory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }



    public void saveRestaurantNameAddressHandler(ActionEvent event) {
        try {
            
            if(this.serviceRestaurant.updateNameAddress(this.restaurant.getId(), this.restaurantNameTextField.getText(), this.restaurantAddressTextField.getText()) == true)
                this.saveMessageLabel.setText("Enregistré avec succés");

        } catch (SQLException e) {
            this.saveMessageLabel.setText("échec de l'enregistrement");
            System.out.println(e.getMessage());
        }
    }


    public void addMealsHandler(ActionEvent event) {

        Meal meal = new Meal(mealsNameTextField.getText(), priceSpinner.getValue(), mealsDescripTextField.getText());

        try {
            if(serviceRestaurant.addMealToMenu(this.restaurant.getId(), meal)) {
                this.addMealMessageLabel.setText("Repas ajouter avec succés");
                this.emptyAddMealField();
                this.renderRestaurantMenu();
            }
            else this.addMealMessageLabel.setText("échec lors de l'ajout de repas");
                
        } catch (SQLException e) {
            this.addMealMessageLabel.setText("échec lors de l'ajout de repas");
            System.out.println(e.getMessage());
        }
    }


    public void fieldResetHandler() {
        this.emptyAddMealField();
        this.addMealMessageLabel.setText("");
        this.addMealButton.setDisable(false);
        this.modifyMealButton.setDisable(true);
        
    }

    public void emptyAddMealField() {
        this.mealsNameTextField.setText("");
        this.mealsDescripTextField.setText("");

        this.valueFactory.setValue(0.5);
        this.priceSpinner.setValueFactory(this.valueFactory);
    }


    public void renderRestaurantMenu() {
        this.menuVBox.getChildren().clear();
        
        
        try {
            
            restaurant = serviceRestaurant.findByManagerId(this.restaurantManagerId);
            this.restaurantNameTextField.setText(restaurant.getName());
            this.restaurantAddressTextField.setText(restaurant.getAddress());
            ArrayList<Meal> menu = serviceRestaurant.deserializeJSONMenu(restaurant.getMenu());
            for (int i = 0; i < menu.size(); i++) {
                Meal meal = menu.get(i);
                int index = i;

                String mealRow = " " + meal.getName() + "    " + meal.getPrice() + " TND";

               
                Label label01 = new Label(mealRow); label01.setFont(new Font("Arial", 16));
                Button modifyMealBtn = new Button(" MODIFIER ");
                modifyMealBtn.setOnAction(event -> this.sendMealToModification(index, menu.get(index)));
                Button deleteMealBtn = new Button(" SUPRIMER ");
                deleteMealBtn.setOnAction(event -> { this.deleteMealHandler(index); });
                FlowPane flowPane = new FlowPane( label01, modifyMealBtn, deleteMealBtn);
                flowPane.setHgap(10);
                Label label02 = new Label("     "+meal.getDescription()); label02.setFont(new Font("Arial", 16));
                Separator separator = new Separator();
                this.menuVBox.setSpacing(15.0);
                this.menuVBox.getChildren().addAll(flowPane, label02, separator);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMealToModification(int index, Meal meal) {
        this.addMealButton.setDisable(true);
        this.modifyMealButton.setDisable(false);
        this.mealIdToBeUpdated = index;

        this.mealsNameTextField.setText(meal.getName());
        this.mealsDescripTextField.setText(meal.getDescription());
        this.valueFactory.setValue(meal.getPrice());
        this.priceSpinner.setValueFactory(this.valueFactory);
    }


    
    public void updateMeal() {
        try {
            boolean updateResult = this.serviceRestaurant.updateMeal(this.restaurant.getId(), this.mealIdToBeUpdated, this.mealsNameTextField.getText(), this.mealsDescripTextField.getText(), this.priceSpinner.getValue());
            
            if(updateResult) {
                this.addMealMessageLabel.setText("Repas modifier avec succés");
                this.emptyAddMealField();
                this.addMealButton.setDisable(false);
                this.modifyMealButton.setDisable(true);
            } else {
                this.addMealMessageLabel.setText("échec lors de la modification de repas");
            }
        } catch (SQLException e) {
            this.addMealMessageLabel.setText("échec lors de la modification de repas");
            System.out.println(e.getMessage());
        }
        this.renderRestaurantMenu();
    }


    public void deleteMealHandler(int mealIndex) {
        try {
            this.serviceRestaurant.deleteMeal(this.restaurant.getId(), mealIndex);
            this.renderRestaurantMenu();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void returnToCustomerOrderHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/customerOrdersList.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    

}
