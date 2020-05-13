package com.editor;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;

public class EditFunctions {
    static Window window;
    static FindWindow findWindow;
    static ReplaceWindow replaceWindow;

    static Language languageC;
    static boolean ifCsetUp;

    public EditFunctions(Window window) {
        this.window = window;
        findWindow = new FindWindow();
        replaceWindow = new ReplaceWindow();
        ifCsetUp = false;
    }

    public static void undo() {
        try {
            window.undoManager.undo();
        }catch (CannotUndoException e) {
            System.err.println("No text to undo");
        }
        FileFunctions.ifEdited = true;
    }

    public static void redo() {
        try {
            window.undoManager.redo();
        }catch (CannotRedoException e) {
            System.err.println("No text to redo");
        }
        FileFunctions.ifEdited = true;
    }

    public static void find() {
        findWindow.setVisible(true);
    }

    public void replace() {
        replaceWindow.setVisible(true);
    }

    public static void setUpC() {   // należy dokończyć
        if(ifCsetUp)
            return;
        languageC = new Language();
        languageC.addKeyword("void", Color.RED);
        languageC.addKeyword("int", Color.RED);
        languageC.addKeyword("char", Color.RED);
        languageC.addKeyword("double", Color.RED);
        languageC.addKeyword("typedef", Color.blue);
        languageC.addKeyword("struct", Color.blue); // resztę trzeba dopisać, to tylko prototyp

        languageC.addSectionMarker('{', '}');
        languageC.setHighlightNumbers(true);
        ifCsetUp = true;

    }
}
