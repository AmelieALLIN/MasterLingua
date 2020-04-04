package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;

public class QuestionText extends SugarRecord implements Question {
    private String idquestion;
    private String nomquestion;
    private String idcarte;

    public QuestionText(){

    }

    public QuestionText(String id_question, String nom_question, String id_carte) {
        this.idquestion = id_question;
        this.nomquestion = nom_question;
        this.idcarte = id_carte;
    }

    public String getNom_question() {
        return nomquestion;
    }

    @Override
    public String getIdQuestion() {
        return idquestion;
    }

    @Override
    public String getIdCarte() {
        return idcarte;
    }
}
