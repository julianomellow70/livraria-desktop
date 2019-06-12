package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Autor;

public class AutorFormularioControllerUpdate {
    Autor autor;
    public AutorFormularioControllerUpdate(Autor autor){
        this.autor = autor;

    }


    @FXML
    private Button btnSalvarUpdate;

    @FXML
    private TextField txfNomeUpdate;
    @FXML
    private TextField txfEmailUpdate, txfIDUpdate;
}
