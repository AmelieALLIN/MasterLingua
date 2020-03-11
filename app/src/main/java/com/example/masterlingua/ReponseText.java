package com.example.masterlingua;

import com.orm.SugarRecord;

public class ReponseText extends SugarRecord implements Reponse {
    private String idreponse;
    private String nomreponse;
    private boolean br;
    private String idcarte;

    public ReponseText() {

    }

    public ReponseText(String id, String nom, String carte, boolean br) {
        this.idreponse = id;
        this.nomreponse = nom;
        this.idcarte = carte;
        this.br = br;
    }

    public String getNom() {
        return nomreponse;
    }

    @Override
    public String getIdCarte() {
        return idcarte;
    }

    @Override
    public String getIdReponse() {
        return idreponse;
    }

    public boolean getbr() {
        return br;
    }

}
