package com.example.masterlingua;

public class QuestionDeckMultiple {
    private String id;
    private String text;

    public QuestionDeckMultiple(String id, String text){
        this.id = id;
        this.text = text;
    }

    public String getId(){
        return id;
    }

    public String getText(){
        return text;
    }
}
