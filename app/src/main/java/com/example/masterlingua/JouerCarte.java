package com.example.masterlingua;

import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class JouerCarte extends AppCompatActivity {
    ListView reps;
    Carte carte;
    ArrayList<String> reponses=new ArrayList<>();
    TextView question;
    String ok;
    Context context = this;
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carte);
        reps = (ListView) findViewById(R.id.list);
        question= findViewById(R.id.question);
        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,reponses);
        reps.setAdapter(adapter);

        carte = (Carte) bundle.getSerializable("carte");
        if(!carte.getReponses().isEmpty()) {
            for (int i = 0; i<carte.getReponses().size(); i++){
                reponses.add(carte.getReponses().get(i));
            }
        }
        ok=carte.getBonne_rep();
        question.setText(carte.getQuestion());
        reps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();


                if(choix.equals(ok))
                {showToastOk();
                    retour();}
                else
                {showToastNo();
                    retour();
                }
            }
        });


    }

    public void showToastOk() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_no, (ViewGroup) findViewById(R.id.toast_root));

        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.good);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    public void showToastNo() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_no, (ViewGroup) findViewById(R.id.toast_root));

        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.bad);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    public void retour()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                {Intent retour = new Intent(JouerCarte.this, AfficherDeck.class);
                    startActivity(retour);
                    finish();
                }

            }
        }, 3200);
    }
}

