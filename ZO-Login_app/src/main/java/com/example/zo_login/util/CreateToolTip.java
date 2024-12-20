package com.example.zo_login.util;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class CreateToolTip {

//    public static void createTooltip(Button node, String text, String position) {
//        // Tạo Popup cho Tooltip
//        Popup tooltipPopup = new Popup();
//        Region background = new Region();
//        background.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-radius: 10px;"); // Màu và bo tròn góc
//        background.setPrefSize(200, 100);
//        tooltipPopup.getContent().add(background);
//
//        // Xử lý khi hover vào Node
//        node.setOnMouseEntered(event -> {
//            showTooltip(event, tooltipPopup, node, position);
//        });
//
//        node.setOnMouseExited(event -> {
//
//            tooltipPopup.hide();
//        });
//    }

    public static void createTooltip(Button node, String text, String position) {
        // Tạo Popup cho Tooltip
        Popup tooltipPopup = new Popup();

        // Tạo Label chứa text
        Label label = new Label(text);
        label.setFont(Font.font(20));
        label.setTextFill(Color.web("#ffffff"));
        label.setStyle("-fx-background-color: #504E4E; -fx-padding: 5px; -fx-background-radius: 5px;");

        // Tạo StackPane để bo tròn và chứa Label
        StackPane background = new StackPane(label);
        background.setStyle("-fx-background-radius: 5px;");
//        background.setMaxWidth(300); // Giới hạn chiều rộng tối đa
        background.setPrefHeight(node.getHeight()); // Chiều cao theo Button
        background.setMinHeight(node.getHeight());

        // Thêm vào Popup
        tooltipPopup.getContent().add(background);

        // Xử lý khi hover vào Button
        node.setOnMouseEntered(event -> {
            showTooltip(event, tooltipPopup, node, position);
        });

        node.setOnMouseExited(event -> {
            tooltipPopup.hide();
        });
    }


    private static void showTooltip(MouseEvent event, Popup popup, Button node, String position) {
        Point2D point = node.localToScreen(0, 0);
        double x = point.getX();
        double y = point.getY();

        // Xác định vị trí hiển thị
        switch (position.toLowerCase()) {
            case "left":
                popup.show(node, x - popup.getWidth() - 5, y);
                break;
            case "right":
                popup.show(node, x + node.getWidth() + 5, y);
                break;
            case "top":
                popup.show(node, x, y - node.getHeight() - 5);
                break;
            case "bottom":
                popup.show(node, x, y + node.getHeight() + 5);
                break;
        }
    }

}
