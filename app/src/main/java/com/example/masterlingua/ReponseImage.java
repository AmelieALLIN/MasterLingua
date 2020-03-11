package com.example.masterlingua;

import com.orm.SugarRecord;

public class ReponseImage extends SugarRecord implements Reponse{
    private String idreponse;
    private boolean br;
    private String idcarte;
    byte[] image;


    public ReponseImage() {

    }

    public ReponseImage(String id, String carte, boolean br, byte[] image) {
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

    public byte[] getImage(){
        return this.image;
    }

    public boolean getBr() { return this.br; }
}
