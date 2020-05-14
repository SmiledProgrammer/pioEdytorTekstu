package com.editor;

import com.editor.Language;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class AddLanguages {
    public static final String C="c";
    static private Language languageC;
    static private Map<String, Boolean> languages;
    public AddLanguages() {
        //ifCsetUp = false;
        languages = new HashMap<>();
        languages.put(C,false);
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
        languages.replaceAll((key,value)-> value=false);
        setIfSetup(s,true);
    }
    public static void updateText() {
        if(getIfSetup(C))
            languageC.updateTextColors();
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
}
