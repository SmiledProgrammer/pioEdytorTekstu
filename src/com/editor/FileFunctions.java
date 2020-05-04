package com.editor;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import static javax.swing.JOptionPane.*;

public class FileFunctions {

    static NotepadWindow window;
    static String fName;
    static String fAddress;

    public FileFunctions(NotepadWindow window) {
        FileFunctions.window = window;
    }
    private static int ifSave() {
        InputHandler.setAllFalse();
        InputHandler.cleanSet();
        Object[] options = {"Yes",
                "No",
                "Cancel"};
        return JOptionPane.showOptionDialog(window,
                "Do you want to save changes? "
                        + "All unsaved changes will be lost!",
                "Save changes",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
    }
    public static void newFile() {
        int ifLeaveSave = ifSave();
        if(ifLeaveSave == YES_OPTION) {
            saveFile();
        } else if(ifLeaveSave == CANCEL_OPTION || ifLeaveSave == CLOSED_OPTION) {
            return;
        }
        NotepadWindow.textArea.setText("");
        window.setTitle("Notepad+++ - New");
        fName = null;
        fAddress = null;
        System.out.println("New File");
    }

    public static void openFile() {
        int ifLeaveSave = ifSave();
        if(ifLeaveSave == YES_OPTION) {
            saveFile();
        } else if(ifLeaveSave == CANCEL_OPTION || ifLeaveSave == CLOSED_OPTION) {
            return;
        }
        FileDialog fd = new FileDialog(window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fName = fd.getFile();
            fAddress = fd.getDirectory();
            window.setTitle(fName);
        }

        try {
            BufferedReader buff = new BufferedReader(new FileReader(fAddress + fName));
            NotepadWindow.textArea.setText("");
            String line = null;
            while((line = buff.readLine()) != null) {
                //NotepadWindow.textArea.append(line + "\n");
                NotepadWindow.textArea.getStyledDocument().insertString(0,line, null);
            }
            buff.close();

            System.out.println("Opened: "+ fAddress + fName);

        }catch (Exception e) {
            System.out.println("Can't open a file!!");
        }
    }

    public static void saveFile() {
        if (fName == null)
            saveFileAs();

        else {
            try {
                FileWriter fw = new FileWriter(fAddress + fName);
                fw.write(NotepadWindow.textArea.getText());
                fw.close();

                System.out.println("Saved: "+ fAddress + fName);

            } catch (Exception e) {
                System.out.println("Can't Save !!!");
            }

        }
    }

    public static void saveFileAs() {
        FileDialog fd = new FileDialog(window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fName = fd.getFile();
            fAddress = fd.getDirectory();
            window.setTitle(fName);
        }

        try {
            FileWriter fw = new FileWriter(fAddress + fName);
            fw.write(NotepadWindow.textArea.getText());
            fw.close();

            System.out.println("Saved: "+ fAddress + fName);
        } catch (Exception e) {
            System.out.println("Can't Save !!!");
        }
    }

    public static void exitFile() {
        int ifLeaveSave = ifSave();
        if(ifLeaveSave == YES_OPTION) {
            saveFile();
        } else if(ifLeaveSave == CANCEL_OPTION || ifLeaveSave == CLOSED_OPTION) {
            return;
        }
        System.exit(0);
    }
}