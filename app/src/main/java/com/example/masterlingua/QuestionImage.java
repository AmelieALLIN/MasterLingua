package com.example.masterlingua;

import com.orm.SugarRecord;

public class QuestionImage extends SugarRecord implements Question {
    private String idquestion;
    private String idcarte;

    String image;
    //byte[] image;

    public QuestionImage(){

    }

    public QuestionImage(String idquestion,String imagee, String idcarte) {
        this.idquestion = idquestion;
        this.idcarte = idcarte;
        image=imagee;
    }

    @Override
    public String getIdQuestion() {
        return idquestion;
    }

    @Override
    public String getIdCarte() {
        return idcarte;
    }


    public String getImage(){
        return this.image;
    }
}
