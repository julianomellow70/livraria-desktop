package controller;

import dao.EstadoDAO;
import dao.MunicipioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Estado;
import model.Municipio;

import java.util.List;

public class EstadoController {

    @FXML
    private TextField txfNome;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Estado> tableEstado;
    @FXML
    private TableColumn<Estado, Integer> colunaIDEstado;
    @FXML
    private TableColumn<Estado, String> colunaNomeEstado;

    public void initialize (){
        preencher_todos();
    }

    public void cliqueBotaoExcluir(){
        Estado e = tableEstado.getSelectionModel().getSelectedItem();
        EstadoDAO ed = new EstadoDAO();
        ed.deletar(e);
        preencher_todos();
    }
    public void cliqueBotaoUpdate(){
        btnUpdate.setDisable(true);
        Estado e = tableEstado.getSelectionModel().getSelectedItem();
        txfNome.setText(e.getUF());
    }
    public void cliqueBotaoSalvar(){
        Estado e = new Estado();
        EstadoDAO ed = new EstadoDAO();
        e.setUF(txfNome.getText());
        if(btnUpdate.isDisable()){
            Estado estadoUpdate = tableEstado.getSelectionModel().getSelectedItem();
            e.setId(estadoUpdate.getId());
        ed.alterar(e);


        }else{
            ed.inserir(e);
        }
        txfNome.setText("");
        preencher_todos();
        btnUpdate.setDisable(false);
    }

    public void preencher_todos() {


        colunaIDEstado.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeEstado.setCellValueFactory(new PropertyValueFactory<>("UF"));



        tableEstado.setItems(lista_estado_tabela());
    }
    private ObservableList<Estado> lista_estado_tabela() {
        ObservableList<Estado> row_estado = FXCollections.observableArrayList();

        EstadoDAO estadoDAO = new EstadoDAO();

        List<Estado> estados = estadoDAO.listar_todos_estados();
        for (Estado e : estados) {
            row_estado.add(new Estado(e.getId(), e.getUF()));
        }
        return row_estado;
    }



}
