package com.example.zologin.utill;

public class UserConstant {

    public static final String SCRIP_LISTENING_ACCTION = (
            "// Lắng nghe sự kiện Click\n" +
                    "window.clickHandler = function(event) {\n" +
                    "    console.log('Click event at: (' + event.clientX + ', ' + event.clientY + ')');\n" +
                    "};\n" +
                    "document.addEventListener('click', window.clickHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Scroll\n" +
                    "window.scrollHandler = function() {\n" +
                    "    console.log('Scrolled to position: ' + window.scrollY);\n" +
                    "};\n" +
                    "window.addEventListener('scroll', window.scrollHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Input\n" +
                    "window.inputHandler = function(event) {\n" +
                    "    console.log('Input event on element: ' + event.target.tagName + ' with value: ' + event.target.value);\n" +
                    "};\n" +
                    "document.addEventListener('input', window.inputHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Focus\n" +
                    "window.focusHandler = function(event) {\n" +
                    "    console.log('Focus event on element: ' + event.target.tagName);\n" +
                    "};\n" +
                    "document.addEventListener('focus', window.focusHandler, true);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Blur\n" +
                    "window.blurHandler = function(event) {\n" +
                    "    console.log('Blur event on element: ' + event.target.tagName);\n" +
                    "};\n" +
                    "document.addEventListener('blur', window.blurHandler, true);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Keydown\n" +
                    "window.keydownHandler = function(event) {\n" +
                    "    console.log('Keydown event: ' + event.key);\n" +
                    "};\n" +
                    "document.addEventListener('keydown', window.keydownHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Keyup\n" +
                    "window.keyupHandler = function(event) {\n" +
                    "    console.log('Keyup event: ' + event.key);\n" +
                    "};\n" +
                    "document.addEventListener('keyup', window.keyupHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Mouseover\n" +
                    "window.mouseoverHandler = function(event) {\n" +
                    "    console.log('Mouseover event on element: ' + event.target.tagName);\n" +
                    "};\n" +
                    "document.addEventListener('mouseover', window.mouseoverHandler);\n" +
//                    "// Lắng nghe sự kiện Mouseout\n" +
//                    "window.mouseoutHandler = function(event) {\n" +
//                    "    console.log('Mouseout event on element: ' + event.target.tagName);\n" +
//                    "};\n" +
//                    "document.addEventListener('mouseout', window.mouseoutHandler);\n" +
//                    "\n" +
//                    "// Lắng nghe sự kiện Mousemove\n" +
//                    "window.mousemoveHandler = function(event) {\n" +
//                    "    console.log('Mousemove event at: (' + event.clientX + ', ' + event.clientY + ')');\n" +
//                    "};\n" +
                    "document.addEventListener('mousemove', window.mousemoveHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Resize\n" +
                    "window.resizeHandler = function() {\n" +
                    "    console.log('Window resized to: ' + window.innerWidth + 'x' + window.innerHeight);\n" +
                    "};\n" +
                    "window.addEventListener('resize', window.resizeHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Contextmenu (Chuột phải)\n" +
                    "window.contextmenuHandler = function(event) {\n" +
                    "    console.log('Context menu opened at: (' + event.clientX + ', ' + event.clientY + ')');\n" +
                    "};\n" +
                    "document.addEventListener('contextmenu', window.contextmenuHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Dragstart\n" +
                    "window.dragstartHandler = function(event) {\n" +
                    "    console.log('Drag started on element: ' + event.target.tagName);\n" +
                    "};\n" +
                    "document.addEventListener('dragstart', window.dragstartHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Dragend\n" +
                    "window.dragendHandler = function(event) {\n" +
                    "    console.log('Drag ended on element: ' + event.target.tagName);\n" +
                    "};\n" +
                    "document.addEventListener('dragend', window.dragendHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Drop\n" +
                    "window.dropHandler = function(event) {\n" +
                    "    console.log('Drop event on element: ' + event.target.tagName);\n" +
                    "};\n" +
                    "document.addEventListener('drop', window.dropHandler);\n" +
                    "\n" +
                    "// Lắng nghe sự kiện Submit" +
                    "window.submitHandler = function(event) {" +
                    "    console.log('Form submitted: ' + event.target.tagName);" +
                    "};" +
                    "document.addEventListener('submit', window.submitHandler);" +
                    "// Lắng nghe sự kiện Change" +
                    "window.changeHandler = function(event) {" +
                    "    console.log('Change event on element: ' + event.target.tagName + ' with value: ' + event.target.value);" +
                    "};" +
                    "document.addEventListener('change', window.changeHandler);" +
                    "// Lắng nghe sự kiện Load" +
                    "window.loadHandler = function() {" +
                    "    console.log('Page fully loaded');};" +
                    "window.addEventListener('load', window.loadHandler);" +
                    "// Lắng nghe sự kiện Beforeunload" +
                    "window.beforeUnloadHandler = function(event) {" +
                    "    console.log('Beforeunload event triggered');};" +
                    "window.addEventListener('beforeunload', window.beforeUnloadHandler);" +
                    "// Lắng nghe sự kiện Error" +
                    "window.errorHandler = function(event) {" +
                    "    console.log('Error event: ' + event.message);};" +
                    "window.addEventListener('error', window.errorHandler);");
    public  static  final String SCRIPT_REMOVE_LISTENING = (
            "// Xóa sự kiện Click\n" +
                    "document.removeEventListener('click', window.clickHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Scroll\n" +
                    "window.removeEventListener('scroll', window.scrollHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Input\n" +
                    "document.removeEventListener('input', window.inputHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Focus\n" +
                    "document.removeEventListener('focus', window.focusHandler, true);\n" +
                    "\n" +
                    "// Xóa sự kiện Blur\n" +
                    "document.removeEventListener('blur', window.blurHandler, true);\n" +
                    "\n" +
                    "// Xóa sự kiện Keydown\n" +
                    "document.removeEventListener('keydown', window.keydownHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Keyup\n" +
                    "document.removeEventListener('keyup', window.keyupHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Mouseover\n" +
                    "document.removeEventListener('mouseover', window.mouseoverHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Mouseout\n" +
                    "document.removeEventListener('mouseout', window.mouseoutHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Mousemove\n" +
                    "document.removeEventListener('mousemove', window.mousemoveHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Resize\n" +
                    "window.removeEventListener('resize', window.resizeHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Contextmenu\n" +
                    "document.removeEventListener('contextmenu', window.contextmenuHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Dragstart\n" +
                    "document.removeEventListener('dragstart', window.dragstartHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Dragend\n" +
                    "document.removeEventListener('dragend', window.dragendHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Drop\n" +
                    "document.removeEventListener('drop', window.dropHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Submit\n" +
                    "document.removeEventListener('submit', window.submitHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Change\n" +
                    "document.removeEventListener('change', window.changeHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Load\n" +
                    "window.removeEventListener('load', window.loadHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Beforeunload\n" +
                    "window.removeEventListener('beforeunload', window.beforeUnloadHandler);\n" +
                    "\n" +
                    "// Xóa sự kiện Error\n" +
                    "window.removeEventListener('error', window.errorHandler);\n" +
                    "\n" +
                    "// Xóa tất cả các listener đã được gán\n" +
                    "console.log('All event listeners removed');\n");

    public static final Integer TIME_OUT_SESSION = 60;
}
