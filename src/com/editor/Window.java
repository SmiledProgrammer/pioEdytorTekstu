package com.editor;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Window extends JFrame {

    JTextArea textArea;
    JScrollPane scrollPane;
    MenuBar menuBar;

    public Window() {
        setupWindow();
        setupDefaultComponentOptions();
        setupTextArea();
        setupMenuBar();
        setVisible(true);
    }

    public void setupWindow() {
        setTitle("Notepad+++");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setupDefaultComponentOptions() {
        UIManager.put("Menu.selectionBackground", new ColorUIResource(MenuOptionTree.selectionColor));
        UIManager.put("Menu.opaque", true);
        UIManager.put("MenuItem.selectionBackground", new ColorUIResource(MenuOption.selectionColor));
        UIManager.put("MenuItem.opaque", true);
    }

    public void setupTextArea() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }

    public void setupMenuBar() {
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
    }
}