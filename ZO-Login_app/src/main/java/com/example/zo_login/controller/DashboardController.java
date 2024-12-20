package com.example.zo_login.controller;

import com.example.zo_login.model.MenuObject;
import com.example.zo_login.util.CreateToolTip;
import com.example.zo_login.util.CustomAlert;
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

public class DashboardController {

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
    private Button btnMenuSchedule;
    @FXML
    private Button btnMenuCoder;
    @FXML
    private Button btnMenuBlog;
    @FXML
    private Button btnMenuContact;

    @FXML
    private Button btnNoti;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAccount;

    @FXML
    private StackPane idBodyCenter;

    @FXML
    public void initialize() {
        // Tạo Tooltip cho từng nút menu
        CreateToolTip.createTooltip(btnMenuProfile, "Profile", "right");
        CreateToolTip.createTooltip(btnMenuProxy, "Proxy", "right");
        CreateToolTip.createTooltip(btnMenuRec, "Script", "right");
        CreateToolTip.createTooltip(btnMenuSchedule, "Schedule", "right");
        CreateToolTip.createTooltip(btnMenuCoder, "Coder", "right");
        CreateToolTip.createTooltip(btnMenuBlog, "Blog", "right");
        CreateToolTip.createTooltip(btnMenuContact, "Contact", "right");

        // Tooltip cho các nút khác nếu cần
        CreateToolTip.createTooltip(btnNoti, "Notification", "bottom");
        CreateToolTip.createTooltip(btnLogout, "Sign out", "bottom");
        CreateToolTip.createTooltip(btnAccount, "Account", "bottom");
    }


    @FXML
    private void handleMenuClick(ActionEvent event) {

        List<MenuObject> menuList = new ArrayList<>();
        menuList.add(
                new MenuObject("1","Profile","Mô tả: Chức năng quản lý thông tin trình duyệt","profile_view.fxml","btnMenuProfile"));
        menuList.add(
                new MenuObject("2","Proxy","Mô tả: Chức năng quản lý proxy","proxy_view.fxml","btnMenuProxy"));
        menuList.add(
                new MenuObject("3","Script","Mô tả: Chức năng quản lý thông tin trình duyệt","rec_view.fxml","btnMenuRec"));
        menuList.add(
                new MenuObject("4","Schedule","Mô tả: Chức năng quản lý lịch trình","schedule_view.fxml","btnMenuSchedule"));
        menuList.add(
                new MenuObject("5","Coder","Mô tả: Chức năng viết code","coder_view.fxml","btnMenuCoder"));
        menuList.add(
                new MenuObject("6","Blog","Mô tả: Chức năng bài viết","blog_view.fxml","btnMenuBlog"));

        menuList.add(
                new MenuObject("7","Contact","Mô tả: Chức năng liên hệ","contact_view.fxml","btnMenuContact"));

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
            selectedButton.getStyleClass().remove("button_tooltip");;
            selectedButton.getStyleClass().add("button_selected");
        }
        // Load nội dung FXML
        setCenterContent(menuObject.getFileMenu());
    }

    private void resetMenuColors() {
        // Xóa class cũ và thêm class "button_tooltip" cho các nút
        btnMenuProfile.getStyleClass().remove("button_selected");
        btnMenuProfile.getStyleClass().add("button_tooltip");

        btnMenuProxy.getStyleClass().remove("button_selected");
        btnMenuProxy.getStyleClass().add("button_tooltip");

        btnMenuRec.getStyleClass().remove("button_selected");
        btnMenuRec.getStyleClass().add("button_tooltip");

        btnMenuSchedule.getStyleClass().remove("button_selected");
        btnMenuSchedule.getStyleClass().add("button_tooltip");

        btnMenuCoder.getStyleClass().remove("button_selected");
        btnMenuCoder.getStyleClass().add("button_tooltip");

        btnMenuBlog.getStyleClass().remove("button_selected");
        btnMenuBlog.getStyleClass().add("button_tooltip");

        btnMenuContact.getStyleClass().remove("button_selected");
        btnMenuContact.getStyleClass().add("button_tooltip");
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
    @FXML
    private void showAlert(){
        CustomAlert.showAlert("Đăng ký thành công");
    }
}
