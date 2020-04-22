package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontWindow extends Window implements ActionListener {

    JTextField jSizeField;
    JLabel jSizeLabel, jTypeLabel;
    JButton set;
    int value;
    static Font font;
    JComboBox fontTypes;


    public void CreateFont(){
        MakeFontWindow();
        AddFontSizePanel();
        AddFontTypePanel();
        AddSetButton();
        this.add(jSizeField);
        this.add(jSizeLabel);
        this.add(set);
        this.add(fontTypes);
        this.add(jTypeLabel);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void MakeFontWindow() {
        setTitle("Font Options");
        setSize(400, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void AddSetButton(){
        set = new JButton("Set");
        set.setBounds(50,330,100,20);
        set.addActionListener(this);
    }

    public void AddFontSizePanel() {
        jSizeField = new JTextField();
        jSizeField.setBounds(50,50,30,20);

        jSizeLabel = new JLabel("Font Size");
        jSizeLabel.setBounds(50,25,150,20);
    }

    public void AddFontTypePanel() {
        String fonts[]={"Arial","Comic Sans MS"};
        fontTypes = new JComboBox(fonts);
        fontTypes.setBounds(50,100,100,20);

        jTypeLabel = new JLabel("Font Type");
        jTypeLabel.setBounds(50,75,150,20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String valueStr = jSizeField.getText();
        value = Integer.parseInt(valueStr);

        String fontName = (String) fontTypes.getSelectedItem();

        int style = Font.PLAIN;
        font = new Font(fontName, style, value);
        NotepadWindow.changeFont();
    }
}