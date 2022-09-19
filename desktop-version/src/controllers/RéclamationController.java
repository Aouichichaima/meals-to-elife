package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RéclamationController {


    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> NomRestaurant;

    @FXML
    private TableColumn<?, ?> Avis;
    @FXML
    private Rating numberoneRating ,fastfoodRating ;
    @FXML
    private void handleButtonAction (ActionEvent event) {
        System.out.println("NUMBERONE Rating given by user:" +NumberOneRating.getRating());
        System.out.println("l'ETS Rating given by user:" +LetsRating.getRating());
    }



    
    
}
