package com.example.zo_login.controller;

import com.example.zo_login.model.MenuObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboadController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Text nameMenu;

    @FXML
    private Text descMenu;

    // Các nút menu
    @FXML
    private Button btnMenuProfile;
    @FXML
    private Button btnMenuProxy;
    @FXML
    private Button btnMenuRec;
    @FXML
    private Button btnMenuShedule;
    @FXML
    private Button btnMenuCoder;
    @FXML
    private Button btnMenuBlog;
    @FXML
    private Button btnMenuConnect;

    @FXML
    private Button btnNoti;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAccount;

    @FXML
    private StackPane idBodyCenter;

    @FXML
    private void handleMenuClick(ActionEvent event) {

        List<MenuObject> menuList = new ArrayList<>();
        menuList.add(
                new MenuObject("1","Profile","Mô tả: Chức năng quản lý thông tin trình duyệt","profile_view.fxml","btnMenuProfile"));
        menuList.add(
                new MenuObject("2","Proxy","Mô tả: Chức năng quản lý proxy","proxy_view.fxml","btnMenuProxy"));
        menuList.add(
                new MenuObject("3","Rec","Mô tả: Chức năng quản lý thông tin trình duyệt","rec_view.fxml","btnMenuRec"));
        menuList.add(
                new MenuObject("4","Shedule","Mô tả: Chức năng quản lý lịch trình","shedule_view.fxml","btnMenuShedule"));
        menuList.add(
                new MenuObject("5","Coder","Mô tả: Chức năng viết code","coder_view.fxml","btnMenuCoder"));

        // Tìm Button được nhấn
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        // Tìm MenuObject tương ứng
        MenuObject selectedMenu = menuList.stream()
                .filter(menu -> menu.getIdButton().equals(buttonId))
                .findFirst()
                .orElse(null);

        if (selectedMenu != null) {
            updateMenu(selectedMenu);
        }
    }

    private void updateMenu(MenuObject menuObject) {
        // Cập nhật tên chức năng và mô tả
        nameMenu.setText(menuObject.getNameMenu());
        descMenu.setText(menuObject.getDescMenu());

        // Đổi màu nút menu
        resetMenuColors(); // Reset màu tất cả các nút

        Button selectedButton = (Button) rootPane.lookup("#" + menuObject.getIdButton());
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: #242424; -fx-text-fill: white; -fx-border-color: #000000; -fx-border-width: 0 0 0 3;");
        }
        // Load nội dung FXML
        setCenterContent(menuObject.getFileMenu());
    }

    private void resetMenuColors() {
        // Reset màu tất cả các nút về mặc định
        String defaultStyle = "-fx-background-color: #3A3A3A; -fx-text-fill: gray; -fx-border-width: 0;";
        btnMenuProfile.setStyle(defaultStyle);
        btnMenuProxy.setStyle(defaultStyle);
        btnMenuRec.setStyle(defaultStyle);
        btnMenuShedule.setStyle(defaultStyle);
        btnMenuCoder.setStyle(defaultStyle);
        btnMenuBlog.setStyle(defaultStyle);
        btnMenuConnect.setStyle(defaultStyle);
    }

    private void setCenterContent(String fxmlFile) {
        try {
            // Load FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/zo_login/view/menu_center/"+fxmlFile));
            Node newContent = loader.load();

            // Set vào center của BorderPane
            idBodyCenter.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
