package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class MenuCreationCarte extends AppCompatActivity implements Serializable {

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

    public void goToTuto (View view){
        String nameToGo = "MenuCreationCarte";
        Intent intent = new Intent(this,AfficherTuto.class);
        Bundle bundle = new Bundle();
        bundle.putString("className", nameToGo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
