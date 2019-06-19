package controller;

import dao.EditoraDAO;
import dao.LivroDAO;
import dao.MunicipioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Editora;
import model.Livro;
import model.Municipio;

import java.util.List;

public class LivroController {
    @FXML
    private TextField txfTitulo;
    @FXML
    private TextField txfData;
    @FXML
    private TextField txfQtde;
    @FXML
    private TextField txfPreco;
    @FXML
    private ComboBox cbEditora;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnNovoEditora;
    @FXML
    private TableView <Livro> tableLivro;
    @FXML
    private TableColumn<Editora, Integer> colunaIDLivro;
    @FXML
    private TableColumn<Editora, String> colunaDataLivro;
    @FXML
    private TableColumn<Editora, String> colunaTituloLivro;
    @FXML
    private TableColumn<Editora, Integer> colunaQtdeLivro;
    @FXML
    private TableColumn<Editora, Float> colunaPrecoLivro;
    @FXML
    private TableColumn<Editora, String> colunaEditoraLivro;

    public void cliqueBotaoUpdate (){
        btnUpdate.setDisable(true);
        btnExcluir.setDisable(true);
        tableLivro.setDisable(true);
        btnNovo.setDisable(true);
        btnSalvar.setDisable(false);
        btnNovoEditora.setDisable(false);

        Livro lu = tableLivro.getSelectionModel().getSelectedItem();
        txfData.setText(lu.getData_lancamento());
        txfPreco.setText(String.valueOf(lu.getPreco()));
        txfQtde.setText(String.valueOf(lu.getQtde()));
        txfTitulo.setText(lu.getTitulo());
        cbEditora.setValue(lu.getNome_editora());
        campos(false);
    }
    public void cliqueBotaoExcluir (){
        Livro livro = tableLivro.getSelectionModel().getSelectedItem();
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.excluir(livro);
        preencher_todos();
    }
    public void cliqueBotaoNovoEditora(){

    }
    public void cliqueBotaoSalvar (){
       Livro livro = new Livro();
       LivroDAO livroDAO = new LivroDAO();
       EditoraDAO editoraDAO = new EditoraDAO();
       livro.setTitulo(txfTitulo.getText());
       livro.setPreco(Float.parseFloat(txfPreco.getText()));
       livro.setQtde(Integer.parseInt(txfQtde.getText()));
       livro.setData_lancamento(txfData.getText());
       livro.setEditora_id(editoraDAO.retornarIDEditora(String.valueOf(cbEditora.getValue())));
        Livro lu = tableLivro.getSelectionModel().getSelectedItem();
        livro.setId(lu.getId());


        if (btnUpdate.isDisable() && btnExcluir.isDisable()){

            livroDAO.alterar(livro);
            System.out.println("Alterado!!!!!");
        }else{
        livroDAO.inserir(livro);
            System.out.println("Salvo!!!!!!");
        }
        preencher_todos();
        limpar_campos();
        campos(true);

        btnExcluir.setDisable(false);
        btnUpdate.setDisable(false);
        tableLivro.setDisable(false);
        btnNovoEditora.setDisable(true);

    }
    public void cliqueBotaoNovo (){
        campos(false);
        btnSalvar.setDisable(false);
        btnNovoEditora.setDisable(false);
        btnNovo.setDisable(true);
    }
    public void limpar_campos (){
        txfTitulo.setText("");
        txfQtde.setText("");
        txfPreco.setText("");
        txfData.setText("");
        cbEditora.setValue("");
    }
    public void campos(boolean situacao){

        txfData.setDisable(situacao);
        txfPreco.setDisable(situacao);
        txfQtde.setDisable(situacao);
        txfTitulo.setDisable(situacao);
        cbEditora.setDisable(situacao);


    }

    public void initialize (){
        preencherCBEditora();
        preencher_todos();
    }

    public void preencherCBEditora (){
        EditoraDAO editoraDAO = new EditoraDAO();
        ObservableList<String> nomesEditoras = FXCollections.observableArrayList();

        List<Editora> editoras = editoraDAO.listarTodos();
        for (Editora e : editoras){
            nomesEditoras.add(e.getNome());
        }

        cbEditora.getItems().addAll(nomesEditoras);
    }

    public void preencher_todos() {

        colunaIDLivro.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaTituloLivro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaDataLivro.setCellValueFactory(new PropertyValueFactory<>("data_lancamento"));
        colunaPrecoLivro.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaQtdeLivro.setCellValueFactory(new PropertyValueFactory<>("qtde"));
        colunaEditoraLivro.setCellValueFactory(new PropertyValueFactory<>("nome_editora"));

        tableLivro.setItems(lista_livro_tabela());
    }
    private ObservableList<Livro> lista_livro_tabela() {
        ObservableList<Livro> row_livro = FXCollections.observableArrayList();
        LivroDAO livroDAO = new LivroDAO();
        List<Livro> livros_retorno = livroDAO.listarTodos();
        for (Livro l : livros_retorno) {
            row_livro.add(new Livro(l.getId(),l.getQtde(),l.getTitulo(),l.getData_lancamento(),l.getPreco(),l.getNome_editora()));
        }
        return row_livro;
    }



}
