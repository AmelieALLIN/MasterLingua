package com.example.masterlingua;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;*/
import com.orm.SugarRecord;


public class Carte extends SugarRecord implements Serializable {
    public String question;
    public String reponse1;
    public String reponse2;
    public String reponse3;
    private String bonne_rep;

    // partie qui gère les tags de la carte, à garder !!
    private Categorie categorie;

    //private List<String> reponseliste = new ArrayList<>();
    //private String replistejson;


    public Carte() { }

    /*public Carte(String s, String r1, String r2, String r3, String s2){
        question = s;
        reponseliste.add(r1);
        reponseliste.add(r2);
        reponseliste.add(r3);
        bonne_rep = s2;
    }
    public Carte(String s, String r1, String r2, String s2){
        question = s;
        reponseliste.add(r1);
        reponseliste.add(r2);
        bonne_rep = s2;
    }
    public Carte(String s, String r1, String s2){
        question = s;
        reponseliste.add(r1);
        bonne_rep = s2;
    }
    public Carte(String s,String s2){
        question = s;
        bonne_rep = s2;
    }*/

    public Carte(String s, String r1, String r2, String r3, String s2){
        question = s;
        reponse1 = r1;
        reponse2 = r2;
        reponse3 = r3;
        bonne_rep = s2;
    }
    public Carte(String s, String r1, String r2, String s2){
        question = s;
        reponse1 = r1;
        reponse2 = r2;
        reponse3 = "";
        bonne_rep = s2;
    }
    public Carte(String s, String r1, String s2){
        question = s;
        reponse1 = r1;
        reponse2 = "";
        reponse3 = "";
        bonne_rep = s2;
    }
    public Carte(String s, String s2){
        question = s;
        reponse1 = "";
        reponse2 = "";
        reponse3 = "";
        bonne_rep = s2;
    }

    /*private Carte(Parcel in) {
        this.reponse = in.readString();
        this.reponseliste = new ArrayList<String>();
        in.readList(reponseliste, String.class.getClassLoader());
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>(){
        public CartecreateFromParcel(Parcel in) {
            return new Carte(in);
        }
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public  List<String> getReponseliste() {
        reponseliste = new Gson().fromJson(this.replistejson, new TypeToken<List<String>>(){}.getRawType());
        return reponseliste;
    }

    public setMyStringList(List<String> stringList){
        this.reponseliste = stringList;
    }

    @Override
    public long save(){
        this.replistejson = new Gson().toJson(stringList);
        return super.save();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reponse);
        dest.writeList(getReponseliste());
    }*/


    public String getQuestion(){
        return question;
    }

    /*public List<String> getReponse()
    {
        return reponseliste;
    }*/

    public String getReponse1() {
        return reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

public ArrayList<String> getReponses(){
        ArrayList<String> p=new ArrayList<>();
        p.add(reponse1);
        p.add(reponse2);
        p.add(reponse3);
        return p;
}

    public String getBonne_rep(){
        return bonne_rep;
    }
}
