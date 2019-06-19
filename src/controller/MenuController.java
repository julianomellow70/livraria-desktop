package controller;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    AnchorPane telasPane;

    public void mostrar_autores() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/autor_formulario.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sistema gerenciamento de livraria");
        primaryStage.setScene(new Scene(root, 745, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void mostrar_editoras() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/editora_formulario.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sistema gerenciamento de livraria");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void mostrar_livros() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/livro_formulario.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sistema gerenciamento de livraria");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}



