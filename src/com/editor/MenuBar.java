package com.editor;

import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {

    NotepadWindow notepadWindow;
    FontWindow fontWindow;
    static Color color = new Color(153, 204, 255);
    MenuOptionTree fileTree; //new, open, save, save as, exit
    MenuOptionTree editTree; //undo, redo
    MenuOptionTree formatTree; //font (window pops out)
    MenuOption fileNew, fileOpen, fileSave, fileSaveAs, fileExit;
    MenuOption editUndo, editRedo;
    MenuOption formatFont;

    FileFunctions fileFunctions;
    EditFunctions editFunctions;
    FormatFunctions formatFunctions;

    public MenuBar(NotepadWindow notepadWindow) {
        this.notepadWindow = notepadWindow;
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

        formatFont = new MenuOption("Font");
        formatFont.addActionListener(this);
        formatFont.setActionCommand("Font");
        formatTree.add(formatFont);
    }

    public void setupFunctions() {
        fileFunctions = new FileFunctions(notepadWindow);
        editFunctions = new EditFunctions(notepadWindow);
        formatFunctions = new FormatFunctions(fontWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New": fileFunctions.newFile(); break;
            case "Open": fileFunctions.openFile(); break;
            case "Save": FileFunctions.saveFile(); break;
            case "SaveAs": FileFunctions.saveFileAs(); break;
            case "Exit": fileFunctions.exitFile(); break;
            case "Undo": editFunctions.undo(); break;
            case "Redo": editFunctions.redo(); break;
            case "Font": formatFunctions.font(); break;
        }
    }
}