package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreerCarte extends AppCompatActivity {
    private Carte carte;
    private Spinner spinner1, spinner2;
    private CheckBox checkAnswer1, checkAnswer2, checkAnswer3;
    private EditText question;
    private List<String> answers;
    private boolean b1, b2, b3;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);

        question = findViewById(R.id.question);
        answers = new ArrayList<>();
        Button validate = findViewById(R.id.validate);
        spinner1 = findViewById(R.id.languagesList1);
        spinner2 = findViewById(R.id.languagesList2);

        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);

                //si le champ de la question est vide : toast pour dire que la question est obligatoire pour valider la carte
                if (question.getText().toString().isEmpty()){
                    CharSequence text = getText(R.string.warning_question);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
                else {
                    String bonneReponse = "";
                    // vérifier si chaque champ de réponse est vide, sinon ajouter la réponse à la liste answers
                    if(!answer1.getText().toString().isEmpty()){ // champ de réponse 1
                        answers.add(answer1.getText().toString());
                        if(checkAnswer1.isChecked()) b1 = true;
                        if(b1) bonneReponse = answers.get(0);
                    }
                    if(!answer2.getText().toString().isEmpty()){ // champ de réponse 2
                        answers.add(answer2.getText().toString());
                        if(checkAnswer2.isChecked()) b2 = true;
                        if(b2) bonneReponse = answers.get(1);
                    }
                    if(!answer3.getText().toString().isEmpty()){ // champ de réponse 3
                        answers.add(answer3.getText().toString());
                        if(checkAnswer3.isChecked()) b3 = true;
                        if(b3) bonneReponse = answers.get(2);
                    }

                    // en faire un logger
                    /*for(int i=0; i<answers.size(); i++){
                        System.out.println(answers.get(i) + i);
                    }*/

                    // construire la carte avec le bon nombre de réponses en argument
                    CharSequence text = getText(R.string.card_created);
                    int duration = Toast.LENGTH_SHORT;
                    String s1 = String.valueOf(spinner1.getSelectedItem());
                    String s2 = String.valueOf(spinner2.getSelectedItem());
                    if (answers.size() == 3) {
                        carte = new Carte(s1, s2, question.getText().toString(), answers.get(0), answers.get(1), answers.get(2), bonneReponse);
                        Toast.makeText(context, text, duration).show();
                        /*answers.remove(2);
                        answers.remove(1);
                        answers.remove(0);*/
                    }
                    if (answers.size() == 2) {
                        carte = new Carte(s1, s2, question.getText().toString(), answers.get(0), answers.get(1), bonneReponse);
                        Toast.makeText(context, text, duration).show();
                        System.out.println(carte.getReponse().get(0));
                        /*answers.remove(1);
                        answers.remove(0);*/
                    }
                    if (answers.size() == 1) {
                        carte = new Carte(s1, s2, question.getText().toString(), answers.get(0), bonneReponse);
                        Toast.makeText(context, text, duration).show();
                        //answers.remove(0);
                    }
                    if (answers.isEmpty()) {
                        carte = new Carte(s1, s2, question.getText().toString());
                        Toast.makeText(context, text, duration).show();
                    }

                    //test affichage contenu carte
                    if (!carte.getQuestion().isEmpty()) System.out.println(carte.getQuestion());
                    // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                    Intent afficherCarte = new Intent(getApplicationContext(), AfficherCarte.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    afficherCarte.putExtras(bundle);
                    startActivity(afficherCarte);
                    finish();
                    /*System.out.println("reponse : "+carte.getReponse().get(0));
                    if(!carte.getReponse().isEmpty()) {
                        for (int i = 0; i<carte.getReponse().size(); i++){
                            afficherCarte.putExtra("reponses"+i, carte.getReponse().get(i));
                            System.out.println("reponse"+i+" : "+carte.getReponse().get(i));
                        }
                    }
                    afficherCarte.putExtra("bonne_rep",carte.getBonne_rep());
                    System.out.println("BonneREEEEP"+carte.getBonne_rep());*/

                    // aller à l'activité qui affiche la carte
                    /*startActivity(afficherCarte);
                    finish();*/
                }
            }
        });
    }

    public void onCheckBoxClicked(View view){
        checkAnswer1 = findViewById(R.id.checkAnswer1);
        checkAnswer2 = findViewById(R.id.checkAnswer2);
        checkAnswer3 = findViewById(R.id.checkAnswer3);

        // s'assurer qu'au total une seule réponse est cochée correcte
        checkAnswer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer2.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer1.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer1.setChecked(false);
                    checkAnswer2.setChecked(false);
                }
            }
        });

    }
}
