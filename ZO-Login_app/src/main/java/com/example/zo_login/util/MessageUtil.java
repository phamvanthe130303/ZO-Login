package com.example.zo_login.util;

import com.example.zo_login.config.UTF8ResourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {
    private static Locale currentLocale = new Locale("vi_VN");
    private static ResourceBundle bundle = initResourceBundle(currentLocale);

    // Phương thức khởi tạo ResourceBundle
    public static ResourceBundle initResourceBundle(Locale locale) {
        if (bundle == null || !bundle.getLocale().equals(locale)) {
            // bundle không bị khởi tạo lại hai lần trong trường hợp nhiều luồng gọi cùng lúc
            synchronized (MessageUtil.class) {
                if (bundle == null || !bundle.getLocale().equals(locale)) {
                    bundle = UTF8ResourceBundle.getBundle("messages", locale.toString());
                }
            }
        }
        return bundle;
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    // Phương thức lấy message theo key
    public static String getMessage(String key) {
        // Mặc định là ngôn ngữ Tiếng Anh nếu locale chưa được khởi tạo
        if (bundle == null) {
            initResourceBundle(currentLocale);
        }
        return bundle.getString(key);
    }

    // Phương thức thay đổi locale (nếu cần)
//    public static void setLocale(String en_contry) {
//        if(en_contry.isEmpty()){
//            en_contry ="vi_VN";
//        }
//        Locale locale = new Locale(en_contry);
//        initResourceBundle(locale);
//    }
}
