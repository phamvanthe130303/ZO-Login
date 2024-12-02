package com.example.zologin.module.user.controller;

import com.example.zologin.module.user.service.ChromeService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.page.Page;
import org.openqa.selenium.devtools.v130.target.Target;
import org.openqa.selenium.Point;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class CreateWebDriverController {

    public static void main(String[] args) {

        CreateWebDriverController controller = new CreateWebDriverController();
        controller.CreateWebDriverDemo();
    }

    private Map<String,String> tabMap = new HashMap<>();

    public  void CreateWebDriverDemo(){
        ChromeService chromeService = new ChromeService();

        ChromeOptions options = new ChromeOptions();

        // Loại bỏ thông báo "Chrome is being controlled by automated test software"
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("window-size=200,300");

        // clearResolutionCache xóa thông tin cache cấu hình WebDriverManager
        WebDriverManager.chromedriver().clearResolutionCache();


        WebDriverManager.chromedriver().setup();


        // Lấy danh sách màn hình
        GraphicsDevice[] screens = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getScreenDevices();
        ChromeDriver driver = new ChromeDriver(options);
        if (screens.length < 2) {
            System.out.println("Chỉ có 1 màn hình. Không thể di chuyển sang màn hình thứ hai.");
        } else {
            Rectangle screenBounds = screens[1].getDefaultConfiguration().getBounds();

            // Di chuyển trình duyệt sang màn hình thứ hai
            driver.manage().window().setPosition(new Point(screenBounds.x,screenBounds.y));

        }


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
        Set<String> tablist  = driver.getWindowHandles();

        // Lắng nghe sự kiện tab mới được tạo
        devTools.addListener(Target.targetCreated(), target -> {
            Set<String> newHandles = driver.getWindowHandles();
            tablist.clear();
            tablist.addAll(newHandles);

            //kiem tra nếu target.getTargetId() có trong tablist là tab mới được tạo
            if (tablist.contains(target.getTargetId().toString().trim())) {
                System.out.println("Tab mới được mở: " + target.getTargetId());
            }
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
            if (frame.getFrame().getParentId().isEmpty()) {
                chromeService.getAcctionUser(driver);
                System.out.println("Chuyển hướng hoặc tải lại trang: " + frame.getFrame().getUrl());
            }

        });

        driver.get("https://www.google.com/?hl=vi");


    }


}
