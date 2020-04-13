package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherCarteQuestionImage extends AppCompatActivity {
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<QuestionImage> quest=new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    ImageView question;
    String ok,iddeck;
    List<Carte> cartes;
    LinearLayout layout;
    String jointuredeckcarte;
    static int compteur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_carte_image_question);
        reps =  findViewById(R.id.list);
        layout = findViewById(R.id.layout);
        question= findViewById(R.id.q);
        Bundle bundle = getIntent().getExtras();
        carte = (Carte) bundle.getSerializable("carte");
        cartes= (List<Carte>) bundle.getSerializable("liste");
        iddeck=bundle.getString("iddeck");
        compteur=bundle.getInt("compteur");
        String idc = carte.getIdCarte();
        reponses = ReponseText.find(ReponseText.class,"idcarte = ?", idc);
        for(int i=0;i<reponses.size();i++)
        {
            nom_rep.add(reponses.get(i).getNom());
        }

        for(int y=0;y<reponses.size();y++){
            if(reponses.get(y).getbr() == true){
                ok = reponses.get(y).getNom();
            }
        }

        quest = QuestionImage.find(QuestionImage.class,"idcarte = ?", idc);
        for(int n=0; n<quest.size();n++){
            byte[] encodeByte = Base64.decode(quest.get(n).getImage(), Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            question.setImageBitmap(bmp);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nom_rep);
        reps.setAdapter(adapter);
        reps.setEnabled(false);

        layout.setOnTouchListener(new OnSwipeTouchListener(AfficherCarteQuestionImage.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(cartes.size()==1){
                    if(compteur==0){
                        supprimerdeck();
                        fin();
                    }

                    else
                    {fin();}
                }
                else{
                    cartes.remove(0);
                    next();}

            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                if(cartes.size()==1){
                    ajoutercartedeck();
                    System.out.println(compteur);
                    fin();
                }
                else{
                    ajoutercartedeck();
                    System.out.println(compteur);
                    cartes.remove(0);
                    next();}
            }

            @Override
            public void onSwipeUp(){
                super.onSwipeUp();

                if(compteur==0){
                    supprimerdeck();
                    fin();
                }

                else
                {fin();}
            }


        });
    }

    public void next(){
        carte = cartes.get(0);
        System.out.println("iddd"+carte.getIdCarte());
        System.out.println("tyyype"+carte.getType());

        if (carte.getType().equals("texte")) {
            Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteTexte.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putString("iddeck",iddeck);
            bundle.putInt("compteur",compteur);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();

        } else if (carte.getType().equals("rimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteReponsesImage.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putString("iddeck",iddeck);
            bundle.putInt("compteur",compteur);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        } else if (carte.getType().equals("qimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteQuestionImage.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putString("iddeck",iddeck);
            bundle.putInt("compteur",compteur);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        }
        else if (carte.getType().equals("qson")) {
            Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteQuestionSon.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putString("iddeck",iddeck);
            bundle.putInt("compteur",compteur);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        }}

    public void fin(){
        Intent fin = new Intent(getApplicationContext(), AfficherListeDeck.class);
        startActivity(fin);
        finish();
    }


    public void ajoutercartedeck(){
        jointuredeckcarte = UUID.randomUUID().toString();
        CartesDeck carteDeck = new CartesDeck(jointuredeckcarte,iddeck,carte.getIdCarte());
        carteDeck.save();
        compteur+=1;
    }


    public void supprimerdeck(){
        Deck.executeQuery("DELETE FROM DECK WHERE IDDECK = '" +iddeck  + "'");
        CartesDeck.executeQuery("DELETE FROM CARTES_DECK WHERE IDDECK = '" +iddeck  + "'");
    }

}



