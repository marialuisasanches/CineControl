/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import start.App;

/**
 * FXML Controller class
 *
 * @author Malu Sanches
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private Button btnFilme;

    @FXML
    private Button btnSalas;
    
    @FXML
    private Button btnSessao;

    @FXML
    void btnFilmeOnAction(ActionEvent event) throws Exception{
        App.setRoot("Filme");
    }

    @FXML
    void btnSalasOnAction(ActionEvent event) throws Exception{
        App.setRoot("Sala");
    }
    
    @FXML
    void btnSessaoOnAction(ActionEvent event) throws Exception{
        App.setRoot("Sessao");
    }
}
