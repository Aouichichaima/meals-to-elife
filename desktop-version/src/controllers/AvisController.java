package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import models.AvisService;
import models.Restaurant;
import models.ServiceRestaurant;

public class AvisController implements Initializable {

    
    @FXML
    private Spinner<Integer> noteRestoSpinner;
    @FXML
    private TextField noticeTextField;
    @FXML
    private ChoiceBox<String> restaurantChoiceBox;

    ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
    AvisService avisService = new AvisService();
    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5);
    private int idClient;
    private int idRestaurant;
    private List<Restaurant> restaurants;



    public void setIdClient(int id) {
        this.idClient = id;
        this.render();
    }


    public void render() {
        

        try {
            this.restaurants = this.serviceRestaurant.readAll();

            for (Restaurant restaurant : restaurants) {
                restaurantChoiceBox.getItems().add(restaurant.getName());
                
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void sendNotice(Event action) {

        for (Restaurant restaurant : restaurants) {
            if(restaurant.getName() == restaurantChoiceBox.getValue()) this.idRestaurant = restaurant.getId();
        }

        try {
            avisService.createAvis(this.idClient, this.idRestaurant, this.noteRestoSpinner.getValue(), this.noticeTextField.getText());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            this.valueFactory.setValue(0);
            this.noteRestoSpinner.setValueFactory(valueFactory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
