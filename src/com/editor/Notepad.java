package com.editor;

public class Notepad {

    static NotepadWindow notepadWindow;
    //static InputHandler inputHandler; //do zrobienia

    public static void main(String [] args) {
        notepadWindow = new NotepadWindow();
        notepadWindow.CreateNotepad();
    }
}