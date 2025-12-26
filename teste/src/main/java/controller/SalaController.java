/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Sala;
import model.dao.DaoFactory;
import model.dao.SalaDao;
import start.App;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class SalaController implements Initializable {

    @FXML
    private TextField txtNumeroSala;
    @FXML
    private TextField txtCapacidade;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCadastar;
    @FXML
    private Button btnAlterar;
    @FXML
    private TableView<Sala> tblSalas;
    @FXML
    private TableColumn<Sala, Integer> tblColId;
    @FXML
    private TableColumn<Sala, Integer> tblColNumeroSala;
    @FXML
    private TableColumn<Sala, Integer> tblColCapacidade;
    @FXML
    private Button btnVoltar;

    private List<Sala> listaSala;
    private ObservableList<Sala> observableListSala;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarSalas();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            //trocar para alerta
        }
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Sala salaSelecionada = tblSalas.getSelectionModel().getSelectedItem();

        if (tblSalas.getItems().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("A tabela está vazia.");
            alerta.show();
            return;
        }

        if (salaSelecionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Selecione uma sala para excluir.");
            alerta.show();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Aviso");
        alerta.setContentText("Confirmar exclusão da sala de número " + salaSelecionada.getNumeroSala() + "?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            SalaDao dao = DaoFactory.novoSalaDao();
            dao.excluir(salaSelecionada);
            carregarSalas();
        }

    }

    @FXML
    private void btnCadastarOnAction(ActionEvent event) throws Exception {
        Sala sala = new Sala();

        if (txtNumeroSala.getText().isEmpty() || txtCapacidade.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Preencha os dados corretamente.");
            alerta.show();
        } else {
            SalaDao dao = DaoFactory.novoSalaDao();

            sala.setNumeroSala(Integer.parseInt(txtNumeroSala.getText()));
            sala.setCapacidade(Integer.parseInt(txtCapacidade.getText()));

            dao.inserir(sala);
            carregarSalas();

            txtNumeroSala.clear();
            txtCapacidade.clear();
        }
    }

    @FXML
    private void btnAlterarOnAction(ActionEvent event) throws Exception {
        Sala salaSelecionada = tblSalas.selectionModelProperty().getValue().getSelectedItem();

        if (salaSelecionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Selecione uma sala para alterar.");
            alerta.show();
            return;
        }
        if (txtNumeroSala.getText().isEmpty() || txtCapacidade.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Preencha os dados corretamente.");
            alerta.show();
            return;
        }
        SalaDao dao = DaoFactory.novoSalaDao();

        salaSelecionada.setNumeroSala(Integer.parseInt(txtNumeroSala.getText()));
        salaSelecionada.setCapacidade(Integer.parseInt(txtCapacidade.getText()));

        dao.editar(salaSelecionada);
        carregarSalas();

        txtNumeroSala.clear();
        txtCapacidade.clear();

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }

    public void carregarSalas() throws Exception {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        tblColCapacidade.setCellValueFactory(new PropertyValueFactory<>("capacidade"));

        SalaDao dao = DaoFactory.novoSalaDao();
        listaSala = dao.listar();

        observableListSala = FXCollections.observableArrayList(listaSala);
        tblSalas.setItems(observableListSala);
    }
}
