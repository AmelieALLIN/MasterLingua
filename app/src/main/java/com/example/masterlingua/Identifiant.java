package com.example.masterlingua;

import java.util.HashMap;
import java.util.Map;

public class Identifiant {
    private Map<Integer,String> niveau = new HashMap<>();
    private Map<Integer,String> theme = new HashMap<>();
    private Map<Integer,String> langue = new HashMap<>();
    static Integer cpt;

    public Identifiant(){
        cpt = 0;
    }

    public void putNiveau(String value) {
        cpt = cpt++;
        niveau.put(cpt, value);
    }
    public void putTheme(String value){
        cpt = cpt++;
        theme.put(cpt, value);
    }
    public void putLangue(String value){
        cpt = cpt++;
        langue.put(cpt, value);
    }
}
