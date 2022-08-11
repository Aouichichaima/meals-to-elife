package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IndexController {

    @FXML
    private Label label01;
    
    public void sayWelcome() {
        label01.setText("Welcome To Meals to Elife Application");
        System.out.println("Welcome To Meals to Elife Application");
    }

}
