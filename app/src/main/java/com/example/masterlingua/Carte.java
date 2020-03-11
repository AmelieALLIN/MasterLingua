package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Carte  extends SugarRecord implements Serializable {
    //private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private String idcarte; // private final int id = ID_FACTORY.getAndIncrement();

    public Carte() { }

    public Carte(String id) {
        this.idcarte = id;
    }

    public final String getIdCarte() {
        return idcarte;
    }
}
