package com.example.zo_login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ZoomableGrid extends Application {
    private double mouseAnchorX;
    private double mouseAnchorY;
    private double initialTranslateX;
    private double initialTranslateY;
    private double dragOffsetX;
    private double dragOffsetY;

    @Override
    public void start(Stage primaryStage) {
        // Tạo GridPane
        GridPane grid = new GridPane();

        int cellSize = 20; // Kích thước mỗi ô
        int lineSize = 40;
        for (int row = 0; row <= lineSize; row++) { // Duyệt hàng
            for (int col = 0; col <= lineSize; col++) { // Duyệt cột
                // Vẽ đường ngang
                if (row < lineSize) {
                    Line horizontalLine = new Line(0, 0, cellSize, 0);
                    horizontalLine.setStroke(Color.BLACK);
                    grid.add(horizontalLine, col, row);
                }

                // Vẽ đường dọc
                if (col < lineSize) {
                    Line verticalLine = new Line(0, 0, 0, cellSize);
                    verticalLine.setStroke(Color.BLACK);
                    grid.add(verticalLine, col, row);
                }
            }
        }

        // Tạo Scale để phóng to/thu nhỏ
        Scale scale = new Scale();
        grid.getTransforms().add(scale);

        // Thêm sự kiện zoom
        grid.setOnScroll(e -> {
            double zoomFactor = e.getDeltaY() > 0 ? 1.1 : 0.9;
            scale.setX(scale.getX() * zoomFactor);
            scale.setY(scale.getY() * zoomFactor);
        });

        // Thêm sự kiện kéo
        grid.setOnMousePressed(this::handleMousePressed);
        grid.setOnMouseDragged(this::handleMouseDragged);

        // Tạo StackPane để chứa GridPane
        StackPane root = new StackPane(grid);

        // Tạo Scene và hiển thị Stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Draggable and Zoomable Grid");
        primaryStage.show();
    }

    private void handleMousePressed(MouseEvent event) {
        mouseAnchorX = event.getSceneX();
        mouseAnchorY = event.getSceneY();

        GridPane grid = (GridPane) event.getSource();
        initialTranslateX = grid.getTranslateX();
        initialTranslateY = grid.getTranslateY();
    }

    private void handleMouseDragged(MouseEvent event) {
        double deltaX = event.getSceneX() - mouseAnchorX;
        double deltaY = event.getSceneY() - mouseAnchorY;

        GridPane grid = (GridPane) event.getSource();
        grid.setTranslateX(initialTranslateX + deltaX);
        grid.setTranslateY(initialTranslateY + deltaY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
