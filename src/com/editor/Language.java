package com.editor;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Language {

    private static class SectionMarker { //klasa przechowująca znaki rozpoczynające i kończące sekcję (np. "<" i ">", "{" i "}")
        public char beginning;
        public char ending;

        public SectionMarker(char beginning, char ending) {
            this.beginning = beginning;
            this.ending = ending;
        }
    }

    //private ArrayList<String> keywords; A może lepiej to zrobić za pomocą mapy:
    private Map<String, Color> keywords;
    private ArrayList<SectionMarker> sectionMarkers;

    public Language() {
        //keywords = new ArrayList<String>();
        keywords = new HashMap<>();
        sectionMarkers = new ArrayList<SectionMarker>();
    }

    public void updateTextColors() { //??? implementacja
        /*String text = NotepadWindow.textPane.getText();
        Style style = NotepadWindow.textPane.getStyle(StyleContext.DEFAULT_STYLE);
        */


    }

    public void addKeyword(String word, Color c) {
        keywords.put(word, c);
    }

    public void addSectionMarker(char begin, char end) {
        sectionMarkers.add(new SectionMarker(begin, end));
    }

}