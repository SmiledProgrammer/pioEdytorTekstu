package com.editor;

public class FileFunctions {

    Window window;

    public FileFunctions(Window window) {
        this.window = window;
    }

    public void newFile() {
        window.textArea.setText("");
        window.setTitle("Notepad+++ - New");
    }

    public void openFile() {

    }

    public void saveFile() {

    }

    public void saveFileAs() {

    }

    public void exitFile() {

    }
}