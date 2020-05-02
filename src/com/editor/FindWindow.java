package com.editor;

import javax.swing.*;
import java.awt.*;

public class FindWindow extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JTextField textField;

    public FindWindow() {
        setTitle("Find in text");
        setSize(320, 100);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label = new JLabel("Type in a string to find:");
        panel.add(label);
        textField = new JTextField();
        panel.add(textField);

        add(panel);
    }
}