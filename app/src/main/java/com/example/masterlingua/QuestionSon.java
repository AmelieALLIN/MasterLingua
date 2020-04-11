package com.example.masterlingua;

import com.orm.SugarRecord;

public class QuestionSon extends SugarRecord implements Question {
    private String idquestion;
    private String idcarte;
    String son;
    String url;
    public QuestionSon(){

    }

    public QuestionSon(String idquestion,String audio,String url, String idcarte) {
        this.idquestion = idquestion;
        this.idcarte = idcarte;
        son=audio;
        this.url=url;
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

    public String getUrl(){return this.url;}
}
