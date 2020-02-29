package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherListe extends AppCompatActivity {
    ListView CarteListView;
    List<Carte> carte = Carte.listAll(Carte.class);
    List<ReponseText> reponses = ReponseText.listAll(ReponseText.class);
    List<QuestionText> questions = QuestionText.listAll(QuestionText.class);
    private EditText nom_deck;

    List<String> questioncarte = new ArrayList<>();
    ArrayList<Carte> l = new ArrayList<>();
    Deck deck;
    int count=0;
    List<Categorie> categories;
    // categories = Categorie.listAll(Categorie.class);
    Spinner spinCategories;
    ArrayAdapter<Categorie> adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);

        nom_deck = findViewById(R.id.nom_deck);

        CarteListView = (ListView) findViewById(R.id.myListView);

        CarteListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        CarteListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                mode.setTitle("" + CarteListView.getCheckedItemCount() + " items selected");

            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.menu_contextual_actionbar,menu);
                return true;
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                String idcarte;
                switch (item.getItemId()){

                    case R.id.menu_delete:
                    //action on clicking contextual action bar menu item
                        SparseBooleanArray checkedItems = CarteListView.getCheckedItemPositions();
                        ArrayList<Integer> arrayList = null;
                        if (checkedItems != null && checkedItems.size() > 0) {
                            count = checkedItems.size();
                            arrayList = new ArrayList<>(count);
                            for (int i = 0; i < count; i++) {
                                arrayList.add(checkedItems.keyAt(i));
                                System.out.println("ajout de "+checkedItems.keyAt(i));
                            }
                        }
                        String id_deck = UUID.randomUUID().toString();
                        deck = new Deck(id_deck,nom_deck.toString());
                        deck.save();
                        for(int i=0; i<count; i++)
                        {
                            System.out.println("JE SUIS LAAAAAAAA   question=" + questions.get(arrayList.get(i)).getNom_question());
                            System.out.println("id carte = " + questions.get(arrayList.get(i)).getIdCarte());
                            idcarte = questions.get(arrayList.get(i)).getIdCarte();

                            String id_cartedeck = UUID.randomUUID().toString();
                            CartesDeck cartesDeck = new CartesDeck(id_cartedeck,id_deck,idcarte);
                            cartesDeck.save();
                        }
                        Intent afficherDeck = new Intent(getApplicationContext(), AfficherDeck.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("deck", deck);
                        afficherDeck.putExtras(bundle);
                        startActivity(afficherDeck);
                        finish();
                        mode.finish();
                }
                return true;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        for (int i = 0; i < questions.size(); i++) {
            questioncarte.add(questions.get(i).getNom_question());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questioncarte);
        CarteListView.setAdapter(adapter);
    }

    public void creationCategorie(View v){
        Intent creer_categorie = new Intent(getApplicationContext(), CreerCategorie.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cartes", (Serializable) carte);
        creer_categorie.putExtras(bundle);
        startActivity(creer_categorie);
        finish();
    }

    /** Cette fonction sert pour pouvoir afficher les catégories existantes dans un spinner **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        System.out.println("TRANSMISSION DES DONNEES Nous sommes dans la fonction");
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                System.out.println("LES DONNEES SONT TRANSMISES");
                Bundle bundle = getIntent().getExtras();
                List<Categorie> categoriesCrees = (List<Categorie>) bundle.getSerializable("listeCategories");
                for(int i=0; i<categoriesCrees.size(); i++) {
                    categories.add(categoriesCrees.get(i));
                }
                if (!categories.isEmpty()){
                    spinCategories = findViewById(R.id.spinCategories);
                    adapt = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, categories);
                    spinCategories.setAdapter(adapt);
                    for(int i=0; i<categories.size(); i++) {
                        System.out.println(categories.get(i).getNom());
                    }
                }
            }
        }
    }
}
