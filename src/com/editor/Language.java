package com.editor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;


public class Language {

    private static class SectionMarker { //klasa przechowująca znaki rozpoczynające i kończące sekcję (np. "<" i ">", "{" i "}")
        public char beginning;
        public char ending;

        public SectionMarker(char beginning, char ending) {
            this.beginning = beginning;
            this.ending = ending;
        }
    }

    private Map<String, Color> keywords;
    private ArrayList<SectionMarker> sectionMarkers;
    private boolean highlightNumbers;

    public static String word;
    private int newLines;

    public Language() {
        keywords = new HashMap<>();
        sectionMarkers = new ArrayList<SectionMarker>();
        highlightNumbers = false;
    }

    private boolean isWhitespaceCharacter(char c) {
        //dodaj sprawdzanie czy nie należy do sekcji
        for (SectionMarker sm : sectionMarkers) {
            if (c == sm.beginning || c == sm.ending)
                return false;
        }
        return ((int)c <= 47 || (int)c >= 58 && (int)c <= 64 || (int)c >= 91 && (int)c <= 96 || (int)c >= 123 && (int)c <= 127) ? true : false;
    }

    private void resetTextColor() {
        StyledDocument doc = NotepadWindow.textPane.getStyledDocument();
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setForeground(sas, Color.BLACK);
        NotepadWindow.ignoreNextEdit = true;
        doc.setCharacterAttributes(0, NotepadWindow.textPane.getText().length(), sas, false);

    }

    private void changeTextColor(int pos, int length, Color color) { //funkcja zmieniająca kolor danego fragmentu tekstu (z tego co wiem trzeba usuwać tekst i wstawiać go na nowo z kolorkiem)
        pos -= newLines; // Korekcja pozycji, bo StyledDocument ignoruje znaki nowych linii
        StyledDocument doc = NotepadWindow.textPane.getStyledDocument();
        SimpleAttributeSet sas = new SimpleAttributeSet();

        StyleConstants.setForeground(sas, color);
        NotepadWindow.ignoreNextEdit = true;
        doc.setCharacterAttributes(pos, length, sas, false);
    }

    private int getNextWord(int startingIndex) { //zwraca indeks znalezionego słowa; słowo zapisuje w statycznej zmiennej globalnej "word"; startingIndex to indeks, od którego zaczynane jest sprawdzanie
        word = "";
        String text = NotepadWindow.textPane.getText();
        int i = startingIndex;
        int beginningIndex;
        char c;
        do { //szukanie początku słowa
            c = text.charAt(i++);
            if (c == '\n')
                newLines++;
        } while (isWhitespaceCharacter(c) && i < text.length());
        beginningIndex = i - 1;
        while (!isWhitespaceCharacter(c) && i < text.length()) { //szukanie końca słowa
            word += c;
            c = text.charAt(i);
            if (c == '\n')
                newLines++;
            i++;
        }
        if (!isWhitespaceCharacter(c) && i == text.length()) //dodawanie ostatniego znaku
            word += c;
        return beginningIndex;
    }

    private void checkKeywords(int startingIndex) {
        System.out.println("1. " + word + " - " + word.length());
        if (keywords.containsKey(word)) {
            System.out.println("2. " + word + " - " + word.length());
            changeTextColor(startingIndex, word.length(), (Color) keywords.get(word));
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public void updateTextColors() {
        resetTextColor();
        int index = -1;
        word = "";
        newLines = 0;
        while (index + word.length() + 1 < NotepadWindow.textPane.getText().length()) {
            index = getNextWord(index + word.length() + 1); // Pobieranie następnego słowa w tekście
            checkKeywords(index); // Słowa kluczowe

            if (isNumeric(word) && highlightNumbers) { // Liczby
                changeTextColor(index, word.length(), Color.MAGENTA);
            }

            if (word.length() == 1) {
                for (SectionMarker marker : sectionMarkers) { // Sekcje
                    if (word.charAt(0) == marker.beginning || word.charAt(0) == marker.ending) {
                        changeTextColor(index, 1, Color.CYAN);
                    }
                }
            }
        }
    }

    public void addKeyword(String word, Color c) {
        keywords.put(word, c);
    }

    public void addSectionMarker(char begin, char end) {
        sectionMarkers.add(new SectionMarker(begin, end));
    }

    public void setHighlightNumbers(boolean value) {
        highlightNumbers = value;
    }

    //do usunięcia potem
    public static void main(String[] args) {
        Language l = new Language();
        String text = "Alamakota.\nno i jeszcze   psa!";
        int index = -1;
        l.word = "";
        while (index + word.length() + 1 < text.length()) {
            index = l.getNextWord(index + word.length() + 1);
            System.out.println(index + ". " + word);
        }
        System.out.println(text.charAt(11));
    }

}