package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class AfficherListe extends AppCompatActivity {
    ListView CarteListView;
    List<Carte> cartes = Carte.listAll(Carte.class);
    List<ReponseText> reponses = ReponseText.listAll(ReponseText.class);
    List<QuestionText> questions = QuestionText.listAll(QuestionText.class);
    private EditText nom_deck;

    List<String> questioncarte = new ArrayList<>();
    List<QuestionText> questionfind = new ArrayList<>();
    List<Carte> idcarte = new ArrayList<>();
    ArrayList<Carte> l = new ArrayList<>();
    Carte carte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);

        nom_deck = findViewById(R.id.nom_deck);

        CarteListView = (ListView) findViewById(R.id.myListView);

        for (int i = 0; i < questions.size(); i++) {
            questioncarte.add(questions.get(i).getNom_question());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questioncarte);
        CarteListView.setAdapter(adapter);

        CarteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomC = (String) CarteListView.getItemAtPosition(position).toString();
                questionfind = QuestionText.find(QuestionText.class, "nomquestion = ?", nomC);
                for(int i=0;i<questionfind.size();i++){
                    idcarte = Carte.find(Carte.class, "idcarte = ?", questionfind.get(i).getIdCarte());
                }
                for (int n=0;n<idcarte.size();n++)
                {
                    carte = idcarte.get(n);
                }

                //System.out.println(carte.getType());
                //System.out.println(carte.getIdCarte());
                if(carte.getType().equals("texte")){
                    System.out.println(carte.getType());
                    Intent jouerCarteT = new Intent(getApplicationContext(), AfficherCarte.class );
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    jouerCarteT.putExtras(bundle);
                    startActivity(jouerCarteT);
                    finish();
                }

               else if(carte.getType().equals("rimage")){
                  System.out.println(carte.getType());
                    Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteReponsesImage.class );
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    jouerCarte.putExtras(bundle);
                    startActivity(jouerCarte);
                    finish();
                }


            }
        });
    }
}
