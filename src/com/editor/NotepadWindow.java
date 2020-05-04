package com.editor;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class NotepadWindow extends Window {

    static  JTextPane textArea;
    MenuBar menuBar;
    JScrollPane scrollPane;
    static Font currentFont;
    static Color currentFontColor = Color.BLACK;

    public void CreateNotepad(){
        MakeNotepadWindow();
        setupDefaultComponentOptions();
        setupTextArea();
        setupMenuBar();
        setVisible(true);
    }

    public void MakeNotepadWindow() {
        setTitle("Notepad+++");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private SimpleAttributeSet crateStyle(int style)
    {
        SimpleAttributeSet res = new SimpleAttributeSet();
        StyleConstants.setAlignment(res,style);
        //StyleConstants.s
        return res;
    }
    private  static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        // Set the font color
        StyleConstants.setForeground(attrs, c);

        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();

        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, true);
    }



    public void setupTextArea() {
        textArea = new JTextPane();
        textArea.addKeyListener(new InputHandler());
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        textArea.setFont(currentFont);
        textArea.setParagraphAttributes(crateStyle(StyleConstants.ALIGN_RIGHT), true);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }

    public void setupMenuBar() {
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
    }

    static public void setFont() {
        //textArea.setFont(currentFont);
        setJTextPaneFont(textArea, currentFont,currentFontColor);
       // textArea.getParagraphAttributes().
    }
}
