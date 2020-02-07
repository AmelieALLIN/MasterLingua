package com.example.masterlingua;

import java.io.Serializable;
import java.util.List;

public class Deck implements Serializable {
    private List<Carte> deck;

    public Deck(List<Carte> c){
        deck=c;
    }
}
