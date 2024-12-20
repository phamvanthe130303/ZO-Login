package com.example.zo_login;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestScriptEditor extends Application {
    private Pane canvasPane  = new Pane();
    private List<ScriptElement> elements = new ArrayList<>();
    private Gson gson = new Gson();
    private ScriptElement selectedElement = null;
    private Line tempLine = null;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Lưới trung tâm
        canvasPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc;");
        canvasPane.setPrefSize(800, 600);

        // Xử lý kéo thả phần tử vào lưới
        setupCanvasDragAndDrop();

        ScrollPane scrollPane = new ScrollPane(canvasPane);
        root.setCenter(scrollPane);

        // Thanh chọn phần tử (bên trái)
        VBox leftPanel = createSelectionPanel();
        root.setLeft(leftPanel);

        // Bảng cấu hình (bên phải)
        VBox rightPanel = createConfigurationPanel();
        root.setRight(rightPanel);

        // Thanh menu xuất/đọc file
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        Scene scene = new Scene(root, 200, 180);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kịch bản kiểm thử");
        primaryStage.show();
    }

    //hàm kéo phần tử vào lưới
    private void setupCanvasDragAndDrop() {
        canvasPane.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.COPY);
            }
            e.consume();
        });

        canvasPane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                String itemType = db.getString();
                if ("Button".equals(itemType)) {
                    Button newButton = createDraggableButton("Button", e.getX(), e.getY());
                    canvasPane.getChildren().add(newButton);
                    elements.add(new ScriptElement("Button", e.getX(), e.getY(), new HashMap<>()));
                } else if ("Input".equals(itemType)) {
                    TextField newInput = createDraggableInput(e.getX(), e.getY());
                    canvasPane.getChildren().add(newInput);
                    elements.add(new ScriptElement("Input", e.getX(), e.getY(), new HashMap<>()));
                }
            }
            e.setDropCompleted(true);
            e.consume();
        });
    }

    private VBox createSelectionPanel() {
        VBox panel = new VBox();
        panel.setSpacing(10);
        panel.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        Button btnElement = new Button("Button");
        btnElement.setOnDragDetected(e -> startDrag(btnElement, "Button"));

        Button inputElement = new Button("Input");
        inputElement.setOnDragDetected(e -> startDrag(inputElement, "Input"));

        panel.getChildren().addAll(btnElement, inputElement);
        return panel;
    }

    private void startDrag(Button source, String type) {
        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(type);
        db.setContent(content);
    }

    private VBox createConfigurationPanel() {
        VBox panel = new VBox();
        panel.setSpacing(10);
        panel.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9;");

        Label configLabel = new Label("Cấu hình:");
        TextArea configDetails = new TextArea();
        panel.getChildren().addAll(configLabel, configDetails);
        return panel;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem saveItem = new MenuItem("Xuất kịch bản");
        saveItem.setOnAction(e -> saveScript());

        MenuItem loadItem = new MenuItem("Đọc kịch bản");
        loadItem.setOnAction(e -> loadScript());

        fileMenu.getItems().addAll(saveItem, loadItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private void saveScript() {
        try {
            String jsonData = gson.toJson(elements);
            Files.write(Paths.get("script.json"), jsonData.getBytes());
            System.out.println("Kịch bản đã được lưu!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadScript() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("script.json")));
            elements = gson.fromJson(jsonContent, ArrayList.class);
            System.out.println("Kịch bản đã được tải!");
            renderElements();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void renderElements() {
        canvasPane.getChildren().clear();
        for (ScriptElement element : elements) {
            if ("Button".equals(element.getType())) {
                Button button = createDraggableButton("Button", element.getX(), element.getY());
                canvasPane.getChildren().add(button);
            } else if ("Input".equals(element.getType())) {
                TextField input = createDraggableInput(element.getX(), element.getY());
                canvasPane.getChildren().add(input);
            }
        }
    }

    private Button createDraggableButton(String text, double x, double y) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);

        button.setOnMousePressed(e -> handleElementPressed(button));
        button.setOnMouseDragged(e -> handleElementDragged(button, e.getX(), e.getY()));
        button.setOnMouseReleased(e -> handleElementReleased(button));
        return button;
    }

    private TextField createDraggableInput(double x, double y) {
        TextField input = new TextField("Input");
        input.setLayoutX(x);
        input.setLayoutY(y);

        input.setOnMousePressed(e -> handleElementPressed(input));
        input.setOnMouseDragged(e -> handleElementDragged(input, e.getX(), e.getY()));
        input.setOnMouseReleased(e -> handleElementReleased(input));
        return input;
    }

    private void handleElementPressed(Control element) {
        selectedElement = findElementByPosition(element.getLayoutX(), element.getLayoutY());
        tempLine = new Line(element.getLayoutX() + element.getWidth() / 2,
                element.getLayoutY() + element.getHeight() / 2,
                element.getLayoutX() + element.getWidth() / 2,
                element.getLayoutY() + element.getHeight() / 2);
        tempLine.setStroke(Color.GRAY);
        canvasPane.getChildren().add(tempLine);
    }

    private void handleElementDragged(Control element, double x, double y) {
        element.setLayoutX(x);
        element.setLayoutY(y);

        if (tempLine != null) {
            tempLine.setEndX(x + element.getWidth() / 2);
            tempLine.setEndY(y + element.getHeight() / 2);
        }
    }

    private void handleElementReleased(Control element) {
        if (tempLine != null) {
            ScriptElement target = findElementByPosition(element.getLayoutX(), element.getLayoutY());
            if (target != null && selectedElement != null && target != selectedElement) {
                Line line = new Line(selectedElement.getX(), selectedElement.getY(), target.getX(), target.getY());
                line.setStroke(Color.BLACK);
                canvasPane.getChildren().add(line);
            }
            canvasPane.getChildren().remove(tempLine);
            tempLine = null;
            selectedElement = null;
        }
    }

    private ScriptElement findElementByPosition(double x, double y) {
        for (ScriptElement element : elements) {
            if (Math.abs(element.getX() - x) < 10 && Math.abs(element.getY() - y) < 10) {
                return element;
            }
        }
        return null;
    }

    static class ScriptElement {
        private String type;
        private double x;
        private double y;
        private Map<String, String> properties;

        public ScriptElement(String type, double x, double y, Map<String, String> properties) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.properties = properties;
        }

        public String getType() {
            return type;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public Map<String, String> getProperties() {
            return properties;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
