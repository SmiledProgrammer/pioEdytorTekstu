package com.editor;

import javax.swing.*;

public class FormatFunctions {

    FontWindow fontWindow;

    public FormatFunctions(FontWindow fontWindow) {
        this.fontWindow = fontWindow;
    }

    public void font() {
        fontWindow = new FontWindow();
        fontWindow.CreateFont();
    }

    public void wrap(boolean isOn)
    {
        NotepadWindow.textArea.setLineWrap(isOn);
        NotepadWindow.textArea.setWrapStyleWord(isOn);
    }
}
