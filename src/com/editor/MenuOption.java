package com.editor;

import javax.swing.*;
import java.awt.Color;

public class MenuOption extends JMenuItem {

    static Color defaultColor = new Color(120, 190, 255);
    static Color selectionColor = new Color(100, 180, 255);

    public MenuOption(String text) {
        setBorder(BorderFactory.createLineBorder(defaultColor, 2));
        setText(text);
        setBackground(defaultColor);
    }
}