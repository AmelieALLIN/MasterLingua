package com.example.masterlingua;

import android.content.Context;
//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CreerCarte extends AppCompatActivity {
    private Carte carte;
    private EditText question;
    private List<String> answers;
    String bonne_rep;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);

        question = findViewById(R.id.question);
        answers = new ArrayList<>();
        Button validate = findViewById(R.id.validate);

        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText answer1 = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);
                EditText answer3 = findViewById(R.id.answer3);
                RadioGroup rbanswer1 = findViewById(R.id.rbanswer1);
                RadioGroup rbanswer2 = findViewById(R.id.rbanswer2);
                RadioGroup rbanswer3 = findViewById(R.id.rbanswer3);

                //si le champ de la question est vide : toast pour dire que la question est obligatoire pour valider la carte
                if (question.getText().toString().isEmpty()){
                    CharSequence text = getText(R.string.warning_question);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
                else {
                    // vérifier si chaque champ de réponse est vide, sinon ajouter la réponse à la liste answers
                    if(!answer1.getText().toString().isEmpty()){ // champ de réponse 1
                        answers.add(answer1.getText().toString());
                        if(rbanswer1.getCheckedRadioButtonId() == R.id.radio_true1) bonne_rep = answer1.getText().toString();
                    }
                    if(!answer2.getText().toString().isEmpty()){ // champ de réponse 2
                        answers.add(answer2.getText().toString());
                        if(rbanswer2.getCheckedRadioButtonId() == R.id.radio_true2) bonne_rep = answer2.getText().toString();
                    }
                    if(!answer3.getText().toString().isEmpty()){ // champ de réponse 3
                        answers.add(answer3.getText().toString());
                        if(rbanswer3.getCheckedRadioButtonId() == R.id.radio_true3) bonne_rep = answer3.getText().toString();
                    }
                    // en faire un logger
                    /*for(int i=0; i<answers.size(); i++){
                        System.out.println(answers.get(i) + i);
                    }*/
                    // construire la carte avec le bon nombre de réponses en argument
                    CharSequence text = getText(R.string.card_created);
                    int duration = Toast.LENGTH_SHORT;
                    if (answers.size() == 3) {
                        carte = new Carte(question.getText().toString(), answers.get(0), answers.get(1), answers.get(2), bonne_rep);
                        carte.save();
                        Toast.makeText(context, text, duration).show();
                        answers.removeAll(answers);
                    }
                    if (answers.size() == 2) {
                        carte = new Carte(question.getText().toString(), answers.get(0), answers.get(1), bonne_rep);
                        carte.save();
                        Toast.makeText(context, text, duration).show();
                        answers.removeAll(answers);
                    }
                    if (answers.size() == 1) {
                        carte = new Carte(question.getText().toString(), answers.get(0), bonne_rep);
                        carte.save();
                        Toast.makeText(context, text, duration).show();
                        answers.removeAll(answers);
                    }
                    if (answers.isEmpty()) {
                        bonne_rep = null;
                        carte = new Carte(question.getText().toString(),bonne_rep);
                        carte.save();
                        Toast.makeText(context, text, duration).show();
                    }
                }

                Intent afficherCarte = new Intent(getApplicationContext(), AfficherCarte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                afficherCarte.putExtras(bundle);

                afficherCarte.putExtra("rep1",answer1.getText().toString());
                afficherCarte.putExtra("rep2",answer2.getText().toString());
                afficherCarte.putExtra("rep3",answer3.getText().toString());
                afficherCarte.putExtra("bonne_rep",bonne_rep);

                startActivity(afficherCarte);
                finish();
            }
        });

    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton)view).isChecked();
        switch(view.getId()){
            case R.id.radio_true1:
                if(checked)
                    break;
            case R.id.radio_false1:
                if(checked)
                    break;
        }
        switch (view.getId()){
            case R.id.radio_true2:
                if(checked)
                    break;
            case R.id.radio_false2:
                if(checked)
                    break;
        }
        switch (view.getId()){
            case R.id.radio_true3:
                if(checked)
                    break;
            case R.id.radio_false3:
                if(checked)
                    break;
        }
    }

}
