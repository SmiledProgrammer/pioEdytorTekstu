package com.editor;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import java.awt.*;

public class NotepadWindow extends Window {

    static JTextArea textArea;
    MenuBar menuBar;
    JScrollPane scrollPane;
    static Font currentFont;

    public void CreateNotepad(){
        MakeNotepadWindow();
        setupDefaultComponentOptions();
        setupTextArea();
        setupMenuBar();
        setVisible(true);
    }

    public void MakeNotepadWindow() {
        setTitle("Notepad+++");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setupTextArea() {
        textArea = new JTextArea();
        textArea.addKeyListener(new InputHandler());
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        textArea.setFont(currentFont);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }

    public void setupMenuBar() {
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
    }

    static public void setFont() {
        textArea.setFont(currentFont);
    }
}
