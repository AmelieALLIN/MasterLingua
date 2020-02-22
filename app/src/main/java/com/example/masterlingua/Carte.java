package com.example.masterlingua;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Carte implements Serializable {
    //private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private int id; // private final int id = ID_FACTORY.getAndIncrement();
    private Question question;
    private List<Reponse> reponses;
    private int ind_br;

    public Carte() { }

    public Carte(int id, Question question, List<Reponse> reponses, int ind_br) {
        this.id = id;
        this.question = question;
        this.reponses = reponses;
        this.ind_br = ind_br;
    }

    public final int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public int getInd_br() {
        return ind_br;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public void setInd_br(int ind_br) {
        this.ind_br = ind_br;
    }
}
