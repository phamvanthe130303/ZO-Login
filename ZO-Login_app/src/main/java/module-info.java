module com.example.zo_login {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires static lombok;
    requires com.google.gson;

    exports com.example.zo_login.controller;  // Xuất package controller để có thể sử dụng trong FXML
    opens com.example.zo_login.controller to javafx.fxml;  // Mở quyền cho javafx.fxml để truy cập các lớp trong controller

    opens com.example.zo_login to javafx.fxml;
    exports com.example.zo_login;

}