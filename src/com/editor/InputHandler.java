package com.editor;


import java.util.Set;
import java.util.HashSet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    private static final Set<Integer> pressedKeys = new HashSet<>();
    private static Boolean saveAs;
    private static final Boolean[] save = new Boolean[2];
    private static final Boolean[] newF = new Boolean[2];
    private static final Boolean[] openF = new Boolean[2];
    private static final Boolean[] undo = new Boolean[2];
    private static Boolean redo;

    public InputHandler() {
        InputHandler.save[0] = false;
        InputHandler.save[1] = false;
        InputHandler.saveAs = false;

        InputHandler.newF[0] = false;
        InputHandler.newF[1] = false;

        InputHandler.openF[0] = false;
        InputHandler.openF[1] = false;

        InputHandler.undo[0] = false;
        InputHandler.undo[1] = false;
        InputHandler.redo = false;
    }
    static void cleanSet() {
        pressedKeys.clear();
    }
    static void setAllFalse() {
        save[0] = false;
        save[1] = false;
        saveAs = false;

        undo[0] = false;
        undo[1] = false;
        redo = false;

        newF[0] = false;
        newF[1] = false;

        openF[0] = false;
        openF[1] = false;
    }
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());

        if (!pressedKeys.isEmpty()) {
            for (Integer pressedKey : pressedKeys) {
                switch (pressedKey) {
                    case KeyEvent.VK_CONTROL:
                        save[0] = true;
                        newF[0] = true;
                        openF[0] = true;
                        undo[0] = true;
                        break;
                    case KeyEvent.VK_S:
                        save[1] = true;
                        break;
                    case KeyEvent.VK_N:
                        newF[1] = true;
                        break;
                    case KeyEvent.VK_O:
                        openF[1] = true;
                        break;
                    case KeyEvent.VK_SHIFT:
                        saveAs = true;
                        redo = true;
                        break;
                    case KeyEvent.VK_Z:
                        undo[1] = true;
                        break;
                }

            }
            if (save[0] && save[1]) {
                // do write file
                if (!saveAs) {
                    FileFunctions.saveFile();
                } else {
                    FileFunctions.saveFileAs();
                }
                System.out.println("Zapis przez skrot");
                save[0] = false;
                save[1] = false;
                saveAs = false;
            }
            if (undo[0] && undo[1]) {
                if (!redo) {
                    EditFunctions.undo();
                } else {
                    EditFunctions.redo();
                }
                undo[0] = false;
                undo[1] = false;
                redo = false;
            }
            if (newF[0] && newF[1]) {
                FileFunctions.newFile();
                newF[0] = false;
                newF[1] = false;
            }
            if (openF[0] && openF[1]) {
                FileFunctions.openFile();
                openF[0] = false;
                openF[1] = false;
            }

            if (pressedKeys.contains(KeyEvent.VK_CONTROL) && pressedKeys.contains(KeyEvent.VK_S)) {
                pressedKeys.remove(KeyEvent.VK_CONTROL);
                pressedKeys.remove(KeyEvent.VK_S);
                if(pressedKeys.contains(KeyEvent.VK_SHIFT)) {
                    pressedKeys.remove(KeyEvent.VK_SHIFT);
                }
            }
            if (pressedKeys.contains(KeyEvent.VK_CONTROL) && pressedKeys.contains(KeyEvent.VK_O)) {
                pressedKeys.remove(KeyEvent.VK_CONTROL);
                pressedKeys.remove(KeyEvent.VK_O);
            }

        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        //System.out.println(pressedKeys);
        setAllFalse();
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        /* not used */
    }
}
