/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package animasigame;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button pbo;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScaleTransition trans =new ScaleTransition();
        trans.setNode(pbo);
        trans.setDuration(Duration.seconds(2));
        trans.setByX(2);
        trans.setByY(2);
        
        trans.setCycleCount(1);
        trans.play();
    }    
    
}
