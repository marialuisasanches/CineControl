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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.dao.DaoFactory;
import model.dao.FilmeDao;
import model.dao.SalaDao;
import model.dao.SessaoDao;
import start.App;
import javafx.scene.control.ListCell;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class SessaoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Sessao> tblSessao;
    @FXML
    private TableColumn<Sessao, Integer> tblColId;
    @FXML
    private TableColumn<Sessao, Integer> tblColSala;
    @FXML
    private TableColumn<Sessao, String> tblColFilme;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private ComboBox<Sala> cmbSala;
    @FXML
    private ComboBox<Filme> cmbFilme;

    private List<Sessao> listaSessao;
    private ObservableList<Sessao> observableListSessao;

    private List<Filme> listaFilmes;
    private ObservableList<Filme> observableListFilmes;

    private List<Sala> listaSalas;
    private ObservableList<Sala> observableListSalas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarSessao();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        carregarCombos();
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Sessao sessaoSelecionada = tblSessao.selectionModelProperty().getValue().getSelectedItem();

        if (tblSessao.getItems().isEmpty()) {
            Alert alerta1 = new Alert(Alert.AlertType.WARNING);
            alerta1.setTitle("Aviso!");
            alerta1.setContentText("Tabela está vazia");
            alerta1.show();
            return;
        }
        if (sessaoSelecionada == null) {
            Alert alerta1 = new Alert(Alert.AlertType.WARNING);
            alerta1.setTitle("Aviso!");
            alerta1.setContentText("Selecione na tabela para excluir.");
            alerta1.show();
            return;
        }
        SessaoDao dao = DaoFactory.novoSessaoDao();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Aviso");
        alerta.setContentText("Confirmar exclusão da sessão do filme: " + sessaoSelecionada.getFilme() + "?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.get() == ButtonType.OK) {
            dao.excluir(sessaoSelecionada);
        }
        carregarSessao();
    }

    @FXML
    private void btnCadastrarOnAction(ActionEvent event) throws Exception {

        Filme filmeSelecionado = cmbFilme.getSelectionModel().getSelectedItem();
        Sala salaSelecionada = cmbSala.getSelectionModel().getSelectedItem();

        if (salaSelecionada == null || filmeSelecionado == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Selecione um Filme e uma Sala.");
            alerta.show();
            return;
        } else {
            Sessao sessao = new Sessao(filmeSelecionado, salaSelecionada);
            SessaoDao dao = DaoFactory.novoSessaoDao();

            try {
                dao.inserir(sessao);

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Aviso!");
                alerta.setContentText("Sessão cadastrada!");
                alerta.show();

                carregarSessao();

                cmbSala.getSelectionModel().clearSelection();
                cmbFilme.getSelectionModel().clearSelection();

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Aviso!");
                alerta.setContentText("Erro: " + e.getMessage());
                alerta.show();
            }
        }
    }

    @FXML
    private void btnAlterarOnAction(ActionEvent event) throws Exception {
        Sessao sessaoSelecionado = tblSessao.selectionModelProperty().getValue().getSelectedItem();
        Filme filmeNovo = cmbFilme.getSelectionModel().getSelectedItem();
        Sala salaNova = cmbSala.getSelectionModel().getSelectedItem();

        if (sessaoSelecionado == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Selecione na tabela para alterar.");
            alerta.show();
            return;
        }
        if (filmeNovo == null || salaNova == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso!");
            alerta.setContentText("Selecione o novo Filme e Sala.");
            alerta.show();
            return;
        }

        sessaoSelecionado.setSala(salaNova);
        sessaoSelecionado.setFilme(filmeNovo);

        SessaoDao dao = DaoFactory.novoSessaoDao();
        dao.editar(sessaoSelecionado);

        carregarSessao();

        cmbSala.getSelectionModel().clearSelection();
        cmbFilme.getSelectionModel().clearSelection();
    }

    public void carregarSessao() throws Exception {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColFilme.setCellValueFactory(new PropertyValueFactory<>("filmeNome"));
        tblColSala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));

        SessaoDao dao = DaoFactory.novoSessaoDao();
        listaSessao = dao.listar();

        observableListSessao = FXCollections.observableArrayList(listaSessao);
        tblSessao.setItems(observableListSessao);
    }

    public void carregarCombos() {
        carregarComboFilmes();
        carregarComboSalas();
    }

    public void carregarComboFilmes() {
        try {
            FilmeDao filmeDao = DaoFactory.novoFilmeDao();
            listaFilmes = filmeDao.listar();

            observableListFilmes = FXCollections.observableArrayList(listaFilmes);
            cmbFilme.setItems(observableListFilmes);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage()); 
        }
    }

    public void carregarComboSalas() {
        try {
            SalaDao salaDao = DaoFactory.novoSalaDao();
            listaSalas = salaDao.listar();

            observableListSalas = FXCollections.observableArrayList(listaSalas);
            cmbSala.setItems(observableListSalas);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
