package com.example.zo_login.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertCustomController {
    @FXML
    private Label lblMessage;

    private Stage stage;

    // Hàm này được gọi để thiết lập dữ liệu hiển thị
    public void setMessage(String message, Stage stage) {
        lblMessage.setText(message);
        this.stage = stage;
    }

    // Xử lý sự kiện khi nhấn nút OK
    @FXML
    private void onOkClicked() {
        if (stage != null) {
            stage.close(); // Đóng cửa sổ Alert
        }
    }
}
