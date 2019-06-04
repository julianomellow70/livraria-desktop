package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/autor_formulario.fxml"));
        primaryStage.setTitle("Sistema gerenciamento de livraria");
        primaryStage.setScene(new Scene(root, 740, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
