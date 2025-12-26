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
import model.Filme;
import model.dao.DaoFactory;
import model.dao.FilmeDao;
import start.App;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class FilmeController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtDuracao;
    @FXML
    private TextField txtGenero;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private TableView<Filme> tblFilmes;
    @FXML
    private TableColumn<Filme, Integer> tblColId;
    @FXML
    private TableColumn<Filme, String> tblColTitulo;
    @FXML
    private TableColumn<Filme, Integer> tblColDuracao;
    @FXML
    private TableColumn<Filme, String> tblColGenero;

    private List<Filme> listaFilme;
    private ObservableList<Filme> observableListFilme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarFilmes();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //criar alerta aqui e mostrar o erro através de um INFORMATION.
        }
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Filme filmeSelecionado = tblFilmes.selectionModelProperty().getValue().getSelectedItem();

        if (tblFilmes.getItems().isEmpty()) {
            Alert alerta1 = new Alert(Alert.AlertType.WARNING);
            alerta1.setTitle("Aviso!");
            alerta1.setContentText("Tabela está vazia");
            alerta1.show();
            return;
        }
        if (filmeSelecionado == null) {
            Alert alerta1 = new Alert(Alert.AlertType.WARNING);
            alerta1.setTitle("Aviso!");
            alerta1.setContentText("Selecione na tabela para excluir.");
            alerta1.show();
            return;
        }
        FilmeDao dao = DaoFactory.novoFilmeDao();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Aviso");
        alerta.setContentText("Confirmar exclusão de " + filmeSelecionado.getTitulo() + "?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.get() == ButtonType.OK) {
            dao.excluir(filmeSelecionado);
        }
        carregarFilmes();
    }

    @FXML
    private void btnCadastrarOnAction(ActionEvent event) throws Exception {
        Filme filme = new Filme();

        if (txtTitulo.getText().isEmpty() || txtDuracao.getText().isEmpty() || txtGenero.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Preencha os dados corretamente.");
            alerta.show();
        } else {
            FilmeDao dao = DaoFactory.novoFilmeDao();

            filme.setTitulo(txtTitulo.getText());
            filme.setDuracao(Integer.parseInt(txtDuracao.getText()));
            filme.setGenero(txtGenero.getText());

            dao.inserir(filme);
            carregarFilmes();

            txtTitulo.clear();
            txtDuracao.clear();
            txtGenero.clear();
        }
    }

    @FXML
    private void btnAlterarOnAction(ActionEvent event) throws Exception {
        Filme filmeSelecionado = tblFilmes.selectionModelProperty().getValue().getSelectedItem();

        if (filmeSelecionado == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Selecione na tabela para alterar.");
            alerta.show();
            return;
        }
        if (txtTitulo.getText().isEmpty() || txtDuracao.getText().isEmpty() || txtGenero.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Preencha os dados corretamente.");
            alerta.show();
            return;
        }
        FilmeDao dao = DaoFactory.novoFilmeDao();

        filmeSelecionado.setTitulo(txtTitulo.getText());
        filmeSelecionado.setDuracao(Integer.parseInt(txtDuracao.getText()));
        filmeSelecionado.setGenero(txtGenero.getText());

        dao.editar(filmeSelecionado);
        carregarFilmes();

        txtTitulo.clear();
        txtDuracao.clear();
        txtGenero.clear();

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }

    public void carregarFilmes() throws Exception {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tblColGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tblColDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));

        FilmeDao dao = DaoFactory.novoFilmeDao();
        listaFilme = dao.listar();

        observableListFilme = FXCollections.observableArrayList(listaFilme);
        tblFilmes.setItems(observableListFilme);
    }
}
