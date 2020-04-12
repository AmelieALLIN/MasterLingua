package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuCreationCarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creation_carte);
    }

    public void QuestionText(View view) {
        Intent intent = new Intent(this,CreerCarte.class);
        startActivity(intent);
    }

    public void QuestionImage(View view) {
        Intent intent = new Intent(this,CreerCarteQuestionImage.class);
        startActivity(intent);
    }

    public void ReponseImage(View view) {
        Intent intent = new Intent(this,CreerCarteReponseImage.class);
        startActivity(intent);
    }


    public void questionAudio (View view) {
        Intent intent = new Intent(this,CreerCarteQuestionSon.class);
        startActivity(intent);
    }

}
