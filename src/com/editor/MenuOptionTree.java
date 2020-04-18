package com.editor;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuOptionTree extends JMenu {

    static Color defaultColor = new Color(153, 204, 255);
    static Color hoverColor = new Color(130, 190, 255);
    static Color selectionColor = new Color(70, 160, 255);

    public MenuOptionTree(String name) {
        setBorder(BorderFactory.createLineBorder(defaultColor, 1));
        setText(name);
        setupHoverEvent();
        setBackground(defaultColor);
    }

    public void setupHoverEvent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor);
            }
        });
    }
}