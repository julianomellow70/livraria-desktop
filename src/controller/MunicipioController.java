package controller;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dao.AutorDAO;
import dao.EstadoDAO;
import dao.MunicipioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Autor;
import model.Editora;
import model.Estado;
import model.Municipio;

import java.util.List;

public class MunicipioController {
@FXML
    private TextField txfNome;
@FXML
    private ComboBox cbuf;
@FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView <Municipio> tableMunicipio;
    @FXML
    private TableColumn<Municipio, Integer> colunaIDMunicipio;
    @FXML
    private TableColumn<Municipio, String> colunaNomeMunicipio;
    @FXML
    private TableColumn<Municipio, String> colunaUFMunicipio;

    public void preencher_todos() {


        colunaIDMunicipio.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeMunicipio.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaUFMunicipio.setCellValueFactory(new PropertyValueFactory<>("uf"));


        tableMunicipio.setItems(lista_municipio_tabela());
    }
    private ObservableList<Municipio> lista_municipio_tabela() {
        ObservableList<Municipio> row_municipio = FXCollections.observableArrayList();
        MunicipioDAO municipioDAO = new MunicipioDAO();
        List<Municipio> municipios = municipioDAO.listar_todos_municipios_tabela();
        for (Municipio m : municipios) {
            row_municipio.add(new Municipio(m.getId(), m.getNome(), m.getUf()));
        }
        return row_municipio;
    }
    public void initialize () {
        preencher_todos();
        preencherCBEstado();
    }

    public void preencherCBEstado (){
        EstadoDAO estadoDAO = new EstadoDAO();
        ObservableList<String> nomesEstados = FXCollections.observableArrayList();

        List<Estado> estados = estadoDAO.listar_todos_estados();
        for (Estado d : estados){
            nomesEstados.add(d.getUF());
        }

        cbuf.getItems().addAll(nomesEstados);
    }

    public void cliqueBotaoSalvar (){
        EstadoDAO estadoDAO = new EstadoDAO();
        Municipio m = new Municipio();
        m.setNome(txfNome.getText());
        m.setEstado_id(estadoDAO.retornarIDEstado(String.valueOf(cbuf.getValue())));

        MunicipioDAO md = new MunicipioDAO();

        if (btnUpdate.isDisable()){
            System.out.println("Alterado!!!");
           md.alterar(m);
        }else {
            System.out.println("salvo!!!");

            md.inserir(m);
        }

        preencher_todos();
        btnUpdate.setDisable(false);
        txfNome.setText("");
        cbuf.setValue("");
    }

    public void cliqueBotaoUpdate (){
        btnUpdate.setDisable(true);
     Municipio m = tableMunicipio.getSelectionModel().getSelectedItem();
     txfNome.setText(m.getNome());
     cbuf.setValue(m.getUf());
    }

    public void cliqueBotaoExcluir (){
        Municipio m = tableMunicipio.getSelectionModel().getSelectedItem();
        MunicipioDAO md = new MunicipioDAO();
        md.excluir(m);

        preencher_todos();

    }
}
