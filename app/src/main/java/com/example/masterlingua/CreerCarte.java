package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    List<Categorie> categories = Categorie.listAll(Categorie.class);
    boolean catUnique;
    Categorie catChoisie = null;
    String idCat;

    String idcarte = UUID.randomUUID().toString();
    String idquestion = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);

        question = findViewById(R.id.question);
        answers = new ArrayList<>();
        Button validate = findViewById(R.id.validate);
        Button sauverCarte = findViewById(R.id.sauverCarte);
        spinCategories = findViewById(R.id.spinCategories);

        spinCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nomCat = spinCategories.getItemAtPosition(position).toString();
                catUnique = true;
                // on vérifie que la catégorie n'existe pas déjà en parcourant ce qui est dans la BD
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getNomCategorie().equals(nomCat)) {
                        catUnique = false;
                        catChoisie = categories.get(i);
                        System.out.println("YOOOOOO " + catChoisie.getNomCategorie());
                        break;
                    }
                }
                // si elle n'existe pas déjà
                if (catUnique) {
                    idCat = UUID.randomUUID().toString();
                    catChoisie = new Categorie(idCat, nomCat);
                    catChoisie.save();
                    System.out.println("PIIIIIIII " + catChoisie.getNomCategorie());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);

                creationCarte(idcarte, idquestion, answer1, answer2, answer3);
                if (catChoisie != null) {
                    CarteCategorie carteCategorie = new CarteCategorie(idcarte, idCat);
                    carteCategorie.save();
                }
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
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);
                creationCarte(idcarte, idquestion, answer1, answer2, answer3);
                if (catChoisie != null) {
                    CarteCategorie carteCategorie = new CarteCategorie(idcarte, idCat);
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

    public void creationCarte(String idcarte, String idquestion, EditText
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
                boolean b = answers.get(i).equals(bonneReponse);
                String nomrep = answers.get(i);
                ReponseText reponse = new ReponseText(idrep, nomrep, idcarte, b);
                reponse.save();
            }
            carte.save();
            quest.save();
            Toast.makeText(context, text, duration).show();
            //test affichage contenu carte
            /*List<QuestionText> quest = QuestionText.find(QuestionText.class, "idcarte = ?", carte.getIdCarte());
                if (!quest.isEmpty())
                    for (int m = 0; m < quest.size(); m++) {
                        System.out.println(quest.get(m).getNom_question());
            }*/
        }
    }
}
