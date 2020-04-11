package com.example.masterlingua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreerCarteQuestionSon extends AppCompatActivity {

    private TextView text;
    ImageView i;
    private CheckBox checkAnswer1, checkAnswer2, checkAnswer3;
    String bonneReponse;
    String type="qson";
    MediaRecorder mRecorder;
    MediaPlayer mediaPlayer;
    Button start, stop, play,stop_play,validate,save;
    String monfichier="";
    EditText answer1,answer2,answer3;
    private boolean b1, b2, b3;
    private List<String> answers;
    Carte carte;
    int ok=0;
    Context context=this;

    public static final int request_code = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte_question_son);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        play=findViewById(R.id.play);
        stop_play=findViewById(R.id.stopPlay);
        text=findViewById(R.id.text1);
        answers = new ArrayList<>();
        validate = findViewById(R.id.validate);
        save=findViewById(R.id.save);
        i=findViewById(R.id.micImage);
         answer1 = findViewById(R.id.answer1);
         answer2 = findViewById(R.id.answer2);
         answer3 = findViewById(R.id.answer3);
        checkAnswer1 = findViewById(R.id.checkAnswer1);
        checkAnswer2 = findViewById(R.id.checkAnswer2);
        checkAnswer3 = findViewById(R.id.checkAnswer3);

        if (checkPermissionFromDevice()){

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monfichier= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+
                            UUID.randomUUID()+"AudioFile.mp3";

                    SetupMediaRecorder();

                    try {
                        mRecorder.prepare();
                        mRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    start.setEnabled(false);
                    stop.setEnabled(true);
                    play.setEnabled(false);
                    stop_play.setEnabled(false);
                    ok++;
                }
            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecorder.stop();
                    play.setEnabled(true);
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    stop_play.setEnabled(false);
                    mediaPlayer=new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(monfichier);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    start.setEnabled(false);
                    stop.setEnabled(false);
                    play.setEnabled(false);
                    stop_play.setEnabled(true);
                    new Handler().postDelayed(new Runnable()
                                              {
                                                  public void run()
                                                  {
                                                      stop_play.setEnabled(false);
                                                      start.setEnabled(true);
                                                      play.setEnabled(true);
                                                  }
                                              }, mediaPlayer.getDuration()
                    );

                }
            });

            stop_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    SetupMediaRecorder();
                    start.setEnabled(true);
                    stop_play.setEnabled(false);
                    play.setEnabled(true);
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else {

            requestPermissionFromDevice();

        }
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
             saveCard();
                    // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                    Intent afficherCarte = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    afficherCarte.putExtras(bundle);
                    startActivity(afficherCarte);
                    finish();
                }

        });
        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveCard();
                // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                Intent afficherCarte = new Intent(getApplicationContext(), JouerCarteQuestionSon.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                bundle.putString("chemin",monfichier);
                afficherCarte.putExtras(bundle);
                startActivity(afficherCarte);
                finish();
            }

        });



    }


    private void saveCard(){
        String idcarte = UUID.randomUUID().toString();
        String idquestion = UUID.randomUUID().toString();
        String idrep;
        boolean br;
        //si le champ de la question est vide : toast pour dire que la question est obligatoire pour valider la carte
        if (ok==0) {
            CharSequence text = getText(R.string.warning_question);
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, "Veuillez enregistrer votre question et deux réponses minimum", duration).show();
        }
        else {
            // vérifier si chaque champ de réponse est vide, sinon ajouter la réponse à la liste answers
            if(!answer1.getText().toString().isEmpty()){ // champ de réponse 1
                answers.add(answer1.getText().toString());
                if(checkAnswer1.isChecked()) b1 = true;
                if(b1) bonneReponse = answers.get(0);
            }
            if(!answer2.getText().toString().isEmpty()){ // champ de réponse 2
                answers.add(answer2.getText().toString());
                if(checkAnswer2.isChecked()) b2 = true;
                if(b2) bonneReponse = answers.get(1);
            }
            if(!answer3.getText().toString().isEmpty()){ // champ de réponse 3
                answers.add(answer3.getText().toString());
                if(checkAnswer3.isChecked()) b3 = true;
                if(b3) bonneReponse = answers.get(2);
            }

            System.out.println(" LAAAAAAAAA   ok1");
            CharSequence text = getText(R.string.card_created);
            int duration = Toast.LENGTH_SHORT;

            carte = new Carte(idcarte,type);
            QuestionSon quest = new QuestionSon(idquestion,monaudio(monfichier),idcarte);
            for (int i = 0; i < answers.size(); i++) {
                idrep = UUID.randomUUID().toString();
                if (answers.get(i) == bonneReponse) {
                    br = true;
                }
                else
                {
                    br = false;
                }
                String nomrep = answers.get(i);
                ReponseText reponse = new ReponseText(idrep,nomrep,idcarte,br);
                reponse.save();
            }
            carte.save();
            quest.save();
            Toast.makeText(context, text, duration).show();
    }
    }

    private void SetupMediaRecorder() {
        mRecorder=new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(monfichier);
    }

    private void requestPermissionFromDevice() {
        ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO},
                request_code);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case request_code:
            {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    Toast.makeText(getApplicationContext(),"permission granted...",Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"permission denied...",Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                    finish();
                }
            }
            break;
        }
    }

    private boolean checkPermissionFromDevice() {
        int storage_permission= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recorder_permssion=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
        return storage_permission == PackageManager.PERMISSION_GRANTED && recorder_permssion == PackageManager.PERMISSION_GRANTED;
    }


    private String monaudio(String url)
    {
        String audio = "";
        URL u = null;
        InputStream is = null;
        byte[] data = new byte[0];

        try {
            u = new URL(url);
            is = u.openStream();
            HttpURLConnection huc = (HttpURLConnection)u.openConnection();

            if(huc != null)
            {
                InputStream inputStream =  huc.getInputStream();
                byte[] buff = new byte[8000];
                int bytesRead = 0;

                ByteArrayOutputStream bao = new ByteArrayOutputStream();

                while((bytesRead = inputStream.read(buff)) != -1) {
                    bao.write(buff, 0, bytesRead);
                }

                data = bao.toByteArray();
                audio=Base64.encodeToString(data,Base64.DEFAULT);

            }
        }catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if(is != null){
                    is.close();
                }
            }catch (IOException ioe) {

            }

        }
        return audio;
    }
    public void onCheckBoxClicked(View view){
        checkAnswer1 = findViewById(R.id.checkAnswer1);
        checkAnswer2 = findViewById(R.id.checkAnswer2);
        checkAnswer3 = findViewById(R.id.checkAnswer3);

        // s'assurer qu'au total une seule réponse est cochée correcte
        checkAnswer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer2.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer1.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer1.setChecked(false);
                    checkAnswer2.setChecked(false);
                }
            }
        });

    }


}