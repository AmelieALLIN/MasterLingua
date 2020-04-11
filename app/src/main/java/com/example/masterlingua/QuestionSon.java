package com.example.masterlingua;

import com.orm.SugarRecord;

public class QuestionSon extends SugarRecord implements Question {
    private String idquestion;
    private String idcarte;
    //byte[] son;
    String son;
    public QuestionSon(){

    }

    public QuestionSon(String idquestion,String audio, String idcarte) {
        this.idquestion = idquestion;
        this.idcarte = idcarte;
        son=audio;
    }

    @Override
    public String getIdQuestion() {
        return idquestion;
    }

    @Override
    public String getIdCarte() {
        return idcarte;
    }


    public String getSon(){
        return this.son;
    }
}
