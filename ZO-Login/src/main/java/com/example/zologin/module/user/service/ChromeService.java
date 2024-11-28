package com.example.zologin.module.user.service;

import com.example.zologin.utill.UserConstant;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ChromeService {

    public void getAcctionUser(WebDriver webDriver){

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //chờ load xong moi them javascript
        boolean isPageLoaded = false;
        int elapsedSeconds = 0;

        while (!isPageLoaded && elapsedSeconds < UserConstant.TIME_OUT_SESSION) {
            try {
                // Kiểm tra trạng thái tài liệu
                String readyState = (String) js.executeScript("return document.readyState");
                if (readyState.equals("complete")) {
                    isPageLoaded = true;
                } else {
                    // Chờ 500ms trước khi kiểm tra lại
                    Thread.sleep(500);
                    elapsedSeconds += 0.5;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while waiting for page load", e);
            }
        }

        if (!isPageLoaded) {
            throw new RuntimeException("Page did not load completely within " + UserConstant.TIME_OUT_SESSION + " seconds");
        }

        boolean isVariableExists = (Boolean) js.executeScript(
                "return typeof exitJavaScriptSelenium !== 'undefined';"
        );

        if (!isVariableExists) {
            System.out.println("đã thêm js vào trang");
            js.executeScript(UserConstant.SCRIP_LISTENING_ACCTION);
        }
    }
}
