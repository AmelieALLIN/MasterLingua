package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

public class JouerDeck3Type extends AppCompatActivity {
    ListView liste_cartes;
    TextView scorefinal;
    TextView scoretemporaire;
    static List<Carte> listcarte = new ArrayList<>();
    List<Carte> cartes = new ArrayList<>();
    List<CartesDeck> cartedecks = new ArrayList<>();
    static List<String> questioncarte = new ArrayList<>();
    Carte carte;
    static Deck deck;
    private static boolean  deja_initialise=false;
    private static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer_deck3_type);

        liste_cartes = (ListView) findViewById(R.id.liste_cartes);
        scorefinal = (TextView) findViewById(R.id.scorefinal);
        scoretemporaire = (TextView) findViewById(R.id.scoretempo);
        Button retour = findViewById(R.id.buttonretour);

        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();
        if(deja_initialise==false){
            deck = (Deck) bundle.getSerializable("deck");
            score=0;
        }


        if(deja_initialise==false){
            cartedecks = CartesDeck.findWithQuery(CartesDeck.class, "Select * from CARTES_DECK where iddeck = ?", deck.getId_deck());
            for(int i=0; i<cartedecks.size(); i++){
                System.out.println("iiiiiiiiiiiiiininnnnnnnntialisation +"+cartedecks.get(i).getId_carte());
                cartes = Carte.find(Carte.class, "idcarte = ?", cartedecks.get(i).getId_carte());
                for (int j=0; j<cartes.size(); j++){
                    listcarte.add(cartes.get(j));
                }
                questioncarte.add("Question "+i);
            }
        }
        for(int k=0;k<listcarte.size();k++){
            System.out.println("lllllliiiiiiiiiiste carte ="+listcarte.get(k).getIdCarte());
        }
        if(deja_initialise==true){
            score+=getIntent().getExtras().getInt("score");
            System.out.println("=============================================je suis passser par la");
        }
//question carte c est le seul truc qui restera
        final ArrayAdapter adapter_cartes = new ArrayAdapter(this, android.R.layout.simple_list_item_1, questioncarte);
        liste_cartes.setAdapter(adapter_cartes);
        scoretemporaire.setText("Score actuel = "+score);
        if(questioncarte.size()!=0){
            liste_cartes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String string = parent.getItemAtPosition(position).toString();
                    String[] ind = string.split(" ");
                    int indice = parseInt(ind[1]);
                    System.out.println("indddddddddddddddd"+ind);
                    for(int j=0;j<listcarte.size();j++){
                        System.out.println("3333333333333333333333333333333333333333333333333333333id carte= "+listcarte.get(j).getIdCarte());
                    }
                    System.out.println("oooooooooooooooooooooooooooooooooooooo"+listcarte.get(1).getType());
                    if (listcarte.get(indice).getType().equals("text")) {
                        carte = listcarte.get(indice);
                        System.out.println("********************* type texttext");
                        System.out.println("********************* type carte ="+carte.getType());
                        System.out.println("********************* id carte ="+carte.getIdCarte());
                        Intent afficher = new Intent(getApplicationContext(), JouerCarteD.class);
                        Bundle jouerCTT = new Bundle();
                        jouerCTT.putSerializable("carte", carte);
                        afficher.putExtras(jouerCTT);
                        startActivity(afficher);
                        finish();
                        questioncarte.remove(position);
                        deja_initialise = true;
                    } else {
                        if (listcarte.get(indice).getType().equals("qimage")) {
                            carte = listcarte.get(indice);
                            System.out.println("********************* type imagetext");
                            System.out.println("********************* type carte ="+carte.getType());
                            System.out.println("********************* id carte ="+carte.getIdCarte());
                            Intent afficher = new Intent(getApplicationContext(), JouerQuestionImageD.class);
                            Bundle jouerCTT = new Bundle();
                            jouerCTT.putSerializable("carte", carte);
                            afficher.putExtras(jouerCTT);
                            startActivity(afficher);
                            System.out.println("********************* 2");
                            finish();
                            questioncarte.remove(position);
                            deja_initialise = true;
                        } else {
                            if (listcarte.get(indice).getType().equals("rimage")) {
                                carte = listcarte.get(indice);
                                System.out.println("********************* type textimage");
                                System.out.println("********************* type carte ="+carte.getType());
                                System.out.println("********************* id carte ="+carte.getIdCarte());
                                Intent afficher = new Intent(getApplicationContext(), JouerCarteReponseImageD.class);
                                Bundle jouerCTT = new Bundle();
                                jouerCTT.putSerializable("carte", carte);//si marche pas 2eme classe
                                afficher.putExtras(jouerCTT);
                                startActivity(afficher);
                                System.out.println("********************* 3");
                                finish();
                                questioncarte.remove(position);
                                deja_initialise = true;
                            }
                        }
                    }
                    for (int m=0;m<questioncarte.size();m++) {
                        System.out.println("---------------------------------------"+questioncarte.get(m));
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "votre score est de :" + score, Toast.LENGTH_SHORT).show();
            scoretemporaire.setText(" ");
            scorefinal.setText("Votre score final = "+score);
            deja_initialise = false;


        }

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finalintent = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                startActivity(finalintent);
            }
        });
    }
}
