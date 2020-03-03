package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreerCarte extends AppCompatActivity {
    private Carte carte;
    private CheckBox checkAnswer1, checkAnswer2, checkAnswer3;
    private EditText question;
    private List<String> answers;
    String bonneReponse;
    private boolean b1, b2, b3;
    Context context = this;
    private List<String> catForSpin;
    private List<Categorie>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);

        for(int i=0; i<; i++){
            catForSpin.add();
        }

        question = findViewById(R.id.question);
        answers = new ArrayList<>();
        Button validate = findViewById(R.id.validate);
        Button sauverCarte = findViewById(R.id.sauverCarte);

        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String idcarte = UUID.randomUUID().toString();
                String idquestion = UUID.randomUUID().toString();
                String idrep;
                boolean br;
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);

                for(int i=0; i<categoriesCrees.size(); i++) {
                    categories.add(categoriesCrees.get(i));
                }
                if (!categories.isEmpty()){
                    spinCategories = findViewById(R.id.spinCategories);
                    adapt = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, categories);
                    spinCategories.setAdapter(adapt);
                    for(int i=0; i<categories.size(); i++) {
                        System.out.println(categories.get(i).getNom());
                    }
                }

                //si le champ de la question est vide : toast pour dire que la question est obligatoire pour valider la carte
                if (question.getText().toString().isEmpty()){
                    CharSequence text = getText(R.string.warning_question);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
                else {
                    //String bonneReponse = "";
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
                    carte = new Carte(idcarte);
                    QuestionText quest = new QuestionText(idquestion,question.getText().toString(),idcarte);
                    for (int i = 0; i < answers.size(); i++) {
                        idrep = UUID.randomUUID().toString();
                        if (answers.get(i) == bonneReponse) {
                            System.out.println("BBBBBBBBBBBBBBBBBB br "+answers.get(i));
                            br = true;
                        }
                        else
                        {
                            br = false;
                        }
                        String nomrep = answers.get(i);
                        ReponseText reponse = new ReponseText(idrep,nomrep,idcarte,br);
                        reponse.save();
                    }
                    carte.save();
                    quest.save();
                    Toast.makeText(context, text, duration).show();
                        //System.out.println(carte.getReponse().get(0));
                    }

                    //test affichage contenu carte
                    List<QuestionText> quest = QuestionText.find(QuestionText.class,"idcarte = ?", carte.getIdCarte());
                    if (!quest.isEmpty())
                        for(int m=0;m<quest.size();m++){
                            System.out.println(quest.get(m).getNom_question());
                        }
                        // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                        Intent afficherCarte = new Intent(getApplicationContext(), AfficherCarte.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("carte", carte);
                        afficherCarte.putExtras(bundle);
                        afficherCarte.putExtra("valeur", 1);
                        startActivity(afficherCarte);
                        finish();
                    }
            });



        /*sauverCarte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int uniqueIDcarte = Integer.parseInt(UUID.randomUUID().toString());
                int uniqueIDquestion = Integer.parseInt(UUID.randomUUID().toString());
                int br = -1;
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
                    //String bonneReponse = "";
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
                    //for(int i=0; i<answers.size(); i++){
                    //    System.out.println(answers.get(i) + i);
                    }//
                    // construire la carte avec le bon nombre de réponses en argument
                    CharSequence text = getText(R.string.card_created);
                    int duration = Toast.LENGTH_SHORT;

                    QuestionText q = new QuestionText(uniqueIDquestion,question.getText().toString());
                    List<ReponseText> l = new ArrayList<>();
                    for(int i=0; i<answers.size(); i++){
                        int uniqueIDrep = Integer.parseInt(UUID.randomUUID().toString());
                        ReponseText r = new ReponseText(uniqueIDrep,answers.get(i));
                        l.add(r);
                        if(answers.get(i)==bonneReponse)
                        {
                            br = i;
                        }
                        carte = new Carte(uniqueIDcarte,q,l,br);=

                        Toast.makeText(context, text, duration).show();
                    if (answers.size() == 3) {
                        carte = new Carte(question.getText().toString(), answers.get(0), answers.get(1), answers.get(2), bonneReponse);
                        //carte.save();
                        Toast.makeText(context, text, duration).show();
                        //answers.remove(2);
                        //answers.remove(1);
                        //answers.remove(0);
                    }
                    if (answers.size() == 2) {
                        carte = new Carte(question.getText().toString(), answers.get(0), answers.get(1), bonneReponse);
                        //carte.save();
                        Toast.makeText(context, text, duration).show();
                        //System.out.println(carte.getReponses().get(0));
                    }
                    if (answers.size() == 1) {
                        carte = new Carte(question.getText().toString(), answers.get(0), bonneReponse);
                        //carte.save();
                        Toast.makeText(context, text, duration).show();
                        answers.removeAll(answers);
                    }
                    if (answers.isEmpty()) {
                        bonneReponse = null;
                        carte = new Carte(question.getText().toString(),bonneReponse);
                        //carte.save();
                        Toast.makeText(context, text, duration).show();
                    }

                    //test affichage contenu carte
                    //if (!carte.getQuestion().isEmpty()) System.out.println(carte.getQuestion());
                    // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                    Intent afficherCarte = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                    //Bundle bundle = new Bundle();
                    //bundle.putSerializable("carte", carte);
                    //afficherCarte.putExtras(bundle);
                    startActivity(afficherCarte);
                    finish();
                }
            }
        });*/

        sauverCarte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String idcarte = UUID.randomUUID().toString();
                String idquestion = UUID.randomUUID().toString();
                String idrep;
                boolean br;
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
                    //String bonneReponse = "";
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
                    System.out.println(" LAAAAAAAAA   ok1");
                    CharSequence text = getText(R.string.card_created);
                    int duration = Toast.LENGTH_SHORT;

                    System.out.println(" LAAAAAAAAA   ok1");
                    carte = new Carte(idcarte);
                    QuestionText quest = new QuestionText(idquestion,question.getText().toString(),idcarte);
                    for (int i = 0; i < answers.size(); i++) {
                        idrep = UUID.randomUUID().toString();
                        if (answers.get(i) == bonneReponse) {
                            br = true;
                        }
                        else
                        {
                            br = false;
                        }
                        String nomrep = answers.get(i);
                        ReponseText reponse = new ReponseText(idrep,nomrep,idcarte,br);
                        reponse.save();
                    }
                    carte.save();
                    quest.save();
                    Toast.makeText(context, text, duration).show();

                    // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                    Intent afficherCarte = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    afficherCarte.putExtras(bundle);
                    startActivity(afficherCarte);
                    finish();
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
