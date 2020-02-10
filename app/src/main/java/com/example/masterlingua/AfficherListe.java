package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AfficherListe extends AppCompatActivity {
    ListView CarteListView;
    List<Carte> carte = Carte.listAll(Carte.class);
    List<String> questioncarte = new ArrayList<>();
    ArrayList<Carte> l = new ArrayList<>();
    Deck deck;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);

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
                        for(int i=0; i<count; i++)
                        {
                            System.out.println("JE SUIS LAAAAAAAA   ?=" + carte.get(arrayList.get(i)).getQuestion());
                            System.out.println("JE SUIS LAAAAAAAA   ?=" + carte.get(arrayList.get(i)).getReponse());
                            System.out.println("JE SUIS LAAAAAAAA   ?=" + carte.get(arrayList.get(i)).getBonne_rep());
                            System.out.println("JE SUIS LAAAAAAAA   ?=" + carte.get(arrayList.get(i)));
                            l.add(carte.get(arrayList.get(i)));
                            //liste.add(carte.get(arrayList.get(i)));
                            //System.out.println(carte.get(arrayList.get(i)).getReponse().get(1));
                        }
                        deck = new Deck(l);
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

        for (int i = 0; i < carte.size(); i++) {
            questioncarte.add(carte.get(i).getQuestion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questioncarte);
        CarteListView.setAdapter(adapter);
    }

}
