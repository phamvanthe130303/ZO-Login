package com.example.zologin.module.user.service;

import com.example.zologin.utill.UserConstant;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ChromeService {

    public void getAcctionUser(WebDriver webDriver){

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        boolean isPageLoaded = false;
        while (!isPageLoaded) {
            isPageLoaded = js.executeScript("return document.readyState").equals("complete");
        }
        js.executeScript(UserConstant.SCRIP_LISTENING_ACCTION);

    }
}
