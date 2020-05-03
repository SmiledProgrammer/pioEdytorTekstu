package com.editor;

public class PatternFinder {

    private String text;
    private String pattern;
    private int lastPosition;

    private int[] createPrefixSuffixTable() {
        int[] repetition = new int[pattern.length() + 1];
        repetition[0] = 0;
        int i = 0;
        for (int j = 2; j < pattern.length(); j++) {
            if (pattern.charAt(i) == pattern.charAt(j-1)) {
                i++;
                repetition[j] = i;
            } else {
                i = 0;
                repetition[j] = 0;
            }
        }
        return repetition;
    }

    public int findPattern(int startingIndex) {
        int[] psTable = createPrefixSuffixTable();
        int j = 0;
        for (int i = startingIndex; i < text.length(); i++) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == pattern.length())
                    return i - pattern.length() + 1;
            } else {
                j = Math.max(psTable[j] - 1, 0);
            }
        }
        return -1;
    }

    public int findNext() {
        if (lastPosition + pattern.length() < text.length()) {
            int pos = findPattern(lastPosition);
            lastPosition = pos + 1;
            return pos;
        } else {
            return -1;
        }
    }

    public void setStrings(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        lastPosition = 0;
    }

    public void updateTextString(String text) {
        this.text = text;
    }

    //remove l8r
    public static void main(String[] args) {
        PatternFinder pf = new PatternFinder();
        pf.setStrings("ala ma kota,\na kot ma ale,\nala go kocha,\na kot jej wcale", "kot");
        int found = 0;
        found = pf.findNext();
        while (found >= 0) {
            System.out.println(found);
            found = pf.findNext();
        }
    }
}