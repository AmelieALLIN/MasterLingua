package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherCarteReponsesImage extends AppCompatActivity {
    GridView listeReponse;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<ReponseImage> rep = new ArrayList<>();
    List<Bitmap> repImage = new ArrayList<>();
    TextView question;
    Bitmap bmp;
    int ind_br;
    Context context = this;
    String jointuredeckcarte,iddeck;
    List<Carte> cartes;
    LinearLayout layout;
    static int compteur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_carte_image_reponse);
        question = findViewById(R.id.question);
        layout=findViewById(R.id.layout);

        listeReponse = (GridView) findViewById(R.id.gridRep);

        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();

        carte = (Carte) bundle.getSerializable("carte");
        String idc = carte.getIdCarte();
        cartes= (List<Carte>) bundle.getSerializable("liste");
        iddeck=bundle.getString("iddeck");
        compteur=bundle.getInt("compteur");

        List<QuestionText> quest = QuestionText.find(QuestionText.class,"idcarte = ?", idc);
        for(int n=0; n<quest.size();n++){
            question.setText(quest.get(n).getNom_question());
        }

        rep = ReponseImage.find(ReponseImage.class,"idcarte = ?", idc);
        for(int n=0; n<rep.size();n++){
            if(rep.get(n).getImage()!=null){
                if(rep.get(n).getBr()==true)
                {
                    ind_br = n;
                }
                byte[] encodeByte = Base64.decode(rep.get(n).getImage(), Base64.DEFAULT);
                bmp= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
                repImage.add(bmp);
            }
        }

        final CustomerAdapter cm = new CustomerAdapter(getApplicationContext(), repImage);
        listeReponse.setAdapter(cm);
        listeReponse.setEnabled(false);


        layout.setOnTouchListener(new OnSwipeTouchListener(AfficherCarteReponsesImage.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(cartes.size()==1) {
                    if (compteur == 0) {
                        supprimerdeck();
                        fin();
                    } else {
                        fin();
                    }
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

                    fin();
                }
                else{
                    ajoutercartedeck();

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
    }

}

