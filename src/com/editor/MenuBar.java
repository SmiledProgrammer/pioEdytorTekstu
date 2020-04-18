package com.editor;

import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {

    Window window;
    static Color color = new Color(153, 204, 255);
    MenuOptionTree fileTree; //new, open, save, save as, exit
    MenuOptionTree editTree; //undo, redo
    MenuOptionTree formatTree; //font (window pops out)
    MenuOption fileNew, fileOpen, fileSave, fileSaveAs, fileExit;

    FileFunctions fileFunctions;

    public MenuBar(Window window) {
        this.window = window;
        setBorder(BorderFactory.createLineBorder(color, 2));
        setBackground(color);
        setupFileTree();
        setupEditTree();
        setupFormatTree();
        setupFunctions();
    }

    public void setupFileTree() {
        fileTree = new MenuOptionTree("File");
        add(fileTree);

        fileNew = new MenuOption("New");
        fileNew.addActionListener(this);
        fileNew.setActionCommand("New");
        fileTree.add(fileNew);

        fileOpen = new MenuOption("Open");
        fileTree.add(fileOpen);

        fileSave = new MenuOption("Save");
        fileTree.add(fileSave);

        fileSaveAs = new MenuOption("Save as");
        fileTree.add(fileSaveAs);

        fileExit = new MenuOption("Exit");
        fileTree.add(fileExit);
    }

    public void setupEditTree() {
        editTree = new MenuOptionTree("Edit");
        add(editTree);
    }

    public void setupFormatTree() {
        formatTree = new MenuOptionTree("Format");
        add(formatTree);
    }

    public void setupFunctions() {
        fileFunctions = new FileFunctions(window);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New": System.out.println("test"); fileFunctions.newFile(); break;
            case "Open": fileFunctions.openFile(); break;
            case "Save": fileFunctions.saveFile(); break;
            case "SaveAs": fileFunctions.saveFileAs(); break;
            case "Exit": fileFunctions.exitFile(); break;
        }
    }
}