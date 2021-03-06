package com.example.masterlingua;

import com.orm.SugarRecord;

public class CartesDeck extends SugarRecord {
    private String idcarteDeck;
    private String iddeck;
    private String idcarte;

    public CartesDeck() {}

    public CartesDeck(String id, String id_deck, String id_carte){
        this.idcarteDeck = id;
        this.iddeck = id_deck;
        this.idcarte = id_carte;
    }

    public String getId_deck(){
        return iddeck;
    }

    public String getId_carte(){
        return idcarte;
    }
}
