package com.example.masterlingua;

import com.orm.SugarRecord;

public class QuestionImage extends SugarRecord implements Question {
    private String idquestion;
    private String idcarte;

    public QuestionImage(){

    }

    public QuestionImage(String id_question, String id_carte) {
        this.idquestion = id_question;
        this.idcarte = id_carte;
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
