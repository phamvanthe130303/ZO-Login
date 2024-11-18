package com.example.zologin.utill;

public class UserConstant {

    public static final String SCRIP_LISTENING_ACCTION = (
            "document.addEventListener('click', function(event) {" +
            "   console.log('Click event at: (' + event.clientX + ', ' + event.clientY + ')');" +
            "});" +
            "window.addEventListener('scroll', function() {" +
            "   console.log('Scrolled to position: ' + window.scrollY);" +
            "});" +
            "document.addEventListener('input', function(event) {" +
            "   console.log('Input event on element: ' + event.target.tagName + ' with value: ' + event.target.value);" +
            "});");
}
