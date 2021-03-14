package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("quantum.multithreading", "true");
        System.setProperty("javafx.animation.fullspeed", "true");
        System.setProperty("javafx.animation.pulse", "100");
        System.setProperty("javafx.animation.framerate", "100");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setOpacity(0.98);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(375);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
