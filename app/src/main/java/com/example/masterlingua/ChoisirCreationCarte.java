package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoisirCreationCarte extends AppCompatActivity {

    private Button goCreerCarte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouton_creation_carte);
        this.goCreerCarte = findViewById(R.id.goCreerCarte);

        goCreerCarte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent creerCarte = new Intent(getApplicationContext(), CreerCarte.class);
                startActivity(creerCarte);
                finish();
            }
        });
    }
}
