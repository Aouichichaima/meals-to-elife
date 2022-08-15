package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import models.Meal;
import models.Restaurant;
import models.ServiceRestaurant;

public class RestaurantSettingsController {

    private Restaurant restaurant = null;
    private int restaurantManagerId = 1; // this value of the logged in restaurant manager will come later from the login form

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
    private Label addMealMessageLabel;

    

    private ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    private SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999.9);


    public void initialize() {
        try {
            restaurant = serviceRestaurant.findByManagerId(this.restaurantManagerId);
            this.restaurantNameTextField.setText(restaurant.getName());
            this.restaurantAddressTextField.setText(restaurant.getAddress());
            ArrayList<Meal> menu = serviceRestaurant.deserializeJSONMenu(restaurant.getMenu());

            for (int i = 0; i < menu.size(); i++) {
                System.out.println(menu.get(i).getName());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.valueFactory.setValue(0.5);
        this.priceSpinner.setValueFactory(valueFactory);
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
        
    }

    public void emptyAddMealField() {
        this.mealsNameTextField.setText("");
        this.mealsDescripTextField.setText("");

        this.valueFactory.setValue(0.5);
        this.priceSpinner.setValueFactory(this.valueFactory);
    }
    
}
