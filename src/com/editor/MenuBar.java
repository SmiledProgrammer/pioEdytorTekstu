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
    MenuOption editUndo, editRedo;

    FileFunctions fileFunctions;
    EditFunctions editFunctions;

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
        fileOpen.addActionListener(this);
        fileOpen.setActionCommand("Open");
        fileTree.add(fileOpen);

        fileSave = new MenuOption("Save");
        fileSave.addActionListener(this);
        fileSave.setActionCommand("Save");
        fileTree.add(fileSave);

        fileSaveAs = new MenuOption("SaveAs");
        fileSaveAs.addActionListener(this);
        fileSaveAs.setActionCommand("SaveAs");
        fileTree.add(fileSaveAs);

        fileExit = new MenuOption("Exit");
        fileExit.addActionListener(this);
        fileExit.setActionCommand("Exit");
        fileTree.add(fileExit);
    }


    public void setupEditTree() {
        editTree = new MenuOptionTree("Edit");
        add(editTree);

        editUndo = new MenuOption("Undo");
        editUndo.addActionListener(this);
        editUndo.setActionCommand("Undo");
        editTree.add(editUndo);

        editRedo = new MenuOption("Redo");
        editRedo.addActionListener(this);
        editRedo.setActionCommand("Redo");
        editTree.add(editRedo);
    }

    public void setupFormatTree() {
        formatTree = new MenuOptionTree("Format");
        add(formatTree);
    }

    public void setupFunctions() {
        fileFunctions = new FileFunctions(window);
        editFunctions = new EditFunctions(window);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New": fileFunctions.newFile(); break;
            case "Open": fileFunctions.openFile(); break;
            case "Save": fileFunctions.saveFile(); break;
            case "SaveAs": fileFunctions.saveFileAs(); break;
            case "Exit": fileFunctions.exitFile(); break;
            case "Undo": editFunctions.undo(); break;
            case "Redo": editFunctions.redo(); break;
        }
    }
}