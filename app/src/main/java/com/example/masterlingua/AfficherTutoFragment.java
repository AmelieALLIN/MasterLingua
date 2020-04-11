package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AfficherTutoFragment extends Fragment {
    ImageButton close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tuto_fragment, container, true);
        close = rootView.findViewById(R.id.close);
        close.setOnClickListener(closeListener);

        return rootView;
    }
    private View.OnClickListener closeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           /* Intent pageAccueil = new Intent(String.valueOf(getLayoutInflater()));
            startActivity(pageAccueil);*/
        }
    };
}
