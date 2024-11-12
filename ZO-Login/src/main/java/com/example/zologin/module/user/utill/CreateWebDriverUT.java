package com.example.zologin.module.user.utill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class CreateWebDriverUT {

    public static WebDriver oneWebDriver(ChromeOptions options){
        return new ChromeDriver(options);
    }

    /*public static List<WebDriver> listWebDriver(){
        return new ChromeDriver(options);
    }*/
}
