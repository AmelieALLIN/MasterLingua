package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Carte  extends SugarRecord implements Serializable {
    //private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private String idcarte; // private final int id = ID_FACTORY.getAndIncrement();
    private String type;

    public Carte() { }

    public Carte(String id,String type) {
        this.idcarte = id;
        this.type=type;
    }
    public Carte(String id) {
        this.idcarte = id;
    }

    public final String getIdCarte() {
        return idcarte;
    }
     public String getType(){
        return this.type;
     }

}
