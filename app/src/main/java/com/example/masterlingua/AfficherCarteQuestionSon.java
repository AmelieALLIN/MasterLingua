package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherCarteQuestionSon extends AppCompatActivity {
    static int score;
    static boolean jouer;
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    String ok, idc,iddeck;
    String monfichier;
    Context context = this;
    List<Carte> cartes;
    Button play, stop;
    MediaPlayer mediaPlayer;
    RelativeLayout layout;
    String jointuredeckcarte;
    static int compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jouer_carte_question_son);
        reps = findViewById(R.id.list);
        play= findViewById(R.id.play);
        stop= findViewById(R.id.stop);
        layout=findViewById(R.id.layout);
        Bundle bundle = getIntent().getExtras();
        carte =(Carte)bundle.getSerializable("carte");
        cartes = (List<Carte>) bundle.getSerializable("liste");
        iddeck=bundle.getString("iddeck");
        compteur=bundle.getInt("compteur");
        idc =carte.getIdCarte();
        List<QuestionSon> quest = QuestionSon.find(QuestionSon.class, "idcarte = ?", idc);
        for(
                int n = 0; n<quest.size();n++)

        {
            monfichier = quest.get(n).getUrl();
        }

        mediaPlayer=new

                MediaPlayer();

        try

        {
            mediaPlayer.setDataSource(monfichier);
            mediaPlayer.prepare();
        } catch(
                IOException e)

        {
            e.printStackTrace();
        }

        reponses =ReponseText.find(ReponseText .class,"idcarte = ?",idc);
        for(
                int i = 0;i<reponses.size();i++)

        {
            nom_rep.add(reponses.get(i).getNom());
        }

        for(
                int y = 0;y<reponses.size();y++)

        {
            if (reponses.get(y).getbr() == true) {
                ok = reponses.get(y).getNom();
            }
        }


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nom_rep);
        reps.setAdapter(adapter);
        layout.setOnTouchListener(new OnSwipeTouchListener(AfficherCarteQuestionSon.this) {
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
        }

    }

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

