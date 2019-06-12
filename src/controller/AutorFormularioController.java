package controller;

import dao.AutorDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Autor;

import java.util.List;
import java.util.Optional;

public class AutorFormularioController  {



    @FXML
    private TextField txfNome;
    @FXML
    private TextField txfEmail;
    @FXML
    private Button btnSalvar;
    @FXML
    private TableView<Autor> tableAutores;
    @FXML
    private TableColumn<Autor, Integer> colunaIDAutor;
    @FXML
    private TableColumn<Autor, String> colunaNomeAutor;
    @FXML
    private TableColumn<Autor, String> colunaEmailAutor;
    @FXML
    private Button btnTodos;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnUpdate;







    public AutorFormularioController (){
        //this.txfNome = new TextField();
        //this.txfNome.setText("teste");
        //this.txfNome.setEditable(false);
    }

    @FXML
    public void initialize(){

    }

    public void passarMouseBotaoTodos (){
    btnTodos.setStyle("-fx-background-color: #000000;");
    btnTodos.setTextFill(Color.WHITE);
    }
    public void tirarMouseBotaoTodos(){
        btnTodos.setStyle("-fx-background-color: #D2691E;");
        btnTodos.setTextFill(Color.BLACK);
    }
    public void passarMouseBotaoExcluir (){
        btnExcluir.setStyle("-fx-background-color: #000000;");
        btnExcluir.setTextFill(Color.WHITE);
    }
    public void tirarMouseBotaoExcluir(){
        btnExcluir.setStyle("-fx-background-color: #B22222;");
        btnExcluir.setTextFill(Color.WHITE);
    }
    public void passarMouseBotaoUpdate (){
        btnUpdate.setStyle("-fx-background-color: #000000;");
        btnUpdate.setTextFill(Color.WHITE);
    }
    public void tirarMouseBotaoUpdate(){
        btnUpdate.setStyle("-fx-background-color: #B22222;");
        btnUpdate.setTextFill(Color.WHITE);
    }

    public void preencher_todos() {


        colunaIDAutor.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeAutor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmailAutor.setCellValueFactory(new PropertyValueFactory<>("email"));


        tableAutores.setItems(lista_autores_tabela());
    }


    public void startUpdate(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/autor_formulario_update.fxml"));

        primaryStage.setTitle("Sistema gerenciamento de livraria");
        primaryStage.setScene(new Scene(root, 370, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void cliqueMouseBotaoUpdate () throws Exception {
       Autor autor;
        autor = tableAutores.getSelectionModel().getSelectedItem();
        startUpdate(new Stage());


    }


    private ObservableList<Autor> lista_autores_tabela() {
        ObservableList<Autor> row_autor = FXCollections.observableArrayList();
        AutorDAO autorDAO = new AutorDAO();
        List<Autor> autores = autorDAO.listarTodos();
        for (Autor autor_l : autores) {
            row_autor.add(new Autor(autor_l.getId(), autor_l.getNome(), autor_l.getEmail()));
        }
        return row_autor;
    }

    public void salvar() {


        Autor autor = new Autor();
        autor.setNome(txfNome.getText());
        autor.setEmail(txfEmail.getText());

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.inserir(autor);

        limparCampos();
        preencher_todos();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de autores");
        alert.setHeaderText("Cadastro de autores");
        alert.setContentText("Autor cadastrado com sucesso");
        alert.showAndWait();
    }

    private void limparCampos() {
        txfNome.setText("");
        txfEmail.setText("");
    }

    public void cliqueMouseBotaoExcluir (){
        Autor autor = tableAutores.getSelectionModel().getSelectedItem();
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.deletar(autor);
        preencher_todos();
    }


}
