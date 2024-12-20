package com.example.zo_login.util;

import com.example.zo_login.controller.AlertCustomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomAlert {

    public static void showAlert(String message) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(CustomAlert.class.getResource("/com/example/zo_login/view/alert_custom.fxml"),MessageUtil.getBundle());
            Parent root = loader.load();

            // Lấy controller để thiết lập dữ liệu
            AlertCustomController controller = loader.getController();

            // Tạo một cửa sổ Alert mới
            Stage alertStage = new Stage();
            alertStage.initModality(Modality.APPLICATION_MODAL); // Đặt thành modal
            alertStage.initStyle(StageStyle.UNDECORATED);

            alertStage.setScene(new Scene(root));

            // Gửi dữ liệu đến controller
            controller.setMessage(message, alertStage);

            // Hiển thị Alert
            alertStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
