package com.example.masterlingua;

import com.orm.SugarRecord;

public class ReponseImage extends SugarRecord implements Reponse{
    private String idreponse;
    private boolean br;
    private String idcarte;
    String image;


    public ReponseImage() {

    }

    public ReponseImage(String id, String carte, boolean br, String image) {
        this.idreponse = id;
        this.idcarte = carte;
        this.br = br;
        this.image = image;
    }

    @Override
    public String getIdReponse() {
        return idreponse;
    }

    @Override
    public String getIdCarte(){
        return idcarte;
    }

    public String getImage(){
        return this.image;
    }

    public boolean getBr() { return this.br; }
}
