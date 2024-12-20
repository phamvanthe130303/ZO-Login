package com.example.zo_login;

import com.example.zo_login.util.MessageUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("view/dashboard_main.fxml"),
                MessageUtil.getBundle());
        //màu icon: #e59866 , màu alert : #3C3F41
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ZO Login App");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/zo_login/images/avata.png")));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
