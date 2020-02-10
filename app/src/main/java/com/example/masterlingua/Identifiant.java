package com.example.masterlingua;

public class Identifiant {
    private String niveau;
    private String theme;
    private String langue;

    public Identifiant(String n, String t, String l) {
        niveau = n;
        theme = t;
        langue = l;
    }

    public Identifiant(String n){
        niveau = n;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getTheme() {
        return theme;
    }

    public String getLangue() {
        return langue;
    }
}
