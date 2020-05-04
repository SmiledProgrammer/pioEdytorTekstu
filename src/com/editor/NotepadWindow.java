package com.editor;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.*;

public class NotepadWindow extends Window implements Runnable {

    static JTextArea textArea;
    MenuBar menuBar;
    JScrollPane scrollPane;
    static Font currentFont;

    @Override
    public void run() {
        MakeNotepadWindow();
        setupDefaultComponentOptions();
        setupTextArea();
        setupMenuBar();
        setVisible(true);
        System.out.println(SwingUtilities.isEventDispatchThread());
    }

    public void MakeNotepadWindow() {
        setTitle("Notepad+++");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int ifSave = FileFunctions.ifSave();
                if(ifSave == YES_OPTION) {
                    FileFunctions.saveFile();
                } else if(ifSave == CANCEL_OPTION || ifSave == CLOSED_OPTION) {
                    return;
                }
                System.exit(0);
            }
        });
    }

    public void setupTextArea() {
        textArea = new JTextArea();
        textArea.addKeyListener(new InputHandler());
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
                FileFunctions.ifEdited = true;
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
