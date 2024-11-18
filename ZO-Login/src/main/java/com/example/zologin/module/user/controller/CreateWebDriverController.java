package com.example.zologin.module.user.controller;

import com.example.zologin.module.user.service.ChromeService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.page.Page;
import org.openqa.selenium.devtools.v130.target.Target;


import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CreateWebDriverController {

    public static void main(String[] args) {

        CreateWebDriverController controller = new CreateWebDriverController();
        controller.CreateWebDriverDemo();
    }

    public  void CreateWebDriverDemo(){
        ChromeService chromeService = new ChromeService();

        ChromeOptions options = new ChromeOptions();

        // Loại bỏ thông báo "Chrome is being controlled by automated test software"
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("window-size=200,300");

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver(options);
        // Xóa thuộc tính `navigator.webdriver` để che dấu là webdriver
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        DevTools devTools =  driver.getDevTools();
        try {
            devTools.createSession();
        }catch (Exception e){
            e.printStackTrace();
        }

        // Bật lắng nghe Target events
        devTools.send(Target.setDiscoverTargets(true, Optional.empty()));

        // Lắng nghe sự kiện tab mới được tạo
        devTools.addListener(Target.targetCreated(), target -> {
            System.out.println("Tab mới được mở: " + target.getTargetId());
        });

        // Lắng nghe sự kiện tab bị đóng
        devTools.addListener(Target.targetDestroyed(), targetId -> {
            System.out.println("Tab bị đóng: " + targetId);
        });

        AtomicReference<String> activeTabId = new AtomicReference<>("");

        // Lắng nghe thay đổi thông tin của các tab
        devTools.addListener(Target.targetInfoChanged(), targetInfo -> {
            if (targetInfo.getAttached()) {
                String tabId = String.valueOf(targetInfo.getTargetId());
                System.out.println("Tab đang được focus: " + tabId);
                activeTabId.set(tabId);
            }
        });

        //Chuyển hướng hoặc tải lại trang
        devTools.send(Page.enable());
        devTools.addListener(Page.frameNavigated(), frame -> {
            System.out.println("Chuyển hướng hoặc tải lại trang: " + frame.getFrame().getUrl());
        });

        driver.get("https://www.google.com/?hl=vi");

        chromeService.getAcctionUser(driver);
    }
}
