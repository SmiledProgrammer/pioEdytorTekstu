package com.editor;

public class FormatFunctions {

    FontWindow fontWindow;

    public FormatFunctions(FontWindow fontWindow) {
        this.fontWindow = fontWindow;
    }

    public void font() {
        fontWindow = new FontWindow();
        fontWindow.CreateFont();
    }
}
