package com.editor;

import com.editor.Language;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class AddLanguages {
    public static final String C = "c";
    public static final String JAVA = "java";
    static private Language languageC;
    static private Language languageJava;
    static private Map<String, Boolean> languages;

    public AddLanguages() {
        //ifCsetUp = false;
        languages = new HashMap<>();
        languages.put(C,false);
        languages.put(JAVA,false);
    }

    public static boolean getIfSetup(String l) {
        return languages.get(l);
    }

    public static void setIfSetup(String l, boolean b) {
        try {
            languages.replace(l,b);
        } catch (NullPointerException e) {
            System.out.println("Jezyk nie istnieje, to po co go wpisujesz");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Nie możesz podać takiego jezyka");
            System.exit(0);
        }
    }

    public static void disableAll() {
        languages.replaceAll((key,value)-> value=false);
    }

    public static void disableAllExcept(String s) {
        disableAll();
        setIfSetup(s,true);
    }

    public static void updateText() {
        if(getIfSetup(C))
            languageC.updateTextColors();

        if (getIfSetup(JAVA))
            languageJava.updateTextColors();
        // dla kolejnych jezykow kolejne warunki
    }

    public static void setUpC() {   // należy dokończyć
        if( /*!ifCsetUp*/ !getIfSetup(C) || languageC != null)
            return;
        languageC = new Language();
        languageC.addKeyword("void", Color.RED);
        languageC.addKeyword("int", Color.RED);
        languageC.addKeyword("char", Color.RED);
        languageC.addKeyword("double", Color.RED);
        languageC.addKeyword("typedef", Color.blue);
        languageC.addKeyword("struct", Color.blue); // resztę trzeba dopisać, to tylko prototyp

        languageC.addSectionMarker('{', '}');
        languageC.setHighlightNumbers(true);
        //ifCsetUp = true;
    }

    public static void setUpJava() {
        if(!getIfSetup(JAVA) || languageJava != null)
            return;

        languageJava = new Language();
        languageJava.addKeyword("byte", Color.ORANGE);
        languageJava.addKeyword("short", Color.ORANGE);
        languageJava.addKeyword("int", Color.ORANGE);
        languageJava.addKeyword("long", Color.ORANGE);
        languageJava.addKeyword("float", Color.ORANGE);
        languageJava.addKeyword("double", Color.ORANGE);
        languageJava.addKeyword("boolean", Color.ORANGE);
        languageJava.addKeyword("void", Color.ORANGE);
        languageJava.addKeyword("true", Color.ORANGE);
        languageJava.addKeyword("false", Color.ORANGE);
        languageJava.addKeyword("class", Color.ORANGE);
        languageJava.addKeyword("new", Color.ORANGE);
        languageJava.addKeyword("null", Color.ORANGE);
        languageJava.addKeyword("instanceof", Color.ORANGE);
        languageJava.addKeyword("package", Color.ORANGE);
        languageJava.addKeyword("import", Color.ORANGE);
        languageJava.addKeyword("main", Color.YELLOW);
        languageJava.addKeyword("public", Color.ORANGE);
        languageJava.addKeyword("protected", Color.ORANGE);
        languageJava.addKeyword("default", Color.ORANGE);
        languageJava.addKeyword("private", Color.ORANGE);
        languageJava.addKeyword("if", Color.ORANGE);
        languageJava.addKeyword("else", Color.ORANGE);
        languageJava.addKeyword("switch", Color.ORANGE);
        languageJava.addKeyword("case", Color.ORANGE);
        languageJava.addKeyword("break", Color.ORANGE);
        languageJava.addKeyword("default", Color.ORANGE);
        languageJava.addKeyword("for", Color.ORANGE);
        languageJava.addKeyword("continue", Color.ORANGE);
        languageJava.addKeyword("return", Color.ORANGE);
        languageJava.addKeyword("while", Color.ORANGE);
        languageJava.addKeyword("do", Color.ORANGE);
        languageJava.addKeyword("static", Color.ORANGE);
        languageJava.addKeyword("final", Color.ORANGE);
        languageJava.addKeyword("extends", Color.ORANGE);
        languageJava.addKeyword("implements", Color.ORANGE);
        languageJava.addKeyword("@Override", Color.GREEN);
        languageJava.addKeyword("enum", Color.ORANGE);
        languageJava.addKeyword("try", Color.ORANGE);
        languageJava.addKeyword("catch", Color.ORANGE);
        languageJava.addKeyword("throws", Color.ORANGE);
        languageJava.addKeyword("throw", Color.ORANGE);
        languageJava.addKeyword("abstract", Color.ORANGE);
        languageJava.addKeyword("interface", Color.ORANGE);
        languageJava.addKeyword("var", Color.ORANGE);
        languageJava.addKeyword("transient", Color.ORANGE);

        languageJava.addSectionMarker('{', '}');
        languageJava.setHighlightNumbers(true);
    }
}
