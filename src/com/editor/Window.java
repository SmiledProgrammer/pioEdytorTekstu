package com.editor;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.undo.UndoManager;
import java.awt.event.KeyListener;

public class Window extends JFrame {

    JTextArea textArea;
    JScrollPane scrollPane;
    MenuBar menuBar;
    UndoManager undoManager = new UndoManager();

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
        textArea.addKeyListener(new InputHandler());
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }

    public void setupMenuBar() {
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
    }
    
}