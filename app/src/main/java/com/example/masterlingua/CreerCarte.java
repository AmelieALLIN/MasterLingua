package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreerCarte extends AppCompatActivity {
    private Carte carte;
    private CheckBox checkAnswer1, checkAnswer2, checkAnswer3;
    private EditText question;
    private List<String> answers;
    String bonneReponse;
    private boolean b1, b2, b3;
    Context context = this;
    String type = "texte";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);

        question = findViewById(R.id.question);
        answers = new ArrayList<>();
        Button validate = findViewById(R.id.validate);
        Button retour = findViewById(R.id.retour);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean jouer = true;
                creationCarte(AfficherCarte.class, jouer);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean jouer = false;
                creationCarte(ChoisirCreationCarte.class, jouer);
            }
        });
    }

    public void creationCarte (Class nameClassForIntent, boolean jouer){
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
        if (question.getText().toString().isEmpty()) {
            CharSequence text = getText(R.string.warning_question);
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        } else {
            // vérifier si chaque champ de réponse est vide, sinon ajouter la réponse à la liste answers
            if (!answer1.getText().toString().isEmpty()) { // champ de réponse 1
                answers.add(answer1.getText().toString());
                if (checkAnswer1.isChecked()) b1 = true;
                if (b1) bonneReponse = answers.get(0);
            }
            if (!answer2.getText().toString().isEmpty()) { // champ de réponse 2
                answers.add(answer2.getText().toString());
                if (checkAnswer2.isChecked()) b2 = true;
                if (b2) bonneReponse = answers.get(1);
            }
            if (!answer3.getText().toString().isEmpty()) { // champ de réponse 3
                answers.add(answer3.getText().toString());
                if (checkAnswer3.isChecked()) b3 = true;
                if (b3) bonneReponse = answers.get(2);
            }
            int duration = Toast.LENGTH_SHORT;
            //Si la case bonne réponse cochée correspond à une réponse vide
            boolean[] good_br = new boolean[3];
            if (answer1.getText().toString().isEmpty() && checkAnswer1.isChecked()) {
                CharSequence text = getText(R.string.beware_good_answer);
                Toast.makeText(context, text, duration).show();
                good_br[0] = false;
            }
            if (answer2.getText().toString().isEmpty() && checkAnswer2.isChecked()) {
                CharSequence text = getText(R.string.beware_good_answer);
                Toast.makeText(context, text, duration).show();
                good_br[1] = false;
            }
            if (answer3.getText().toString().isEmpty() && checkAnswer3.isChecked()) {
                CharSequence text = getText(R.string.beware_good_answer);
                Toast.makeText(context, text, duration).show();
                good_br[2] = false;
            }
            if (!(answer1.getText().toString().isEmpty() && checkAnswer1.isChecked()))
                good_br[0] = true;
            if (!(answer2.getText().toString().isEmpty() && checkAnswer2.isChecked()))
                good_br[1] = true;
            if (!(answer3.getText().toString().isEmpty() && checkAnswer3.isChecked()))
                good_br[2] = true;

            System.out.println(" LAAAAAAAAA   ok1");
            carte = new Carte(idcarte, type);
            if (!checkAnswer1.isChecked() && !checkAnswer2.isChecked() && !checkAnswer3.isChecked()) { // si aucune br n'est indiquée
                CharSequence text = getText(R.string.bonne_rep);
                Toast.makeText(context, text, duration).show();
                answers.clear();
            }
            if(!(answers.size() >= 2)){
                CharSequence text = getText(R.string.au_moins_deux);
                Toast.makeText(context, text, duration).show();
                answers.clear();
            }
            if ((checkAnswer1.isChecked() || checkAnswer2.isChecked() || checkAnswer3.isChecked())&& answers.size() >= 2) {
                if (good_br[0] && good_br[1] && good_br[2]) {

                    QuestionText quest = new QuestionText(idquestion, question.getText().toString(), idcarte);
                    for (int i = 0; i < answers.size(); i++) {
                        idrep = UUID.randomUUID().toString();
                        if (answers.get(i).equals(bonneReponse)) {
                            br = true;
                        } else {
                            br = false;
                        }
                        String nomrep = answers.get(i);
                        ReponseText reponse = new ReponseText(idrep, nomrep, idcarte, br);
                        reponse.save();
                    }

                    carte.save();
                    quest.save();
                    CharSequence text = getText(R.string.card_created);
                    Toast.makeText(context, text, duration).show();
                    Intent afficherCarte = new Intent(getApplicationContext(), nameClassForIntent);
                    if(jouer){
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("carte", carte);
                        afficherCarte.putExtras(bundle);
                    }
                    startActivity(afficherCarte);
                    finish();
                }
            }
        }
    }

    public void onCheckBoxClicked(View view) {
        checkAnswer1 = findViewById(R.id.checkAnswer1);
        checkAnswer2 = findViewById(R.id.checkAnswer2);
        checkAnswer3 = findViewById(R.id.checkAnswer3);

        // s'assurer qu'au total une seule réponse est cochée correcte
        checkAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkAnswer2.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkAnswer1.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkAnswer1.setChecked(false);
                    checkAnswer2.setChecked(false);
                }
            }
        });
    }
}