package com.editor;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EditFunctions {
    Window window;

    public EditFunctions(Window window) {
        this.window = window;
    }

    public void undo() {
        try {
            window.undoManager.undo();
        }catch (CannotUndoException e) {
            System.err.println("No text to undo");
        }
    }

    public void redo() {
        try {
            window.undoManager.redo();
        }catch (CannotRedoException e) {
            System.err.println("No text to redo");
        }
    }
}
