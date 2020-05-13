package com.editor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Language {

    protected static class SectionMarker { //klasa przechowująca znaki rozpoczynające i kończące sekcję (np. "<" i ">", "{" i "}")
        public char beginning;
        public char ending;

        public SectionMarker(char beginning, char ending) {
            this.beginning = beginning;
            this.ending = ending;
        }
    }

    protected Map<String, Color> keywords;
    protected ArrayList<SectionMarker> sectionMarkers;
    protected boolean highlightNumbers;

    public static String word;

    public Language() {
        keywords = new HashMap<>();
        sectionMarkers = new ArrayList<SectionMarker>();
        highlightNumbers = false;
    }

    protected static boolean isWhitespaceCharacter(char c) {
        switch (c) {
            case ' ': return true;
            case '\n': return true;
            case '\t': return true;
            default: return false;
        }
    }

    protected void changeTextColor(int pos, int length, Color color) { //funkcja zmieniająca kolor danego fragmentu tekstu (z tego co wiem trzeba usuwać tekst i wstawiać go na nowo z kolorkiem)
        JTextPane pane = NotepadWindow.textPane;
        StyledDocument doc = NotepadWindow.textPane.getStyledDocument();
        Style style = pane.addStyle(color.toString(), null);
        StyleConstants.setForeground(style, color);
        try {
            int test = pos + length;
            System.out.println(pos+" "+test+" "+word);
            doc.remove(pos, length);
            System.out.println(pos+" "+test+" "+word);
            doc.insertString(pos, word, style);
        } catch (BadLocationException ex) {}
    }

    protected int getNextWord(int startingIndex) { //zwraca indeks znalezionego słowa; słowo zapisuje w statycznej zmiennej globalnej "word"; startingIndex to indeks, od którego zaczynane jest sprawdzanie
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

    protected void checkKeywords(int startingIndex ) {
        /*Iterator it = keywords.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry element = (Map.Entry) it.next();
            String keyword = (String) element.getKey();
            if (word.equals(keyword)) {
                changeTextColor(startingIndex, word.length(), (Color) element.getValue());
            }
            it.remove();
        }*/
        if(keywords.containsKey(word)) {
            System.out.println(word + " " + startingIndex);
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
        int index = 0;
        word = "";
        while (index + word.length() + 1 < NotepadWindow.textPane.getText().length()) {
            index = getNextWord(index + word.length() + 1); // Pobieranie następnego słowa w tekście
            checkKeywords(index); // Słowa kluczowe

            // Liczby
            /* do zrobienia (trzeba rozpoznawać liczby ze stringa, np. "15030", "1032.432", itp.) */

            // Sekcje
            /* do zrobienia */
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
        Color c = new Color(100, 100, 50);
        System.out.println(c.toString());
    }


}