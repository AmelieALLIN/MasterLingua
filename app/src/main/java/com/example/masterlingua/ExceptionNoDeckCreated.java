package com.example.masterlingua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExceptionNoDeckCreated extends AppCompatActivity {
    ImageButton close;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_no_deck_existing);
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageAccueil = new Intent(getApplicationContext(), MenuCreationCarte.class);
                startActivity(pageAccueil);
                finish();
            }
        });
    }
}
