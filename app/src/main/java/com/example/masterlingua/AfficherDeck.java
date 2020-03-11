package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AfficherDeck extends AppCompatActivity {
    ListView liste_cartes;
    TextView liste;
    List<Carte> cartes = Carte.listAll(Carte.class);

    static List<String> quests = new ArrayList<>();
    List<QuestionText> questionsdecks = new ArrayList<>();
    List<QuestionText> questionscarte = new ArrayList<>();
    List<Carte> listcarte = new ArrayList<>();
    Carte carte;
    static Deck deck;
    private Context context;
    int j;
    boolean intent_deck=false;
    private static boolean  deja_initialise=false;
    private static int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_deck);
        liste_cartes = (ListView) findViewById(R.id.liste_cartes);
        liste = findViewById(R.id.liste);


        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();
        if(deja_initialise==false){deck = (Deck) bundle.getSerializable("deck");
        score=0;}


        if(deja_initialise==false){
            List<CartesDeck> cartedecks = CartesDeck.findWithQuery(CartesDeck.class, "Select * from CARTES_DECK where iddeck = ?", deck.getId_deck());
            for(int i=0; i<cartedecks.size(); i++){
                questionsdecks = QuestionText.find(QuestionText.class,"idcarte = ?", cartedecks.get(i).getId_carte());
                for(int n=0; n<questionsdecks.size();n++)
                {
                    quests.add(questionsdecks.get(n).getNom_question());
                }
            }
        }
        if(deja_initialise==true){score+=getIntent().getExtras().getInt("score");}

        final ArrayAdapter adapter_cartes = new ArrayAdapter(this, android.R.layout.simple_list_item_1, quests);
        liste_cartes.setAdapter(adapter_cartes);
        if(quests.size()!=0){
        liste_cartes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                questionscarte = QuestionText.find(QuestionText.class,"nomquestion = ?", choix);
                for(int n=0;n<questionscarte.size();n++) {
                    listcarte = Carte.find(Carte.class, "idcarte = ?", questionscarte.get(n).getIdCarte());
                }
                for(int n=0;n<listcarte.size();n++) {
                    carte = listcarte.get(n);
                }
                Intent afficher = new Intent(getApplicationContext(), JouerCarte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                afficher.putExtras(bundle);
                startActivity(afficher);
                finish();
                quests.remove(position);
                deja_initialise=true;
                }
            });}
        else
            Toast.makeText(getApplicationContext(), "votre score est de :"+score, Toast.LENGTH_SHORT).show();
            deja_initialise=false;
    }
}