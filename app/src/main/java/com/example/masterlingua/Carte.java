package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Carte  extends SugarRecord implements Serializable {
    //private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private String idcarte; // private final int id = ID_FACTORY.getAndIncrement();
    private Type type;

    public Carte() { }

    public Carte(String id,Type type) {
        this.idcarte = id;
        this.type = type;
    }

    public final String getIdCarte() {
        return idcarte;
    }

    public final Type getType(){
        return type;
    }
}
