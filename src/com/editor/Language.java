package com.editor;

import java.util.ArrayList;

public class Language {

    private static class SectionMarker { //klasa przechowująca znaki rozpoczynające i kończące sekcję (np. "<" i ">", "{" i "}")
        public char beginning;
        public char ending;

        public SectionMarker(char beginning, char ending) {
            this.beginning = beginning;
            this.ending = ending;
        }
    }

    private ArrayList<String> keywords;
    private ArrayList<SectionMarker> sectionMarkers;

    public Language() {
        keywords = new ArrayList<String>();
        sectionMarkers = new ArrayList<SectionMarker>();
    }

    public void updateTextColors() { //??? implementacja

    }

    public void addKeyword(String word) {
        keywords.add(word);
    }

    public void addSectionMarker(char begin, char end) {
        sectionMarkers.add(new SectionMarker(begin, end));
    }

}