package controller;

import dao.AutorDAO;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Autor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AutorFormularioController {

@FXML
    private TextField txfNome;
@FXML
    private TextField txfEmail;
@FXML
    private Button btnSalvar;
@FXML
    private TableView tableAutores;
@FXML
    private TableColumn colunaIDAutor;
@FXML
    private TableColumn colunaNomeAutor;
@FXML
    private TableColumn colunaEmailAutor;



public void salvar(){


    Autor autor = new Autor();
    autor.setNome(txfNome.getText());
    autor.setEmail(txfEmail.getText());

    AutorDAO autorDAO = new AutorDAO();
    autorDAO.inserir(autor);

    limparCampos();

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

    public void preencher_autores (){
    Autor autor = new Autor();
    AutorDAO autorDAO = new AutorDAO();


    autor.setNome(txfNome.getText());
    autor.setEmail(txfEmail.getText());
    List<Autor> autores = new ArrayList<>();
    autores = autorDAO.listar_porCampos(autor);

    for(Autor autor_result : autores){

        System.out.println(autor_result.getNome());
        System.out.println(autor_result.getEmail());

    }
    }


}
