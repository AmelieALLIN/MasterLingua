package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JouerCarteQuestionSonD extends AppCompatActivity {
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    String ok,idc;
    String monfichier;
    Context context = this;
    Button play,stop;
    MediaPlayer mediaPlayer;
    private int scorecarte;
    //boolean pausee=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jouer_carte_question_son);


        reps = findViewById(R.id.list);
        play=findViewById(R.id.play);
        stop=findViewById(R.id.stop);
        //pause=findViewById(R.id.pause);
        Bundle bundle = getIntent().getExtras();
        carte = (Carte) bundle.getSerializable("carte");
        idc = carte.getIdCarte();
        List<QuestionSon> quest = QuestionSon.find(QuestionSon.class,"idcarte = ?", idc);
        for(int n=0; n<quest.size();n++){
            monfichier=quest.get(n).getUrl();
        }
        mediaPlayer=new MediaPlayer();

        try {
            mediaPlayer.setDataSource(monfichier);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reponses = ReponseText.find(ReponseText.class,"idcarte = ?", idc);
        for(int i=0;i<reponses.size();i++)
        {
            nom_rep.add(reponses.get(i).getNom());
        }

        for(int y=0;y<reponses.size();y++){
            if(reponses.get(y).getbr() == true){
                ok = reponses.get(y).getNom();
            }
        }



        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nom_rep);
        reps.setAdapter(adapter);
        reps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                if(choix.equals(ok))
                {showToastOk();
                    retour();
                    scorecarte=1;
                }
                else
                {showToastNo();
                    retour();
                    scorecarte=0;

                }
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(true);
                play.setEnabled(false);
                //pause.setEnabled(true);
                mediaPlayer.start();
                new Handler().postDelayed(new Runnable() {
                                              public void run() {
                                                  stop.setEnabled(false);
                                                  play.setEnabled(true);
                                                  // pause.setEnabled(false);
                                              }
                                          }, mediaPlayer.getDuration()
                );

               /* if(pausee==false) {
                    mediaPlayer.start();
                    new Handler().postDelayed(new Runnable() {
                                                  public void run() {
                                                      stop.setEnabled(false);
                                                      play.setEnabled(true);
                                                      pause.setEnabled(false);
                                                  }
                                              }, mediaPlayer.getDuration()
                    );
                }
               else if (pausee==true){
                   pausee=false;
                   mediaPlayer.seekTo(t);
                   mediaPlayer.start();
                    new Handler().postDelayed(new Runnable() {
                                                  public void run() {
                                                      stop.setEnabled(false);
                                                      play.setEnabled(true);
                                                      pause.setEnabled(false);
                                                  }
                                              }, ((mediaPlayer.getDuration()-t))
                    );
               }*/

            }

        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play.setEnabled(true);
                stop.setEnabled(false);
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        /*pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               t = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
                play.setEnabled(true);
                stop.setEnabled(true);
                pause.setEnabled(false);
                pausee=true;
                System.out.println("temps passé++++++++++++++++++++++++++"+t);
                System.out.println("temps resntant++++++++++++++++++++++++++"+(mediaPlayer.getDuration()-t));



            }
        });*/

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

                {
                    Intent retour = new Intent(JouerCarteQuestionSonD.this, JouerDeck3Type.class);
                    retour.putExtra("score",scorecarte);
                    startActivity(retour);
                    finish();
                }

            }
        }, 2000);
    }
}











