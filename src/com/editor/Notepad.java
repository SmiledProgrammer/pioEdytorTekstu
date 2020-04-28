package com.editor;

public class Notepad {

    static NotepadWindow notepadWindow;

    public static void main(String [] args) {
        notepadWindow = new NotepadWindow();
        notepadWindow.CreateNotepad();
    }
}