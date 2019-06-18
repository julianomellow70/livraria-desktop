package controller;

import dao.EditoraDAO;
import dao.MunicipioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Autor;
import model.Editora;
import model.Municipio;

import java.util.List;


public class EditoraController {
    @FXML
   private TextField txfNome, txfEndereco, txfSite,txfBairro,txfTelefone ;
    @FXML
    private ComboBox cbMunicipio;
    @FXML
    private Button btnNovo, btnSalvar, btnTodos, btnUpdate, btnExcluir;
    @FXML
    private TableView<Editora> tableEditora;
    @FXML
    private TableColumn<Editora, Integer> colunaIDEditora;
    @FXML
    private TableColumn<Editora, String> colunaNomeEditora;
    @FXML
    private TableColumn<Editora, String> colunaEnderecoEditora;
    @FXML
    private TableColumn<Editora, String> colunaSiteEditora;
    @FXML
    private TableColumn<Editora, String> colunaBairroEditora;
    @FXML
    private TableColumn<Editora, String> colunaTelefoneEditora;
    @FXML
    private TableColumn<Editora, String> colunaMunicipioEditora;

    public void preencher_todos() {

        colunaIDEditora.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeEditora.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSiteEditora.setCellValueFactory(new PropertyValueFactory<>("site"));
        colunaEnderecoEditora.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaBairroEditora.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colunaTelefoneEditora.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaMunicipioEditora.setCellValueFactory(new PropertyValueFactory<>("nomeMunicipio"));


        tableEditora.setItems(lista_editora_tabela());
    }
    private ObservableList<Editora> lista_editora_tabela() {
        ObservableList<Editora> row_editora = FXCollections.observableArrayList();
        EditoraDAO editoraDAO = new EditoraDAO();
        List<Editora> editoras_retorno = editoraDAO.listarTodos();
        for (Editora ed : editoras_retorno) {
            row_editora.add(new Editora(ed.getId(),ed.getNome(),ed.getEndereco(),ed.getSite(),ed.getBairro(),ed.getTelefone(),ed.getNomeMunicipio()));
        }
        return row_editora;
    }
    public void initialize (){
        //preenchendo municipio
       preencherCBMunicipio();
       //preenchendo tabela
        preencher_todos();



    }
    public void preencherCBMunicipio (){
        MunicipioDAO municipioDAO = new MunicipioDAO();
        ObservableList<String> nomesMunicipios = FXCollections.observableArrayList();

        List<Municipio> municipios = municipioDAO.listar_todos_municipios();
        for (Municipio m : municipios){
            nomesMunicipios.add(m.getNome());
        }

        cbMunicipio.getItems().addAll(nomesMunicipios);
    }
    public void cliqueBotaoSalvar (){
       //setando informacoes para salvar
        //setando valores editora


        MunicipioDAO municipioDAO = new MunicipioDAO();
        Editora editora = new Editora();
        editora.setNome(txfNome.getText());
        editora.setBairro(txfBairro.getText());
        editora.setEndereco(txfEndereco.getText());
        editora.setMunicipio_id(municipioDAO.retornarIDMunicipio(String.valueOf(cbMunicipio.getValue())));
        editora.setSite(txfSite.getText());
        editora.setTelefone(txfTelefone.getText());


        EditoraDAO editoraDAO = new EditoraDAO();

        if (btnUpdate.isDisabled() && btnExcluir.isDisabled()){
            editoraDAO.alterar(editora);
        }else {
            //salvando informacoes
            editoraDAO.inserir(editora);
        }
        //preenchendo novamente a tabela
        preencher_todos();


        //limpando campos
        txfNome.setText("");
        txfBairro.setText("");
        txfEndereco.setText("");
        txfSite.setText("");
        txfTelefone.setText("");
        cbMunicipio.setValue("");
        preencherCBMunicipio();
        tableEditora.setDisable(false);
        bloquearcampos();

        btnSalvar.setDisable(true);
        btnNovo.setDisable(false);
        btnUpdate.setDisable(false);
        btnExcluir.setDisable(false);





    }

    public void cliqueBotaoNovo (){
        tableEditora.setDisable(true);
        btnSalvar.setDisable(false);
        btnNovo.setDisable(true);
        btnExcluir.setDisable(false);
        btnUpdate.setDisable(false);
        txfSite.setDisable(false);
        txfTelefone.setDisable(false);
        txfNome.setDisable(false);
        txfEndereco.setDisable(false);
        txfBairro.setDisable(false);
        cbMunicipio.setDisable(false);
    }

    public void bloquearcampos(){
        txfBairro.setDisable(true);
        txfEndereco.setDisable(true);
        txfNome.setDisable(true);
        txfTelefone.setDisable(true);
        txfSite.setDisable(true);
        cbMunicipio.setDisable(true);

    }
    public void cliqueBotaoUpdate(){
        btnUpdate.setDisable(true);
        btnNovo.setDisable(true);
        btnExcluir.setDisable(true);
        tableEditora.setDisable(true);

        txfBairro.setDisable(false);
        txfEndereco.setDisable(false);
        txfNome.setDisable(false);
        txfTelefone.setDisable(false);
        txfSite.setDisable(false);
        btnSalvar.setDisable(false);

        Editora editora = tableEditora.getSelectionModel().getSelectedItem();
        txfNome.setText(editora.getNome());
        txfTelefone.setText(editora.getTelefone());
        txfSite.setText(editora.getSite());
        txfEndereco.setText(editora.getEndereco());
        txfBairro.setText(editora.getBairro());
        cbMunicipio.setValue(editora.getNomeMunicipio());

    }

    public void cliqueBotaoExcluir(){
        Editora editora = tableEditora.getSelectionModel().getSelectedItem();
        EditoraDAO editoraDAO = new EditoraDAO();
        editoraDAO.excluir(editora);
        preencher_todos();
    }






}
