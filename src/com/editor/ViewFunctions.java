package com.editor;

public class ViewFunctions {

    ColorWindow viewWindow;

    public ViewFunctions(ColorWindow viewWindow) { this.viewWindow = viewWindow; }

    public void color() {
        viewWindow = new ColorWindow();
        viewWindow.CreateColor();
    }
}
