package com.example.zo_login.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfileController {

    @FXML
    private TabPane idTabPane;

    @FXML
    public void initialize() {
        // Lắng nghe sự kiện nhấp đúp chuột trên TabPane
        idTabPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2) { // Kiểm tra nhấp đúp
                Tab selectedTab = idTabPane.getSelectionModel().getSelectedItem();
                if (selectedTab != null) {
                    enableTabRename(selectedTab);
                }
            }
        });
    }

    private void enableTabRename(Tab tab) {
        String originalTitle = tab.getText(); // Lưu tên gốc
        TextField textField = new TextField(originalTitle);
        boolean[] isEditing = {false}; // Cờ để kiểm tra trạng thái chỉnh sửa

        // Khi nhấn Enter, cập nhật tên tab
        textField.setOnAction(event -> saveTabName(tab, textField));

        // Khi nhấn Escape, hủy chỉnh sửa
        textField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE -> cancelTabRename(tab, originalTitle);
                case ENTER -> saveTabName(tab, textField);
            }
        });

        // Hủy chỉnh sửa khi mất focus
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                isEditing[0] = true;
            } else if (isEditing[0]) {
                saveTabName(tab, textField); // Chỉ lưu tên nếu đang chỉnh sửa
            }
        });

        // Đặt TextField làm Graphic của tab
        tab.setText(null); // Ẩn văn bản gốc
        tab.setGraphic(textField); // Hiển thị TextField
        textField.requestFocus(); // Tự động focus
        textField.selectAll(); // Chọn toàn bộ văn bản
    }

    private void saveTabName(Tab tab, TextField textField) {
        String newName = textField.getText().trim();

        // Nếu tên trống, đặt tên mặc định
        if (newName.isEmpty()) {
            newName = "New Tab";
        }

        tab.setText(newName); // Cập nhật tên mới
        tab.setGraphic(null); // Xóa TextField
    }

    private void cancelTabRename(Tab tab, String originalTitle) {
        tab.setText(originalTitle); // Khôi phục tên gốc
        tab.setGraphic(null); // Xóa TextField
    }
}
