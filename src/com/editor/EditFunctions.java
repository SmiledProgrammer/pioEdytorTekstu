package com.editor;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EditFunctions {
    static Window window;
    static FindWindow findWindow;

    public EditFunctions(Window window) {
        this.window = window;
        findWindow = new FindWindow();
    }

    public static void undo() {
        try {
            window.undoManager.undo();
        }catch (CannotUndoException e) {
            System.err.println("No text to undo");
        }
    }

    public static void redo() {
        try {
            window.undoManager.redo();
        }catch (CannotRedoException e) {
            System.err.println("No text to redo");
        }
    }

    public static void find() {
        findWindow.setVisible(true);
    }
}
