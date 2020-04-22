package com.editor;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileFunctions {

    static Window window;
    static String fName;
    static String fAddress;

    public FileFunctions(Window window) {
        FileFunctions.window = window;
    }

    public static void newFile() {
        window.textArea.setText("");
        window.setTitle("Notepad+++ - New");
        fName = null;
        fAddress = null;
        System.out.println("New File");
    }

    public static void openFile() {
        FileDialog fd = new FileDialog(window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fName = fd.getFile();
            fAddress = fd.getDirectory();
            window.setTitle(fName);
        }

        try {
            BufferedReader buff = new BufferedReader(new FileReader(fAddress + fName));
            window.textArea.setText("");
            String line = null;
            while((line = buff.readLine()) != null) {
                window.textArea.append(line + "\n");
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
                fw.write(window.textArea.getText());
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
            fw.write(window.textArea.getText());
            fw.close();

            System.out.println("Saved: "+ fAddress + fName);
        } catch (Exception e) {
            System.out.println("Can't Save !!!");
        }
    }

    public static void exitFile() {
        System.exit(0);
    }
}