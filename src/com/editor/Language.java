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

    public Language() {
        keywords = new HashMap<>();
        sectionMarkers = new ArrayList<SectionMarker>();
        highlightNumbers = false;
    }

    private static boolean isWhitespaceCharacter(char c) {
        switch (c) {
            case ' ': return true;
            case '\n': return true;
            case '\t': return true;
            default: return false;
        }
    }

    private void changeTextColor(int pos, int length, Color color) { //funkcja zmieniająca kolor danego fragmentu tekstu (z tego co wiem trzeba usuwać tekst i wstawiać go na nowo z kolorkiem)
        StyledDocument doc = NotepadWindow.textPane.getStyledDocument();
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setForeground(sas, color);
        NotepadWindow.ignoreNextEdit = true;
        doc.setCharacterAttributes(pos, length, sas, false);
        StyleConstants.setForeground(sas, Color.BLACK);
    }

    private int getNextWord(int startingIndex) { //zwraca indeks znalezionego słowa; słowo zapisuje w statycznej zmiennej globalnej "word"; startingIndex to indeks, od którego zaczynane jest sprawdzanie
        word = "";
        String text = NotepadWindow.textPane.getText();
        int i = startingIndex;
        int beginningIndex;
        char c;
        do { //szukanie początku słowa
            c = text.charAt(i);
            i++;
        } while (isWhitespaceCharacter(c) && i < text.length());
        beginningIndex = i - 1;
        while (!isWhitespaceCharacter(c) && i < text.length()) { //szukanie końca słowa
            word += c;
            c = text.charAt(i);
            i++;
        }
        if (!isWhitespaceCharacter(c) && i == text.length()) //dodawanie ostatniego znaku
            word += c;
        return beginningIndex;
    }

    private void checkKeywords(int startingIndex) {
        if (keywords.containsKey(word)) {
            //System.out.println(startingIndex + ". " + word);
            changeTextColor(startingIndex, word.length(), (Color) keywords.get(word));
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void updateTextColors() {
        int index = -1;
        word = "";
        while (index + word.length() + 1 < NotepadWindow.textPane.getText().length()) {
            index = getNextWord(index + word.length() + 1); // Pobieranie następnego słowa w tekście
            checkKeywords(index); // Słowa kluczowe

            // Liczby
            /* do zrobienia (trzeba rozpoznawać liczby ze stringa, np. "15030", "1032.432", itp.) */
            if(isNumeric(word) && highlightNumbers) {
                changeTextColor(index, word.length(), Color.MAGENTA);
            }
            // Sekcje
            int i=0;
            for(SectionMarker marker : sectionMarkers) {
                for(char c : word.toCharArray()) {
                    if (c == marker.beginning || c == marker.ending) {
                        changeTextColor(index + i, 1, Color.CYAN);
                    }
                    i++;
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
        String text = "Ala ma kota.\nI jeszcze   psa!";
        int index = -1;
        l.word = "";
        while (index + word.length() + 1 < text.length()) {
            index = l.getNextWord(index + word.length() + 1);
            System.out.println(index + ". " + word);
        }
    }

}