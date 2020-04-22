package com.editor;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EditFunctions {
    static Window window;

    public EditFunctions(Window window) {
        EditFunctions.window = window;
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
}
