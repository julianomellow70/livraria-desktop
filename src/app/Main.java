package app;

import controller.AutorFormularioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));

        primaryStage.setTitle("Sistema gerenciamento de livraria");
        primaryStage.setScene(new Scene(root, 1500, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
