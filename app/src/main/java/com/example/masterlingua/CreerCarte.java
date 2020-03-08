package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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
    Spinner spinCategories;

    Categorie catChoisie = null;
    String idCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);

        question = findViewById(R.id.question);
        answers = new ArrayList<>();
        Button validate = findViewById(R.id.validate);
        Button sauverCarte = findViewById(R.id.sauverCarte);
        spinCategories = findViewById(R.id.spinCategories);
        idCat = UUID.randomUUID().toString();

        spinCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idCat = UUID.randomUUID().toString();
                String nomCat = spinCategories.getItemAtPosition(position).toString();
                catChoisie = new Categorie(idCat, nomCat);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idcarte = UUID.randomUUID().toString();
                String idquestion = UUID.randomUUID().toString();
                String idCategorie = UUID.randomUUID().toString();
                boolean br = false;
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);

                creationCarte(idcarte, idquestion, br, answer1, answer2, answer3);
                if (catChoisie != null) {
                    CarteCategorie carteCategorie = new CarteCategorie(idcarte, idCategorie);
                    carteCategorie.save();
                }

                //test affichage contenu carte
                /*List<QuestionText> quest = QuestionText.find(QuestionText.class, "idcarte = ?", carte.getIdCarte());
                if (!quest.isEmpty())
                    for (int m = 0; m < quest.size(); m++) {
                        System.out.println(quest.get(m).getNom_question());
                    }*/
                // mettre dans le bundle les informations de la carte créée pour les transmettre à l'activité qui va afficher la carte
                Intent afficherCarte = new Intent(getApplicationContext(), AfficherCarte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                afficherCarte.putExtras(bundle);
                startActivity(afficherCarte);
                finish();
            }
        });

        sauverCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idcarte = UUID.randomUUID().toString();
                String idquestion = UUID.randomUUID().toString();
                String idCategorie = UUID.randomUUID().toString();
                boolean br = false;
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);

                creationCarte(idcarte, idquestion, br, answer1, answer2, answer3);
                if (catChoisie != null) {
                    CarteCategorie carteCategorie = new CarteCategorie(idcarte, idCategorie);
                    carteCategorie.save();
                }

                Intent afficherCarte = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                startActivity(afficherCarte);
                finish();
            }
        });
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

    public void creationCarte(String idcarte, String idquestion, boolean b, EditText
            answer1, EditText answer2, EditText answer3) {
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

            System.out.println(" LAAAAAAAAA   ok1");
            CharSequence text = getText(R.string.card_created);
            int duration = Toast.LENGTH_SHORT;

            System.out.println(" LAAAAAAAAA   ok1");
            carte = new Carte(idcarte);
            QuestionText quest = new QuestionText(idquestion, question.getText().toString(), idcarte);
            for (int i = 0; i < answers.size(); i++) {
                String idrep = UUID.randomUUID().toString();
                b = answers.get(i).equals(bonneReponse);
                String nomrep = answers.get(i);
                ReponseText reponse = new ReponseText(idrep, nomrep, idcarte, b);
                reponse.save();
            }
            carte.save();
            quest.save();
            Toast.makeText(context, text, duration).show();
        }
    }
}
