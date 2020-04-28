package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorWindow extends Window implements ActionListener {

    JLabel jButtonLabel;
    JButton jBackgroundColorButton, jTextColorButton, jMenuBarColorButton, jMenuTreeColorButton, jMenuSelectColorButton, jMenuHoverButton;

    public void CreateColor(){
        MakeColorWindow();
        setupDefaultComponentOptions();
        AddButtons();
        AddLabel();
        this.setLayout(null);
        this.setVisible(true);
    }

    public void MakeColorWindow(){
        setTitle("Color Options");
        setSize(500, 500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void AddButtons() {
        jBackgroundColorButton = new JButton("Background");
        jBackgroundColorButton.setBounds(25,50,200,20);
        jBackgroundColorButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Background");
        this.add(jBackgroundColorButton);

        jTextColorButton = new JButton("Text");
        jTextColorButton.setBounds(25,75,200,20);
        jTextColorButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Text");
        this.add(jTextColorButton);

        jMenuBarColorButton = new JButton("Menu Bar");
        jMenuBarColorButton.setBounds(25,100,200,20);
        jMenuBarColorButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Menu Bar");
        this.add(jMenuBarColorButton);

        jMenuTreeColorButton = new JButton("Menu Tree");
        jMenuTreeColorButton.setBounds(25,125,200,20);
        jMenuTreeColorButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Menu Tree");
        this.add(jMenuTreeColorButton);

        jMenuSelectColorButton = new JButton("Menu Selected");
        jMenuSelectColorButton.setBounds(25,150,200,20);
        jMenuSelectColorButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Menu Selected");
        this.add(jMenuSelectColorButton);

        jMenuHoverButton = new JButton("Menu Hovered");
        jMenuHoverButton.setBounds(25,175,200,20);
        jMenuHoverButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Menu Hovered");
        this.add(jMenuHoverButton);
    }

    public void AddLabel() {
        jButtonLabel = new JLabel("Change the Color of:");
        jButtonLabel.setBounds(25,25,150,20);
        this.add(jButtonLabel);
    }

    public void changeBackground(){
        Color initialBackground = NotepadWindow.textArea.getBackground();
        Color background = JColorChooser.showDialog(this, "Choose", initialBackground);
        NotepadWindow.textArea.setBackground(background);
    }

    public void changeText(){
        Color initialText = NotepadWindow.textArea.getForeground();
        Color text = JColorChooser.showDialog(this, "Choose", initialText);
        NotepadWindow.textArea.setForeground(text);
    }

    public void changeMenuBar(){
        Color initialMenuBar = NotepadWindow.textArea.getForeground();
        Color menuBar = JColorChooser.showDialog(this, "Choose", initialMenuBar);
        MenuOption.barColor = menuBar;
    }

    public void changeMenuTree(){
        Color initialMenuTree = NotepadWindow.textArea.getForeground();
        Color text = JColorChooser.showDialog(this, "Choose", initialMenuTree);
        NotepadWindow.textArea.setForeground(text);
    }

    public void changeMenuSelected(){
        Color initialMenuSelected = NotepadWindow.textArea.getForeground();
        Color text = JColorChooser.showDialog(this, "Choose", initialMenuSelected);
        NotepadWindow.textArea.setForeground(text);
    }

    public void changeMenuHovered(){
        Color initialMenuHovered = NotepadWindow.textArea.getForeground();
        Color text = JColorChooser.showDialog(this, "Choose", initialMenuHovered);
        NotepadWindow.textArea.setForeground(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Background": changeBackground(); break;
            case "Text": changeText(); break;
            case "Menu Bar": changeMenuBar(); break;
            case "Menu Tree": changeMenuTree(); break;
            case "Menu Selected": changeMenuSelected(); break;
            case "Menu Hovered": changeMenuHovered(); break;
        }
    }
}