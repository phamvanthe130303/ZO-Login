package com.example.zo_login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/zo_login/view/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("AHHHHH");
//        stage.getIcons().add(new Image(""));
        stage.setMaximized(true);
        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
