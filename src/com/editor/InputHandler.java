package com.editor;

import java.util.Set;
import java.util.HashSet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class InputHandler implements KeyListener {

    private final Set<Integer> pressedKeys = new HashSet<>();
    Boolean[] write = new Boolean[2];

    public InputHandler() {
        write[0] = false;
        write[1] = false;
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());

        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case 17:                        // CTRL zapisany w ten sposób, bo CTRL_DOWN_MASK ma taką wartość a nie działa
                        write[0] = true;
                        break;
                    case KeyEvent.VK_S:
                        write[1] = true;
                        break;
                }

            }
            if (write[0] && write[1]) {
                // do write file
                FileFunctions.saveFile();
                System.out.println("Zapis przez skrot");
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        /* not used */
    }
}
